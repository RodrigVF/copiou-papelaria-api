package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import com.rodrigvf.copiou_papelaria_api.entity.Permission;
import com.rodrigvf.copiou_papelaria_api.entity.Role;
import com.rodrigvf.copiou_papelaria_api.entity.User;
import com.rodrigvf.copiou_papelaria_api.exception.UnauthorizedException;
import com.rodrigvf.copiou_papelaria_api.exception.UsernameOrPasswordInvalidException;
import com.rodrigvf.copiou_papelaria_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public User save(User user) {
        user.setRole(this.findRole(user.getRole()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public Optional<User> update(Long userId, User updateUser) {
        Optional<User> optUser = repository.findById(userId);
        if (optUser.isPresent()) {

            Role role = this.findRole(updateUser.getRole());

            User user = optUser.get();
            user.setUsername(updateUser.getUsername());
            user.setEmail(updateUser.getEmail());
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setIsActive(updateUser.getIsActive());
            user.setRole(role);

            repository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public void changePassword(Long userId, String currentPassword,String newPassword) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UnauthorizedException("Usuário não encontrado."));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new UsernameOrPasswordInvalidException("Senha atual incorreta.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }

    public Optional<User> changeStatus(Long userId, Boolean active) {
        Optional<User> optUser = repository.findById(userId);
        if (optUser.isPresent()) {
            User user = optUser.get();
            user.setIsActive(active);

            repository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    private Role findRole(Role role) {
        return roleService.findById(role.getId()).orElse(null);
    }

}
