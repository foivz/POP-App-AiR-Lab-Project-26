package hr.foi.popapp.login_username_password

import hr.foi.popapp.core.login.LoginOutcomeListener
import hr.foi.popapp.core.login.LoginUserData
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

/**
 * Integration tests: these hit the real backend and rely on its seed data (data.sql).
 */
class UsernamePasswordLoginHandlerTest {
    @Test
    fun givenCorrectCredentials_whenLoginHandlerUsedWithUsernameAndPassword_thenJwtTokenReceived() {
        val actualUser = ("nalvarez" to "test123")
        val loginToken = UsernamePasswordLoginToken(actualUser.first, actualUser.second)
        val handler = UsernamePasswordLoginHandler()

        val result = CompletableFuture<LoginUserData>()

        handler.handleLogin(loginToken, object : LoginOutcomeListener {
            override fun onSuccessfulLogin(loginUserData: LoginUserData) {
                println(loginUserData.toString())
                result.complete(loginUserData)
            }

            override fun onFailedLogin(reason: String) {
                result.completeExceptionally(Exception(reason))
            }
        })

        val loginUserData = result.get(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        Assert.assertEquals("Nora", loginUserData.firstName)
        Assert.assertTrue(loginUserData.jwt.length > 5)
    }

    @Test
    fun givenBadCredentials_whenLoginHandlerUsedWithUsernameAndPassword_throwError() {
        val actualUser = ("bad username" to "bad password")
        val loginToken = UsernamePasswordLoginToken(actualUser.first, actualUser.second)
        val handler = UsernamePasswordLoginHandler()

        val result = CompletableFuture<String>()

        handler.handleLogin(loginToken, object : LoginOutcomeListener {
            override fun onSuccessfulLogin(loginUserData: LoginUserData) {
                result.completeExceptionally(Exception("User logged in despite the odds."))
            }

            override fun onFailedLogin(reason: String) {
                result.complete(reason)
            }
        })

        val reasonForFailure = result.get(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        Assert.assertEquals("Please check your credentials!", reasonForFailure)
    }

    private companion object {
        const val TIMEOUT_SECONDS = 15L
    }
}
