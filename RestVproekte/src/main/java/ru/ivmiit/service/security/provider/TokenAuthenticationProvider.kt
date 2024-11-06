package ru.ivmiit.service.security.provider

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import ru.ivmiit.service.repositories.TokensRepository
import ru.ivmiit.service.security.token.TokenAuthentication

@Component
class TokenAuthenticationProvider : AuthenticationProvider {

    @Autowired
    private val tokensRepository: TokensRepository? = null

    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val tokenAuthentication = authentication as TokenAuthentication

        val tokenCandidate = tokensRepository!!.findOneByValue(tokenAuthentication.credentials as String)

        if (tokenCandidate!!.isPresent) {
            val userDetails = userDetailsService!!.loadUserByUsername(tokenCandidate.get().user!!.login)
            tokenAuthentication.setUserDetails(userDetails)
            tokenAuthentication.isAuthenticated = true
            return tokenAuthentication
        } else {
            println("Bad token: " + tokenAuthentication.credentials)
            throw IllegalArgumentException("Bad token")
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return TokenAuthentication::class.java == authentication
    }
}
