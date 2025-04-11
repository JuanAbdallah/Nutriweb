
package br.com.davesmartins.nutriweb.config.security;

import br.com.davesmartins.nutriweb.config.security.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author daves
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    
    // Configuracoes de recursos estaticos(js, css, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {
	web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/v2/api-docs", "/carousal/**",
                "/webjars/**", "/configuration/**", "/fonts/**", "/images/**", "/styles/**",
		"/fontAwesome/**", "/swagger-resources/**", "/vendor/**", "/scripts/**", "/backgrounds/**",
                "/favicon.ico", "/actuator**", "/actuator/**","/assets/**", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/error**","/home","/","/user","/api/data","/about", "/cadastro").permitAll()
//                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .failureUrl("/login-error")
                .defaultSuccessUrl("/home")
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home")
                .clearAuthentication(true).invalidateHttpSession(true);
                
    }
        
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
