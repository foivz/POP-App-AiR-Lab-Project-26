package hr.foi.popapp.ws.request_handlers

import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.NewStoreBody
import hr.foi.popapp.ws.models.responses.Store
import hr.foi.popapp.ws.network.NetworkService
import retrofit2.Call

class CreateStoreRequestHandler(private val jwt: String, private val body: NewStoreBody) :
    TemplateRequestHandler<Store>() {
    override fun getServiceCall(): Call<SuccessfulResponseBody<Store>> {
        val service = NetworkService.storesService
        return service.createStore("Bearer $jwt", body)
    }
}