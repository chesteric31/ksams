package be.chesteric31.ksams.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS512
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Service
import java.util.*
import java.util.Collections.emptyList
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class TokenAuthenticationService {

    val expirationTime: Long = 864000000 // 10 days
    val secret = "ThisIsASecret"
    val tokenPrefix = "Bearer"
    val headerString = "Authorization"

    fun addAuthentication(response: HttpServletResponse, authentication: Authentication) {
        val jwt = Jwts
                .builder()
                .setSubject(authentication.name)
                .setExpiration(Date(System.currentTimeMillis() + expirationTime))
                .signWith(HS512, secret).compact()
        response.addHeader(headerString, "$tokenPrefix $jwt")
        response.addHeader("roles", mapAuthoritiesInRoles(authentication.authorities))
    }

    private fun mapAuthoritiesInRoles(authorities: Collection<GrantedAuthority>): String? {
        return authorities.joinToString()
    }

    fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(headerString)
        if (token != null) {
            val user = parseToken(token)
            return if (user != null) UsernamePasswordAuthenticationToken(user, null, emptyList()) else null
        }
        return null
    }

    private fun parseToken(token: String): String? {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace(tokenPrefix, ""))
                .body
                .subject
    }
}
