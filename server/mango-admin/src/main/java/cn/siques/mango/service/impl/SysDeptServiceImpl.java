package cn.siques.mango.service.impl;

import cn.siques.mango.annotation.SaveDate;
import cn.siques.mango.service.SysDeptService;

import cn.siques.mangocommon.Page.MybatisPageHelper;
import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.utils.SecurityUtils;
import cn.siques.mangocore.dao.SysDeptMapper;
import cn.siques.mangocore.entity.SysDept;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper,SysDept> implements SysDeptService {
    @Resource
    SysDeptMapper sysDeptMapper;


    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, sysDeptMapper);
        return page;
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

    @Override
    public PageResult findPageById(PageRequest pageRequest, Long id) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, sysDeptMapper,"findPageById",id);
        return page;
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
