package hr.foi.popapp.ws.request_handlers

import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.LoggedInUserData
import hr.foi.popapp.ws.models.LoginBody
import hr.foi.popapp.ws.network.NetworkService
import retrofit2.Call

class LoginRequestHandler(private val requestBody: LoginBody) : TemplateRequestHandler<LoggedInUserData>() {
    override fun getServiceCall(): Call<SuccessfulResponseBody<LoggedInUserData>> {
        val service = NetworkService.authService
        return service.loginUser(requestBody)
    }
}