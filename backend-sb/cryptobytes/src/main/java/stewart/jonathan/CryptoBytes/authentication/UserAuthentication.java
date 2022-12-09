//package stewart.jonathan.CryptoBytes.authentication;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//import stewart.jonathan.CryptoBytes.model.User;
//import stewart.jonathan.CryptoBytes.service.UserService;
//import stewart.jonathan.CryptoBytes.utilities.PasswordHasher;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class UserAuthentication implements AuthenticationProvider {
//
//    private final UserService userService;
//    private final PasswordHasher passwordHasher;
//
//    @Autowired
//    public UserAuthentication(UserService userService, PasswordHasher passwordHasher) {
//        this.userService = userService;
//        this.passwordHasher = passwordHasher;
//    }
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        User user = userService.findByUsername(username);
//        if (user.getPassword().equals(passwordHasher.encode(password))){
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(user.getRole()));
//            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorities);
//        } else {
//            throw new InsufficientAuthenticationException("Incorrect username or password");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
