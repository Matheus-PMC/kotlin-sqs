package com.kotlin.sqs.configuration


import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.stereotype.Component
import java.util.stream.Collectors
import java.util.stream.Stream


@Component
class JwtAuthConfiguration : Converter<Jwt, AbstractAuthenticationToken> {
    private val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()
    override fun convert(jwt: Jwt): AbstractAuthenticationToken? {
        val authorities = Stream.concat(
            jwtGrantedAuthoritiesConverter.convert(jwt)?.stream()!!,
            extractResourceRoles(jwt).stream()).collect(Collectors.toSet())

        return JwtAuthenticationToken(jwt, authorities, "preferred_username")
    }

    private fun extractResourceRoles(jwt: Jwt): Collection<GrantedAuthority> {
        val resourceAccess = jwt.getClaimAsMap("realm_access")
        val resource = (resourceAccess["roles"] as? Collection<*>)?.takeIf { it.isNotEmpty() } ?: return emptyList()

        return resource.map { SimpleGrantedAuthority("ROLE_$it") }.toSet()
    }
}