package fr.spring.course.tycoursetodolistapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.username}")
    private String username;

    @Value("${security.password}")
    private String password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Returns 403 instead of redirect if login KO
        http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());

        // All APIs required authentication
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // Init default in memory UserService;
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username(username)
                        .password(password)
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
