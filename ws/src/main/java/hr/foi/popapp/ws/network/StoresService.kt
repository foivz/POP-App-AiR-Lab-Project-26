package hr.foi.popapp.ws.network

import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.NewStoreBody
import hr.foi.popapp.ws.models.responses.Store
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface StoresService {
    @GET("stores")
    fun getAllStores(@Header("Authorization") authHeader: String): Call<SuccessfulResponseBody<Store>>

    @POST("stores")
    fun createStore(
        @Header("Authorization") authHeader: String,
        @Body body: NewStoreBody
    ): Call<SuccessfulResponseBody<Store>>
}