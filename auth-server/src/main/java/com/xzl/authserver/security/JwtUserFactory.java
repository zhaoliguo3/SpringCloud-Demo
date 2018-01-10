package com.xzl.authserver.security;

import com.xzl.authserver.domain.User;
import com.xzl.boilerplate.common.security.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

//import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthorities(user.genRolesArray()),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        List<GrantedAuthority> list = new ArrayList<>();
        for (String authority : authorities) {
            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(authority);
            list.add(sga);
        }
        return list;
    }
}

