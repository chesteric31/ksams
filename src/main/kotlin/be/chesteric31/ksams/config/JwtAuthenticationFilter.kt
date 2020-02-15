package be.chesteric31.ksams.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtAuthenticationFilter(@Autowired
                              val tokenAuthenticationService: TokenAuthenticationService) : GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest,
                          servletResponse: ServletResponse,
                          filterChain: FilterChain) {
        val authentication: Authentication? = tokenAuthenticationService.getAuthentication((servletRequest as HttpServletRequest))
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(servletRequest, servletResponse)
    }
}
