package com.xtoon.boot.interfaces.sys.facade.assembler;

import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.RoleId;
import com.xtoon.boot.domain.model.user.types.UserId;
import com.xtoon.boot.domain.model.user.types.UserName;
import com.xtoon.boot.domain.shared.StatusEnum;
import com.xtoon.boot.interfaces.sys.facade.dto.UserDTO;

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
        List<String> roleIdList = userDTO.getRoleIdList();
        List<Role> roleList = null;
        if(roleIdList != null && !roleIdList.isEmpty()) {
            roleList = new ArrayList<>();
            for(String roleId : roleIdList) {
                roleList.add(new Role(new RoleId(roleId)));
            }
        }
        UserName userName = null;
        if(userDTO.getUserName() != null) {
            userName = new UserName(userDTO.getUserName());
        }
        return new User(new UserId(userDTO.getId()),userName, StatusEnum.getStatusEnum(userDTO.getStatus()),null, roleList);
    }

    public static UserDTO fromUser(final User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId() == null?null:user.getUserId().getId());
        userDTO.setUserName(user.getUserName() == null?null:user.getUserName().getName());
        userDTO.setEmail(user.getAccount().getEmail() == null?null:user.getAccount().getEmail().getEmail());
        userDTO.setMobile(user.getAccount().getMobile() == null?null:user.getAccount().getMobile().getMobile());
        userDTO.setRoleIdList(user.getUserRoleIds() == null?null:user.getUserRoleIds());
        userDTO.setStatus(user.getStatus() == null?null:user.getStatus().getValue());
        return userDTO;
    }
}
