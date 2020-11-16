package cn.siques.mangocore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class SysDeptUser extends SysUser {
    public  SysDept sysDept;
}