package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.config.JWTUserData;
import com.rodrigvf.copiou_papelaria_api.dto.request.UserChangePasswordRequest;
import com.rodrigvf.copiou_papelaria_api.dto.request.UserRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.UserResponse;
import com.rodrigvf.copiou_papelaria_api.entity.User;
import com.rodrigvf.copiou_papelaria_api.exception.UnauthorizedException;
import com.rodrigvf.copiou_papelaria_api.exception.UsernameOrPasswordInvalidException;
import com.rodrigvf.copiou_papelaria_api.mapper.UserMapper;
import com.rodrigvf.copiou_papelaria_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('user:get_all')")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('user:get_by_id')")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(UserMapper.toUserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('user:create')")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest request) {
        User savedUser = userService.save(UserMapper.toUser(request));
        return ResponseEntity.ok(UserMapper.toUserResponse(savedUser));
    }

    //TODO: PUT
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('product:update')")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        return userService.update(id, UserMapper.toUser(request))
                .map(user -> ResponseEntity.ok(UserMapper.toUserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('user:deactivate')")
    public ResponseEntity<UserResponse> inactivate(@PathVariable Long id) {
        return userService.changeStatus(id, false)
                .map(user -> ResponseEntity.ok(UserMapper.toUserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/activate/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('user:activate')")
    public ResponseEntity<UserResponse> activate(@PathVariable Long id) {
        return userService.changeStatus(id, true)
                .map(user -> ResponseEntity.ok(UserMapper.toUserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<?> changePassword(
            @PathVariable Long id,
            @RequestBody @Valid UserChangePasswordRequest passwordChangeRequest) {

        Long userIdFromToken = ((JWTUserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).id();

        if (!userIdFromToken.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Você não tem permissão para alterar a senha de outro usuário.");
        }

        userService.changePassword(id, passwordChangeRequest.currentPassword(), passwordChangeRequest.newPassword());

        return ResponseEntity.ok("Senha alterada com sucesso.");
    }

}
