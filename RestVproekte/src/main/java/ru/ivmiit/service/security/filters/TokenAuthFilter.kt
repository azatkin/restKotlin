package ru.ivmiit.service.security.filters

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import ru.ivmiit.service.security.token.TokenAuthentication
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest

class TokenAuthFilter(private val authenticationManager: AuthenticationManager) : Filter {
    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val request = servletRequest as HttpServletRequest

        val authorizationHeader = request.getHeader("Authorization")
        var token: String? = null

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7)
        }

        if (token != null && !token.isEmpty()) {
            val tokenAuthentication = TokenAuthentication(token)
            val authResult = authenticationManager.authenticate(tokenAuthentication)

            if (authResult.isAuthenticated) {
                SecurityContextHolder.getContext().authentication = authResult
                println("Token set in SecurityContext: $token")
            }
        } else {
            println("Authorization header not found or invalid format.")
        }

        filterChain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {
    }
}
