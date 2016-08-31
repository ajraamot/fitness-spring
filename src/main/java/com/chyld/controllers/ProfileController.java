package com.chyld.controllers;


import com.chyld.dtos.AuthDto;
import com.chyld.entities.Profile;
import com.chyld.entities.Role;
import com.chyld.entities.User;
import com.chyld.enums.RoleEnum;
import com.chyld.security.JwtToken;
import com.chyld.services.ProfileService;
import com.chyld.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(final UserService userService, final ProfileService profile) {
        this.userService = userService;
        this.profileService = profile;
    }

    @RequestMapping( value = "/profiles", method = RequestMethod.POST)
    public ResponseEntity<?> saveProfile(@RequestBody Profile profile, Principal prin) throws JsonProcessingException {

        int uid = ((JwtToken) prin).getUserId();
        User u = userService.findUserById(uid);

        if (u.getProfile() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        u.setProfile(profile);
        profile.setUserId(u);
        userService.saveUser(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }
/*        if (ud != null) {
            return ResponseEntity.badRequest().body(EMAIL_EXISTS_MESSAGE);
        }

        User user = new User();
        user.setEnabled(true);
        user.setUsername(auth.getUsername());
        user.setPassword(passwordEncoder.encode(auth.getPassword()));

        user.setRoles(new ArrayList<>());
        Role r = roleService.findByRole(RoleEnum.USER);
        user.getRoles().add(r);

        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(null);
                */

}
