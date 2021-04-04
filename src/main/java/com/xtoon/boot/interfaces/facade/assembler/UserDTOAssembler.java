package com.xtoon.boot.interfaces.facade.assembler;

import com.xtoon.boot.domain.model.types.RoleId;
import com.xtoon.boot.domain.model.types.UserId;
import com.xtoon.boot.domain.model.types.UserName;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.shared.StatusEnum;
import com.xtoon.boot.interfaces.facade.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户Assembler
 *
 * @author haoxin
 * @date 2021-02-23
 **/
public class UserDTOAssembler {

    public static User toUser(final UserDTO userDTO) {
        List<String> roleIds = userDTO.getRoleIdList();
        List<RoleId> roleIdList = null;
        if(roleIds != null && !roleIds.isEmpty()) {
            roleIdList = new ArrayList<>();
            for(String roleId : roleIds) {
                roleIdList.add(new RoleId(roleId));
            }
        }
        UserName userName = null;
        if(userDTO.getUserName() != null) {
            userName = new UserName(userDTO.getUserName());
        }
        return new User(new UserId(userDTO.getId()),userName, StatusEnum.getStatusEnum(userDTO.getStatus()),null,null, roleIdList);
    }

    public static UserDTO fromUser(final User user) {
        List<String> roleIdList = new ArrayList<>();
        if(user.getRoleIds() != null) {
            user.getRoleIds().forEach(roleId -> {
                roleIdList.add(roleId.getId());
            });
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId() == null?null:user.getUserId().getId());
        userDTO.setUserName(user.getUserName() == null?null:user.getUserName().getName());
        userDTO.setEmail(user.getAccount().getEmail() == null?null:user.getAccount().getEmail().getEmail());
        userDTO.setMobile(user.getAccount().getMobile() == null?null:user.getAccount().getMobile().getMobile());
        userDTO.setRoleIdList(roleIdList);
        userDTO.setStatus(user.getStatus() == null?null:user.getStatus().getValue());
        return userDTO;
    }
}
