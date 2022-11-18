package com.emse.spring.faircorp.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping(path = "/{id}")
    public String findUserName(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }
}
