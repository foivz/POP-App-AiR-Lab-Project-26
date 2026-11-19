package hr.foi.popapp.ws.network

import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.responses.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProductsService {
    @GET("products")
    fun getProducts(@Header("Authorization") authHeader: String): Call<SuccessfulResponseBody<Product>>
}