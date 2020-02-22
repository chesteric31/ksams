package be.chesteric31.ksams.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS512
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Collectors.joining
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Service
class TokenAuthenticationService {

    val expirationTime: Long = 864000000 // 10 days
    val secret = "ThisIsASecret"
    val tokenPrefix = "Bearer"
    val headerString = "Authorization"
    val roles = "roles"

    fun addAuthentication(response: HttpServletResponse, authentication: Authentication) {
        val authorities = authentication.authorities.stream()
                .map { it.authority }
                .collect(joining(","));
        val jwt = Jwts
                .builder()
                .setSubject(authentication.name)
                .claim(roles, authorities)
                .setExpiration(Date(System.currentTimeMillis() + expirationTime))
                .signWith(HS512, secret).compact()
        response.addHeader(headerString, "$tokenPrefix $jwt")
        response.addHeader(roles, authorities)
    }

    fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(headerString)
        if (token != null) {
            val user = parseTokenInUser(token)
            val claims = parseTokenInClaims(token)
            if (user != null)  {
                return UsernamePasswordAuthenticationToken(user, null, claims?.let { mapRolesInAuthorizations(it) })
            }
            return null
        }
        return null
    }

    private fun mapRolesInAuthorizations(claims: Claims): MutableCollection<out GrantedAuthority>? {
        return Arrays.stream(claims[roles].toString().split(",").toTypedArray())
                .map { role: String? -> SimpleGrantedAuthority(role) }
                .collect(Collectors.toList())
    }

    private fun parseTokenInClaims(token: String): Claims? {
        return parseToken(token)
                ?.body
    }

    private fun parseTokenInUser(token: String): String? {
        return parseToken(token)
                ?.body
                ?.subject
    }

    private fun parseToken(token: String): Jws<Claims>? {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace(tokenPrefix, ""))
    }
}
