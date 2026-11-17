package hr.foi.popapp.ws.request_handlers

import com.google.gson.Gson
import hr.foi.popapp.core.network.RequestHandler
import hr.foi.popapp.core.network.ResponseListener
import hr.foi.popapp.core.network.models.ErrorResponseBody
import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.RegistrationBody
import hr.foi.popapp.ws.models.responses.RegisteredUser
import hr.foi.popapp.ws.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationRequestHandler(private val requestBody: RegistrationBody) : RequestHandler {
    override fun sendRequest(responseListener: ResponseListener) {
        val service = NetworkService.authService
        val serviceCall = service.registerUser(requestBody)

        serviceCall.enqueue(object : Callback<SuccessfulResponseBody<RegisteredUser>> {
            override fun onResponse(call: Call<SuccessfulResponseBody<RegisteredUser>>, response: Response<SuccessfulResponseBody<RegisteredUser>>) {

                if (response.isSuccessful) {
                    responseListener.onSuccessfulResponse(response.body()!!)
                } else {
                    val errorResponse = Gson().fromJson(response.errorBody()?.string(), ErrorResponseBody::class.java)
                    responseListener.onErrorResponse(errorResponse)
                }

            }

            override fun onFailure(call: Call<SuccessfulResponseBody<RegisteredUser>>, t: Throwable) {
                responseListener.onNetworkFailure(t)
            }
        })
    }
}