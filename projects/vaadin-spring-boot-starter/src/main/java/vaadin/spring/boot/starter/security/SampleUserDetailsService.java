package vaadin.spring.boot.starter.security;

import vaadin.spring.boot.starter.db.SampleEntitySystemUser;
import vaadin.spring.boot.starter.db.SampleRepoSystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SampleUserDetailsService implements UserDetailsService {

    @Autowired
    SampleRepoSystemUser repoUser;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional // evita LazyInitializationException al instanciar roles del usuario
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SampleEntitySystemUser user = repoUser
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        Set<GrantedAuthority> roles = user.getRoles()
                .stream()
                .map(e -> new SimpleGrantedAuthority(e.getRole().name())).collect(Collectors.toSet());
        return new SampleUserDetails(user, roles);
    }
}
