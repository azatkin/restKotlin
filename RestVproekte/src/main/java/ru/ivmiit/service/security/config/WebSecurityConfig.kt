package ru.ivmiit.service.security.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import ru.ivmiit.service.security.filters.TokenAuthFilter
import ru.ivmiit.service.security.provider.TokenAuthenticationProvider

@ComponentScan("ru.ivmiit")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Включение @PreAuthorize
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private val authenticationProvider: TokenAuthenticationProvider? = null

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider)
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    @Throws(Exception::class)
    open fun tokenAuthFilter(): TokenAuthFilter {
        return TokenAuthFilter(authenticationManagerBean())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .addFilterBefore(tokenAuthFilter(), BasicAuthenticationFilter::class.java)
            .authenticationProvider(authenticationProvider)
            .authorizeRequests()
            .antMatchers("/login", "/registration", "/requests").permitAll() // Доступ к созданию заявки для всех
            .antMatchers("/admin/requests/**")
            .hasAuthority("ADMIN") // Доступ к управлению заявками только для администратора
            .antMatchers("/admin/**")
            .hasAuthority("ADMIN") // Доступ к управлению пользователями только для администратора
            .antMatchers("/profile")
            .hasAnyAuthority("USER", "ADMIN") // Доступ к профилю для пользователей и администраторов
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
    }
}
