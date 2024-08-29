package ecommerce.controllers;

import com.google.common.base.Preconditions;
import ecommerce.dto.LoginDto;
import ecommerce.models.User;
import ecommerce.security.TokenJwtManager;
import ecommerce.services.AbstractUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenJwtManager tokenManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AbstractUserService userService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Authentication authenticatedToken = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        Preconditions.checkArgument(authenticatedToken.isAuthenticated(), "Invalid email or password. ");

        User user = userService.getBy(loginDto.getEmail());

        String generatedToken = tokenManager.generate(
                user.getEmail(),
                user.getRole()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("token", generatedToken);
        return ResponseEntity.ok(response);
    }
}
