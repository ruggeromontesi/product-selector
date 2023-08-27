package seb.homework.config.product.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(requests ->
                  requests.requestMatchers(
                              antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/form")).hasRole("USER")
                        .requestMatchers(antMatcher(HttpMethod.POST, "/product/**")).permitAll()
                        .anyRequest().authenticated()
            )
            .formLogin(form -> form.loginProcessingUrl("/login"))
            .httpBasic(Customizer.withDefaults());

      return http.build();
   }

   @Bean
   public UserDetailsService userDetailsService() {
      UserDetails user =
            User.withDefaultPasswordEncoder()
                  .username("ruggero")
                  .password("montesi")
                  .roles("USER")
                  .build();

      return new InMemoryUserDetailsManager(user);
   }
}
