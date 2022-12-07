package stewart.jonathan.CryptoBytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager user() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("{noop}password")
                        .roles("ADMIN")
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/resources/static/**", "/index", "/").permitAll()
                        .antMatchers("/api/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest()
                        .authenticated()
                ).formLogin().and().httpBasic().and().logout().and()
                //.oauth2Login().and()
                .build();
    }

}
