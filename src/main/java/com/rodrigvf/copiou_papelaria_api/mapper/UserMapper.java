package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.ProductRequest;
import com.rodrigvf.copiou_papelaria_api.dto.request.UserRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.*;
import com.rodrigvf.copiou_papelaria_api.entity.*;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UserMapper {

    public static User toUser (UserRequest request) {

        Role role = Role.builder().id(request.roleId()).build();

        return User.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .isActive(request.isActive())
                .role(role)
                .build();
    }

    public static UserResponse toUserResponse(User user) {

        RoleResponse role = user.getRole() != null ? RoleMapper.toRoleResponse(user.getRole()) : null;

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isActive(user.getIsActive())
                .role(role)
                .lastLogin(user.getLastLogin())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

}
