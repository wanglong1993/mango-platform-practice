package cn.central.service.impl;


import cn.central.common.Page.MybatisPageHelper;
import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.dao.SysDictMapper;
import cn.central.entity.SysDict;
import cn.central.service.SysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {
    @Resource
    private SysDictMapper sysDictMapper;


    public SysDict findById(Long id) {
        return sysDictMapper.selectById(id);
    }

    /**
     * 分页
     * @param pageRequest
     * @return
     */
    @Override
    public PageResult findPage(PageRequest pageRequest) {
    Object label = pageRequest.getParams().get("label");

    System.out.println(label);
        if(label!=null){
            // 如果有label 调用这个
            return MybatisPageHelper.findPage(pageRequest,sysDictMapper,"findPageByLabel",label);
        }
        // 没有的话调用这个
        return MybatisPageHelper.findPage(pageRequest,sysDictMapper);
    }

    /**
     * 不包含分页信息
     * @param label
     * @return
     */
    @Override
    public List<SysDict> findByLabel(String label) {
            return sysDictMapper.findByLabel(label);
    }


}
