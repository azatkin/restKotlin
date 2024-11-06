package ru.ivmiit.service.security.details

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.ivmiit.service.repositories.UsersRepository

@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    private val usersRepository: UsersRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(login: String): UserDetails {
        val user = usersRepository!!.findByLogin(login)
            .orElseThrow {
                UsernameNotFoundException(
                    "User not found with login: $login"
                )
            }
        return UserDetailsImpl(user)
    }
}
