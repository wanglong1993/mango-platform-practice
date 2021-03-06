package cn.central.controller;



import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.common.constant.AdminConstants;

import cn.central.common.exception.BusinessException;
import cn.central.common.model.Result;
import cn.central.common.model.SysUser;
import cn.central.controller.dto.UserRoleDto;

import cn.central.dao.SysUserRoleMapper;
import cn.central.entity.SysUserRole;
import cn.central.search.model.LogicDelDto;
import cn.central.search.model.SearchDto;
import cn.central.search.service.IQueryService;
import cn.central.service.SysUserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/pri/user")
@Api(description = "SysUserController", tags = {"用户接口"})
public class SysUserController {


    @Resource
    SysUserService sysUserService;

    @Resource
    BCryptPasswordEncoder passwordEncoder;

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private IQueryService queryService;

    @PreAuthorize("@el.check('sys:user:edit')")
    @PostMapping("/saveRole/{id}")
    @ApiOperation(value = "保存用户角色关系", notes = "保存用户角色关系")
    @Transactional
    public Result saveRole(@RequestBody List<SysUserRole> userRoleList, @PathVariable("id") Long userId){
        // 删除所有已指定的角色关系
        sysUserService.delUserRoleByUserId(userId);
        // 如果size为0，只删除即可
        if(userRoleList.size()==0) return Result.succeed("");

        SysUser user = sysUserService.getById(userId);

        if(AdminConstants.ADMIN.equalsIgnoreCase(user.getUserCode())){
            return Result.failed("管理员不允许修改");
        }
        userRoleList.forEach(sysUserRole -> {
            // id必须一致
            if(sysUserRole.getUserId()==userId)  sysUserRoleMapper.insert(sysUserRole);
            else throw new BusinessException("业务异常");
        });
        return Result.succeed("");
    }

    @PreAuthorize("@el.check('sys:user:edit')")
    @PutMapping("/password/{id}")
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @Transactional
    public Result resetPassword(@PathVariable("id") String userId){

        // 提供密码的情况
          return Result.succeed(sysUserService.update(new UpdateWrapper<SysUser>()
                  .eq("id",userId).set("password",passwordEncoder.encode(AdminConstants.PASSWORD))));
    }

    @PreAuthorize("@el.check('sys:user:delete')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @Transactional
    public Result delete(@PathVariable("id") String id){
       return Result.succeed(sysUserService.removeById(id));
    }


    @PreAuthorize("@el.check('sys:user:add') AND @el.check('sys:user:edit')")
    @PostMapping()
    @ApiOperation(value = "保存或更新用户", notes = "保存或更新用户")
    public Result save(@RequestBody SysUser record){
        // 查询是否已有该用户
        SysUser user = sysUserService.getOne(new QueryWrapper<SysUser>().eq("user_code",record.getUserCode()));
        // 如果没有，直接新增
        if(user==null) return Result.succeed(sysUserService.save(record));
        // 到这里说明数据库有该用户 判断是否为admin用户
        if( AdminConstants.ADMIN.equalsIgnoreCase(user.getUserCode())){
            return Result.failed("不允许修改超级管理员");
        }

        // 更新其他字段
        return Result.succeed(sysUserService.update(record,new QueryWrapper<SysUser>().eq("user_code",record.getUserCode())));
    }


    /**
     * 查找用户权限, el表达式查询必用接口
     *  {@link cn.central.common.config.ElPermissionConfig}
     *
     * 必须是登录用户才可以远程调用此接口
     * @param userCode
     * @return
     */
    @ApiOperation(httpMethod="GET", value="根据用户code查询权限")
    @GetMapping("permissions/{userCode}")
    public Set<String> findPermissionsByUserCode(@PathVariable String userCode){
        return sysUserService.findPermission(userCode);
    }

    /**
     * 一个用户可能含有多个角色
     * @param id
     * @return
     */
    @ApiOperation(httpMethod="GET", value="根据id查询用户角色")
    @PreAuthorize("@el.check('sys:role:view')")
    @GetMapping(value = "findUserRoles/{id}")
    public Result findUserRoles(@PathVariable("id") long id){
       return Result.succeed(sysUserService.findUserRolesById(id));
    }

    @PreAuthorize("@el.check('sys:user:view')")
    @PostMapping("/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest){
        IPage<SysUser> userPage = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        return Result.succeed( sysUserService.page(userPage));
    }


    @ApiOperation(httpMethod="POST", value="查询机构用户")
    @PreAuthorize("@el.check('sys:user:view')")
    @PostMapping("/findOfficeUser/{id}")
    public Result findOfficeUser(@PathVariable("id") Long id, @RequestBody PageRequest pageRequest){
        IPage<SysUser> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());

        return Result.succeed(sysUserService.findUsersByOfficeId(page,id));
    }

    private static final LogicDelDto SEARCH_LOGIC_DEL_DTO = new LogicDelDto("status", "是");

    @ApiOperation(value = "用户全文搜索列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "queryStr", value = "搜索关键字", dataType = "String")
    })
    @GetMapping("/search")
    public PageResult<JsonNode> search(SearchDto searchDto) {
        searchDto.setIsHighlighter(true);
        searchDto.setSortCol("createTime");
        return queryService.strQuery("sys_user", searchDto, SEARCH_LOGIC_DEL_DTO);
    }

}
