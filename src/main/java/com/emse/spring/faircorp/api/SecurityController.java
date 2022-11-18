package com.emse.spring.faircorp.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @GetMapping(path = "/{id}")
    public String findUserName(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }
}
