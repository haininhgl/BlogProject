package com.bezkoder.spring.jwt.mongodb.service;

import com.bezkoder.spring.jwt.mongodb.entity.Role;
import com.bezkoder.spring.jwt.mongodb.entity.RoleType;
import com.bezkoder.spring.jwt.mongodb.entity.User;
import com.bezkoder.spring.jwt.mongodb.exception.BadRequestException;
import com.bezkoder.spring.jwt.mongodb.repository.RoleRepository;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;
import com.bezkoder.spring.jwt.mongodb.request.LoginRequest;
import com.bezkoder.spring.jwt.mongodb.request.SignupRequest;
import com.bezkoder.spring.jwt.mongodb.response.JwtResponse;
import com.bezkoder.spring.jwt.mongodb.security.jwt.JwtService;
//import com.bezkoder.spring.jwt.mongodb.security.jwt.JwtUtils;
import com.bezkoder.spring.jwt.mongodb.security.services.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private  final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

//    private final JwtUtils jwtUtils;

    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String jwt = jwtService.generateToken(userDetails);

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    public void registerUser(SignupRequest signUpRequest) throws BadRequestException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (CollectionUtils.isEmpty(strRoles)) {
            Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new BadRequestException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            Set<RoleType> roleTypeSet = new HashSet<>();
            strRoles.forEach(s -> {
                try {
                    roleTypeSet.add(RoleType.valueOf(s));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            if (roleTypeSet.size() != strRoles.size()) {
                throw new BadRequestException("Error: Role is not valid.");
            }

            Set<Role> foundRoleList = roleRepository.findByNameIn(roleTypeSet);

            roles.addAll(foundRoleList);
        }

        user.setRoles(roles);
        userRepository.save(user);
    }
}


