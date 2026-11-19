package hr.foi.popapp.ws.request_handlers

import com.google.gson.Gson
import hr.foi.popapp.core.network.RequestHandler
import hr.foi.popapp.core.network.ResponseListener
import hr.foi.popapp.core.network.models.ErrorResponseBody
import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.LoggedInUserData
import hr.foi.popapp.ws.models.LoginBody
import hr.foi.popapp.ws.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRequestHandler(private val requestBody: LoginBody) : RequestHandler<LoggedInUserData> {
    override fun sendRequest(responseListener: ResponseListener<LoggedInUserData>) {
        val service = NetworkService.authService
        val serviceCall = service.loginUser(requestBody)

        serviceCall.enqueue(object : Callback<SuccessfulResponseBody<LoggedInUserData>> {

            override fun onResponse(
                call: Call<SuccessfulResponseBody<LoggedInUserData>>,
                response: Response<SuccessfulResponseBody<LoggedInUserData>>
            ) {
                if (response.isSuccessful) {
                    responseListener.onSuccessfulResponse(response.body()!!)
                } else {
                    val errorResponse = Gson().fromJson(response.errorBody()?.string(), ErrorResponseBody::class.java)
                    responseListener.onErrorResponse(errorResponse)
                }
            }

            override fun onFailure(call: Call<SuccessfulResponseBody<LoggedInUserData>>, t: Throwable) {
                responseListener.onNetworkFailure(t)
            }

        })
    }
}