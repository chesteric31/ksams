package be.chesteric31.ksams.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import java.util.Collections.emptyList
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtLoginFilter(@Autowired val tokenAuthenticationService: TokenAuthenticationService,
                     url: String?,
                     authManager: AuthenticationManager?) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)) {

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest,
                                       response: HttpServletResponse): Authentication {
        val username = request.getParameter("username")
        val password = request.getParameter("password")
        println("JWTLoginFilter.attemptAuthentication: username/password= $username, $password")
        return authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(username, password, emptyList()))
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest?,
                                          response: HttpServletResponse,
                                          chain: FilterChain?,
                                          authResult: Authentication) {
        println("JWTLoginFilter.successfulAuthentication:")
        // Write Authorization to Headers of Response.
        tokenAuthenticationService.addAuthentication(response, authResult)
        val authorizationString = response.getHeader("Authorization")
        println("Authorization String=$authorizationString")
    }

    init {
        authenticationManager = authManager
    }
}
