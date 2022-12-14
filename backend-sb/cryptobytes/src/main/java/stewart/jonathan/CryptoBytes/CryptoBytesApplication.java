package stewart.jonathan.CryptoBytes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import stewart.jonathan.CryptoBytes.config.RsaKeyProperties;


@SpringBootApplication
//@EnableConfigurationProperties(RsaKeyProperties.class)
public class CryptoBytesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoBytesApplication.class, args);
	}


}
