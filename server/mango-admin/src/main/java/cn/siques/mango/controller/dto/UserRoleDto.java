package cn.siques.mango.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRoleDto {
    private long userId;
    private long roleId;
}
