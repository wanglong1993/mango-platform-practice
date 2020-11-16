package cn.siques.mangooauth.service;

import cn.siques.mangooauth.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Optional;
import java.util.Set;

/**
 * @author : heshenghao
 * @date : 18:17 2020/11/13
 */

public interface SysUserService  extends IService<SysUser> {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    SysUser findUserByName(String username);
}
