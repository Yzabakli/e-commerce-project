package com.abakli.service.impl;

import com.abakli.entity.User;
import com.abakli.entity.common.UserPrincipal;
import com.abakli.repository.UserRepository;
import com.abakli.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByPhoneNumber(username);

        if (user.isEmpty()) {

            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user.get());
    }
}