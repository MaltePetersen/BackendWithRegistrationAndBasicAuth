package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.data.UserRepository;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

  @Bean
  public CommandLineRunner dataLoader(        UserRepository userRepo, PasswordEncoder encoder) { // user repo for ease of testing with a built-in user
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        userRepo.save(new User("habuma", encoder.encode("password"),
            "Craig Walls", "123 North Street", "Cross Roads", "TX", 
            "76227", "123-123-1234", "ADMIN"));
        System.out.println(        userRepo.findByUsername("habuma").getAuthorities());

      }
    };
  }
  
}
