package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web
                        .configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
                        .configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation
             .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web
             .builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
            //HTTP Basic authentication
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/register/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
            .and()
            .csrf().disable()
            .formLogin().disable();
  }
  @Bean
  public PasswordEncoder encoder() {
    return new StandardPasswordEncoder("53cr3t");
  }
  
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {

    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(encoder());
    
  }

}
