package io.alexc.classroomauthdemo.classroomauthdemo.security;

import io.alexc.classroomauthdemo.classroomauthdemo.entity.Authority;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.UserDomain;
import io.alexc.classroomauthdemo.classroomauthdemo.error.UserNotActivatedException;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.UserDomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component("userDetailsService")
@RequiredArgsConstructor
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {

    private final UserDomainRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.debug("Authenticating {}", username);

        // if the username is email we can validate it as an email
        // in our case we continue

        String usernameLowerCase = username.toLowerCase(Locale.ENGLISH);

        return userRepository.findByUsername(usernameLowerCase)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found."));

    }

    private User createSpringSecurityUser(UserDomain user) {
        if (!user.getEnabled()) {
            throw new UserNotActivatedException("User with username " + user.getUsername() + " is disabled.");
        }
        List<SimpleGrantedAuthority> authorities = user.getAuthorities()
                .stream()
                .map(Authority::getAuthority)
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
