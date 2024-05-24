package com.example.lab6_20201497.config;

import com.example.lab6_20201497.entity.Usuarios;
import com.example.lab6_20201497.repository.UsuariosRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class WebSecurityConfig {

    final UsuariosRepository usuariosRepository;
    final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource, UsuariosRepository usuariosRepository) {
        this.dataSource = dataSource;
        this.usuariosRepository = usuariosRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/processLogin")
                .successHandler((request, response, authentication) -> {

                    DefaultSavedRequest defaultSavedRequest =
                            (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuariosRepository.findByEmail(authentication.getName()));


                    //si vengo por url -> defaultSR existe
                    if (defaultSavedRequest != null) {
                        String targetURl = defaultSavedRequest.getRequestURL();
                        new DefaultRedirectStrategy().sendRedirect(request, response, targetURl);
                    } else { //estoy viniendo del botón de login
                        String rol = "";
                        for (GrantedAuthority role : authentication.getAuthorities()) {
                            rol = role.getAuthority();
                            break;
                        }

                        if (rol.equals("cliente") || rol.equals("gerente")) {
                            response.sendRedirect("/mesas");
                        } else {
                            response.sendRedirect("/mesas");
                        }
                    }
                });

        http.authorizeHttpRequests()
                .requestMatchers("/mesas", "/mesas/**").hasAnyAuthority("admin", "cliente", "gerente")
                .requestMatchers("/reservas", "/reservas/**").hasAnyAuthority("cliente", "gerente")
                .anyRequest().permitAll();

        http.logout()
                .logoutSuccessUrl("/mesas")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        return http.build();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        String sqlAuth = "SELECT email,password,estado FROM usuarios where email = ?";

        String sqlAuto = "SELECT u.email, r.nombre FROM usuario u " +
                "inner join rol r on u.idRol = r.idRol " +
                "where u.email = ?";

        users.setUsersByUsernameQuery(sqlAuth);
        users.setAuthoritiesByUsernameQuery(sqlAuto);

        return users;
    }
}
