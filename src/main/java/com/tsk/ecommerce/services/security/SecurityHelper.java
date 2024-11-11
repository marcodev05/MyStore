package com.tsk.ecommerce.services.security;

import com.tsk.ecommerce.entities.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {

    public static UserEntity user(){
         CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         return customUserDetails.getUserEntity();
    }
}
