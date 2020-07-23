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
                List<Long> treeDept= new ArrayList<>();
                   dept.setLevel(0);
                   treeDept.add(dept.getId());
                   dept.setDeptList(treeDept);
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
            // 最父节点的树


            for (SysDept dept : depts) {
                // 遍历所有节点，若该节点的父节点与最外层节点相同，则说明是子节点
                List<Long> tree = new ArrayList<>();
                if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
//                    // 父节点的机构树
                    List<Long> deptList = sysDept.getDeptList();
                    tree.addAll(deptList);
//                    // 添加子节点id
                    tree.add(dept.getId());
//                    // 将树设置到子节点
                   dept.setDeptList(tree);
                    dept.setParentName(dept.getName());
                    dept.setLevel(sysDept.getLevel() + 1);
                    children.add(dept);
                }
            }
            if(children.size()!=0){

                sysDept.setChildren(children);
            }
            findChildren(children, depts);
        }
    }

}
