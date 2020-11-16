package cn.siques.mango.service.impl;

import cn.siques.mango.service.SysDictService;
import cn.siques.mangocommon.Page.MybatisPageHelper;
import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocore.dao.SysDictMapper;
import cn.siques.mangocore.entity.SysDict;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDictServiceImpl implements SysDictService {
    @Resource
    private SysDictMapper sysDictMapper;



    @Override
    public int save(SysDict record) {
        if(record.getId()==null || record.getId()==0){
            return sysDictMapper.insert(record);
        }
       return sysDictMapper.updateByPrimaryKey(record);
    }

    @Override
    public int delete(SysDict record) {
      return sysDictMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDict> records) {
        for (SysDict d:records
             ) {
            sysDictMapper.deleteByPrimaryKey(d.getId());
        }
        return 1;
    }

    @Override
    public SysDict findById(Long id) {
        return sysDictMapper.selectByPrimaryKey(id);
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
