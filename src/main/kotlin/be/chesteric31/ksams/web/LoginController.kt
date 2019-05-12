package be.chesteric31.ksams.web

import com.sun.security.auth.UserPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/login")
class LoginController {

    @PostMapping(value = ["/"])
    fun login(@RequestBody login: Login): Login {
        when {
            "admin" == login.username && "admin" == login.password -> return Login("admin", "admin", arrayOf("ADMIN", "USER"))
        }
        when {
            "user" == login.username && "user" == login.password -> return Login("user", "user", arrayOf("USER"))
        }
        throw Exception("No User found!")
    }

    @RequestMapping("/user")
    fun user(request: HttpServletRequest): Principal {
        val authToken = request.getHeader("Authorization").substring("Basic".length).trim { it <= ' ' }
        return UserPrincipal    (String(Base64.getDecoder().decode(authToken)).split(":")[0])
    }

}
