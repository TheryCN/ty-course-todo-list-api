package fr.spring.course.tycoursetodolistapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("#{'${security.usernames}'.split(',')}")
    private List<String> usernameList;

    @Value("${security.default.password}")
    private String defaultPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Returns 403 instead of redirect if login KO
        http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());

        // All APIs required authentication
        http.authorizeRequests()
                .antMatchers("/ui/tasks").permitAll()
                .antMatchers("/tasks").permitAll()
                .anyRequest().hasRole(RoleType.ADMIN.name())
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryUserDetailsManagerConfigurer = auth.inMemoryAuthentication();

        // Set user details service manually
        // auth.userDetailsService(myUserDetailsService);

        inMemoryUserDetailsManagerConfigurer
                .withUser("Thery")
                .password(passwordEncoder().encode(defaultPassword)).roles(RoleType.ADMIN.name());

        usernameList.forEach(username -> inMemoryUserDetailsManagerConfigurer
                .withUser(username)
                .password(passwordEncoder().encode(defaultPassword)).roles(RoleType.USER.name()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
