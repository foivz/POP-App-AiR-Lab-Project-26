package hr.foi.popapp.ws.network

import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.RegisteredUser
import hr.foi.popapp.ws.models.RegistrationBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("auth/register")
    fun registerUser(@Body registrationBody: RegistrationBody): Call<SuccessfulResponseBody<RegisteredUser>>
}
