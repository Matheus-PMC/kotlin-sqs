package com.kotlin.sqs.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(private val jwtAuthConfiguration: JwtAuthConfiguration) {
    companion object {
        const val ADMIN = "admin"
        const val USER = "uma_authorization"
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf {
                it.ignoringRequestMatchers("/user/login")
                it.ignoringRequestMatchers("/user/register")
                it.ignoringRequestMatchers("/user/updated/{id}")

            }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/user/**").permitAll()
                    .requestMatchers("/admin/**").hasAnyRole(ADMIN)
                    .requestMatchers("/message/**").hasAnyRole(USER)
                    .anyRequest().authenticated()
            }


        http.oauth2ResourceServer { oauth2 ->
            oauth2.jwt { jwt ->
                jwt.jwtAuthenticationConverter(jwtAuthConfiguration)
                http.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            }
        }
        return http.build()
    }
}