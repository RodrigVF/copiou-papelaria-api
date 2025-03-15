package com.rodrigvf.copiou_papelaria_api.config;

import com.rodrigvf.copiou_papelaria_api.service.PermissionService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class PermissionInitializer {

    @Autowired
    private PermissionService permissionService;

    // Método executado ao iniciar a aplicação
    @PostConstruct
    @Transactional
    public void extractAndSavePermissions() {
        Reflections reflections = new Reflections("com.rodrigvf.copiou_papelaria_api.controller", Scanners.TypesAnnotated);
        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(RestController.class);

        Pattern permissionPattern = Pattern.compile("hasAuthority\\('([^']+)'\\)");

        for (Class<?> controllerClass : controllerClasses) {
            for (var method : controllerClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(org.springframework.security.access.prepost.PreAuthorize.class)) {
                    String expression = method.getAnnotation(org.springframework.security.access.prepost.PreAuthorize.class).value();

                    Matcher matcher = permissionPattern.matcher(expression);
                    while (matcher.find()) {
                        String permission = matcher.group(1);
                        permissionService.registerPermission(permission);
                    }
                }
            }
        }
    }
}
