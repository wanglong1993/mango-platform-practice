package cn.siques.mango.service.impl;

import cn.siques.mango.service.SysDeptService;
import cn.siques.mangocore.Page.PageRequest;
import cn.siques.mangocore.Page.PageResult;
import cn.siques.mangocore.dao.SysDeptMapper;
import cn.siques.mangocore.entity.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    SysDeptMapper sysDeptMapper;


    @Override
    public int save(SysDept record) {
        return 0;
    }

    @Override
    public int delete(SysDept record) {
        return 0;
    }

    @Override
    public int delete(List<SysDept> records) {
        return 0;
    }

    @Override
    public SysDept findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public List<SysDept> findDeptTree() {
        List<SysDept> sysDepts = new ArrayList<>();
        // 查出所有的机构进行排序
        List<SysDept> depts= sysDeptMapper.findAll();
        for (SysDept dept:depts
             ) {
            if(dept.getParentId()==null|| dept.getParentId()==0){
                   dept.setLevel(0);
                   sysDepts.add(dept);
            }
        }

        findChildren(sysDepts, depts);
        return sysDepts;
    }

    // TODO 逻辑
    private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
        for (SysDept sysDept : sysDepts) {
            List<SysDept> children = new ArrayList<>();
            for (SysDept dept : depts) {
                if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
                    dept.setParentName(dept.getName());
                    dept.setLevel(sysDept.getLevel() + 1);
                    children.add(dept);
                }
            }
            sysDept.setChildren(children);
            findChildren(children, depts);
        }
    }

}
