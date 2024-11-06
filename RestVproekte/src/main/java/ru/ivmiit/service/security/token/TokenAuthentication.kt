package ru.ivmiit.service.security.token

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class TokenAuthentication(
    private val token: String,
    private var userDetails: UserDetails? = null,
    private var authenticated: Boolean = false
) : Authentication {

    fun setUserDetails(userDetails: UserDetails) {
        this.userDetails = userDetails
    }

    override fun getAuthorities(): Collection<GrantedAuthority>? {
        return userDetails?.authorities
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.authenticated = isAuthenticated
    }

    override fun getCredentials(): String {
        return token
    }

    override fun getDetails(): Any? {
        return userDetails
    }

    override fun getPrincipal(): UserDetails? {
        return userDetails
    }

    override fun isAuthenticated(): Boolean {
        return authenticated
    }

    override fun getName(): String? {
        return userDetails?.username
    }
}
