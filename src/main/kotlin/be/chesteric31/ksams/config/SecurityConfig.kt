package be.chesteric31.ksams.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(@Autowired val tokenAuthenticationService: TokenAuthenticationService)
    : WebSecurityConfigurerAdapter() {

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .cors()
                .and()
                .csrf().disable()
                //.csrf().ignoringAntMatchers("/api/login").csrfTokenRepository(withHttpOnlyFalse()).and()
                .authorizeRequests()
                .antMatchers(OPTIONS, "/api/**").permitAll()
                .antMatchers(POST, "/api/login").permitAll()
                .antMatchers(GET, "/api/v2/**").hasAnyRole("ANONYMOUS", "USER", "ADMIN")
                .antMatchers(POST, "/api/v2/**").hasRole("ADMIN")
                .antMatchers(DELETE, "/api/v2/**").hasRole("ADMIN")
                .and()
                .addFilterBefore(
                        JwtLoginFilter(tokenAuthenticationService, "/api/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(
                        JwtAuthenticationFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter::class.java)

    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry?) {
                registry!!
                        .addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .exposedHeaders("authorization", "roles")
                //.allowCredentials(true)
            }
        }
    }

}
