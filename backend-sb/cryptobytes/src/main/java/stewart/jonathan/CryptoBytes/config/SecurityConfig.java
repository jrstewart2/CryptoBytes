package stewart.jonathan.CryptoBytes.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import stewart.jonathan.CryptoBytes.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final RsaKeyProperties rsaKeys;
    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public SecurityConfig(RsaKeyProperties rsaKeys, CustomUserDetailService customUserDetailService) {
        this.rsaKeys = rsaKeys;
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors().and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
//                        .antMatchers("/api/admin").hasRole("ADMIN")
//                        .antMatchers("/api/portfolio/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers("/api/users").hasAnyRole("USER", "ADMIN")
                        .antMatchers("/api/auth/**").permitAll()
                        .antMatchers("/resources/static/**","/index", "/", "/home/**").permitAll()
                        //.antMatchers("/test/**").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(customUserDetailService)
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .formLogin()
                .and()
                .httpBasic(Customizer.withDefaults())
                //.logout().and()
                .oauth2Login().and()
                .headers(headers -> headers.frameOptions().sameOrigin())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
