package hr.foi.popapp.network

import hr.foi.popapp.network.models.RegistrationBody
import hr.foi.popapp.network.models.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("auth/register")
    fun registerUser(@Body registrationBody: RegistrationBody): Call<ResponseBody>
}
