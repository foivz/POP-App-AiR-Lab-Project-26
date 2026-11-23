package hr.foi.popapp.ws.request_handlers

import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.RegistrationBody
import hr.foi.popapp.ws.models.responses.RegisteredUser
import hr.foi.popapp.ws.network.NetworkService
import retrofit2.Call

class RegistrationRequestHandler(private val requestBody: RegistrationBody) : TemplateRequestHandler<RegisteredUser>() {
    override fun getServiceCall(): Call<SuccessfulResponseBody<RegisteredUser>> {
        val service = NetworkService.authService
        return service.registerUser(requestBody)
    }
}