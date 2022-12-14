package stewart.jonathan.CryptoBytes.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//import stewart.jonathan.CryptoBytes.model.User;
//import stewart.jonathan.CryptoBytes.service.TokenService;
//import stewart.jonathan.CryptoBytes.service.UserService;
//
//
//@RestController
//@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:3000")
//public class AuthController {
//
//    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
//    private final UserService userService;
//    private final TokenService tokenService;
//
//    @Autowired
//    public AuthController(UserService userService, TokenService tokenService) {
//        this.userService = userService;
//        this.tokenService = tokenService;
//    }
//
//    @PostMapping("/register")
//    public void registerNewUser(@RequestBody User user) {
//        userService.registerNewUser(user);
//    }
//
//    @PostMapping("/token")
//    public String token(Authentication authentication){
//        LOG.debug("Token requested for user: '{}'", authentication.getName());
//        String token = tokenService.generateToken(authentication);
//        LOG.debug("Token granted: '{}', token");
//        return token;
//    }
//}
