package click.pranjalonline.bakibohibackend;
import org.modelmapper.ModelMapper;
import click.pranjalonline.bakibohibackend.main.utils.FileUploadService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootApplication
public class BakibohibackendApplication {
	@Resource
	FileUploadService filesUploadService;
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();};
	public static void main(String[] args) {
		SpringApplication.run(BakibohibackendApplication.class, args);
	}


}
