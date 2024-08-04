package dev.jonnycoddington.vaadintest.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import dev.jonnycoddington.vaadintest.views.LoginView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
@Configuration
public class SecurityConfig extends VaadinWebSecurity {
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    super.configure(http);
    setLoginView(http, LoginView.class);
  }
  
  @Bean
  public UserDetailsService users() {
    var user = User.builder()
        .username("user")
        // super secret password - implement proper hashing for production
        .password("{noop}password")
        .roles(Roles.USER)
        .build();
    
    return new InMemoryUserDetailsManager(user);
  }
}
