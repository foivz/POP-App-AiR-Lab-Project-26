package hr.foi.popapp.login_username_password

import hr.foi.popapp.core.login.LoginHandler
import hr.foi.popapp.core.login.LoginOutcomeListener
import hr.foi.popapp.core.login.LoginToken
import hr.foi.popapp.core.login.LoginUserData
import hr.foi.popapp.core.network.ResponseListener
import hr.foi.popapp.core.network.models.ErrorResponseBody
import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.LoggedInUserData
import hr.foi.popapp.ws.models.LoginBody
import hr.foi.popapp.ws.request_handlers.LoginRequestHandler

class UsernamePasswordLoginHandler : LoginHandler {
    override fun handleLogin(
        loginToken: LoginToken,
        loginListener: LoginOutcomeListener
    ) {
        if (loginToken !is UsernamePasswordLoginToken) {
            throw IllegalArgumentException("Must receive UsernamePasswordLoginToken instance for 'loginToken'!")
        }

        val authorizers = loginToken.getAuthorizers()
        val username = authorizers["username"]!!
        val password = authorizers["password"]!!

        val loginRequestHandler = LoginRequestHandler(LoginBody(username, password))

        loginRequestHandler.sendRequest(
            object : ResponseListener {
                override fun <T> onSuccessfulResponse(response: SuccessfulResponseBody<T>) {
                    val loginUserData = response.data[0] as LoggedInUserData

                    loginListener.onSuccessfulLogin(
                        LoginUserData(
                            userId = loginUserData.id!!,
                            username = loginUserData.username!!,
                            firstName = loginUserData.firstName!!,
                            lastName = loginUserData.lastName!!,
                            role = loginUserData.role!!,
                            isAccepted = loginUserData.isAccepted!!,
                            storeId = loginUserData.store?.id,
                            storeName = loginUserData.store?.name,
                            jwt = loginUserData.tokenPair!!.accessToken!!,
                            refreshToken = loginUserData.tokenPair!!.refreshToken.token!!,
                        )
                    )
                }

                override fun onErrorResponse(response: ErrorResponseBody) {
                    loginListener.onFailedLogin(response.message)
                }

                override fun onNetworkFailure(t: Throwable) {
                    loginListener.onFailedLogin(t.message ?: "Could not connect to network.")
                }

            }
        )
    }
}
