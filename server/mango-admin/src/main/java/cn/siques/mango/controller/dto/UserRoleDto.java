package cn.siques.mango.controller.dto;

public class UserRoleDto {
    private long userId;
    private long roleId;

    @Override
    public String toString() {
        return "UserRoleDto{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public UserRoleDto(long userId, long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
