package org.test.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.test.domain.StdAdmin
import java.time.Instant
import java.util.Date


class TokenProvider {
    fun createToken(stdAdminEntity: StdAdmin, expMinutes: Int, tokenType: String): String {
        return JWT.create()
            .withSubject("member") //
            .withAudience(stdAdminEntity.id.toString())
            .withIssuedAt(Date.from(Instant.now()))
            // .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
            .withExpiresAt(Date(System.currentTimeMillis() + 60000 * expMinutes))
            .withClaim("id", stdAdminEntity.id)
//            .withClaim("username", memberEntity.getUsername())
            .withClaim("tokenType", tokenType)
            .sign(ALGORITHM)
    }

    fun createTokens(stdAdmin: StdAdmin): String {
        val accessToken = createToken(stdAdmin, ACCESS_TOKEN_EXPIRE_MINUTES, "accessToken")
        val refreshToken = createToken(stdAdmin, REFRESH_TOKEN_EXPIRE_MINUTES, "refreshToken")

        return accessToken
    }


    fun checkToken(token: String) {
        val verify = JWT_VERIFIER.verify(token)
        println(verify.getClaim("id"))
    }

    companion object {
        private const val SECRET = "javalin"
        private val ALGORITHM = Algorithm.HMAC512(SECRET)
        private val JWT_VERIFIER = JWT.require(ALGORITHM).build()
        private const val ACCESS_TOKEN_EXPIRE_MINUTES = 10
        private const val REFRESH_TOKEN_EXPIRE_MINUTES = 10
    }

}