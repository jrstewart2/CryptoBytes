package stewart.jonathan.CryptoBytes.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import stewart.jonathan.CryptoBytes.model.CustomUserDetails;
//import stewart.jonathan.CryptoBytes.repository.UserRepository;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public CustomUserDetailService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       return userRepository
//               .findByUsername(username)
//               .map(CustomUserDetails::new)
//               .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//    }
//}
