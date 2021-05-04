package com.euvic.carrental.controllers;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.AuthenticationResponse;
import com.euvic.carrental.services.UserService;
import com.euvic.carrental.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginApi {


    private final UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginApi(final UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<?> login(@RequestBody final User userDTO) throws Exception {

        final User user = null

        final Authentication authentication;

        try {
            user = userService.getEntityByLoginAndisActive(userDTO.getLogin(), true);
            if(user == null){
                throw new BadCredentialsException("User was not found");
            }
            
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (final BadCredentialsException e) {
            throw new Exception("Bad login creds", e);
        }

        final String token = jwtUtil.generateToken(authentication);

        final String role;

        role = user.getRole().getName();

        return ResponseEntity.ok(new AuthenticationResponse(token, role));
    }
}
