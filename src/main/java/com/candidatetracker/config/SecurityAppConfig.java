package com.candidatetracker.config;

import com.candidatetracker.entity.Role;
import com.candidatetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityAppConfig extends WebSecurityConfigurerAdapter
{
  // add a reference to our security data source
  @Autowired
  private UserService userService;

  @Autowired
  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.authorizeRequests()
        .antMatchers("/css/**").permitAll()
        .antMatchers("/", "/candidate/list/**", "/candidate/showAddForm/**")
          .hasRole(Role.RoleType.ROLE_EMPLOYEE.shortName())
        .antMatchers("/candidate/showUpdateForm/**")
          .hasAnyRole(Role.RoleType.ROLE_MANAGER.shortName(),
              Role.RoleType.ROLE_ADMIN.shortName())
        .antMatchers("/candidate/deleteCandidate/**")
          .hasRole(Role.RoleType.ROLE_ADMIN.shortName())
        .antMatchers("/actuator/**").hasRole(Role.RoleType.ROLE_ADMIN.shortName())

        .antMatchers(HttpMethod.GET, "/api/candidates")
          .hasRole(Role.RoleType.ROLE_EMPLOYEE.shortName())
        .antMatchers(HttpMethod.GET, "/api/candidates/**")
          .hasRole(Role.RoleType.ROLE_EMPLOYEE.shortName())
        .antMatchers(HttpMethod.POST, "/api/candidates")
          .hasAnyRole(Role.RoleType.ROLE_MANAGER.shortName(), Role.RoleType.ROLE_ADMIN.shortName())
        .antMatchers(HttpMethod.POST, "/api/candidates/**")
          .hasAnyRole(Role.RoleType.ROLE_MANAGER.shortName(), Role.RoleType.ROLE_ADMIN.shortName())
        .antMatchers(HttpMethod.PUT, "/api/candidates")
          .hasAnyRole(Role.RoleType.ROLE_MANAGER.shortName(), Role.RoleType.ROLE_ADMIN.shortName())
        .antMatchers(HttpMethod.PUT, "/api/candidates/**")
          .hasAnyRole(Role.RoleType.ROLE_MANAGER.shortName(), Role.RoleType.ROLE_ADMIN.shortName())
        .antMatchers(HttpMethod.DELETE, "/api/candidates/**")
          .hasRole(Role.RoleType.ROLE_ADMIN.shortName())
        // secure everything except API with crsf
        .and().csrf().ignoringAntMatchers("/api/**")

        .and()
        .formLogin().loginPage("/login")
        .loginProcessingUrl("/authUser")
        // .defaultSuccessUrl("/", false)
        .successHandler(customAuthenticationSuccessHandler)
        .permitAll()
        .and()
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/access-denied");
  }

  // bcrypt bean definition
  @Bean
  public BCryptPasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder();
  }

  // authenticationProvider bean definition
  @Bean
  public DaoAuthenticationProvider authenticationProvider()
  {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(userService); // set the custom user details service
    auth.setPasswordEncoder(passwordEncoder()); // set the password encoder - bcrypt
    return auth;
  }
}
