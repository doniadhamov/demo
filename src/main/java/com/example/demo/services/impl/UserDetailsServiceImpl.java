package com.example.demo.services.impl;

import com.example.demo.configurations.ProjectUserDetails;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userInfoService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        System.out.println(" username : " + username);

        // TODO : username orqali user ni topish kerak
        if (username == null || username.isEmpty()) {
            System.out.println(" Username cannot be empty ");

            throw new UsernameNotFoundException(" Username cannot be empty ");
        }

        com.example.demo.domains.User user = userRepository.findByUsernameAndFetchRolesEarly(username);
        if (user == null) {
            System.out.println(" User [" + username + "] not found ");
            throw new UsernameNotFoundException(" User [" + username + "] not found ");
        }

        if (user.getEnabled() != Boolean.TRUE) {
            System.out.println(" Your account have been blocked  ");
            throw new UsernameNotFoundException(" Your account have been blocked ");
        }

        ProjectUserDetails userDetails = new ProjectUserDetails(user);
//        User userSpring = new User(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        user.setLastVisit(new Date());
        userRepository.save(user);

        return userDetails;
    }
}
