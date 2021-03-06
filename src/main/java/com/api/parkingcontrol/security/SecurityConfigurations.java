package com.api.parkingcontrol.security;

import com.api.parkingcontrol.filtres.AutenticationJWTFilter;
import com.api.parkingcontrol.repositories.UserRepository;
import com.api.parkingcontrol.services.TokenService;
import com.api.parkingcontrol.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@Profile(value = {"test","prod","dev"})
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

   @Autowired
    private UserService userSerivice;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configuracoes de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSerivice).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configuracoes de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
              //.antMatchers(HttpMethod.GET,"/parking-spot").permitAll()
                        .antMatchers("/auth").permitAll()
                        .antMatchers("/users/cadastro").permitAll()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticationJWTFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }


    //Configuracoes de recursos estaticos(js, css, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/**.html",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/configuration/**",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/h2-console/**");


    }
}
