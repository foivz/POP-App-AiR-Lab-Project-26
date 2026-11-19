package hr.foi.popapp.ws.request_handlers

import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.responses.Store
import hr.foi.popapp.ws.network.NetworkService
import retrofit2.Call

class GetStoresRequestHandler(private val jwt: String) : TemplateRequestHandler<Store>() {
    override fun getServiceCall(): Call<SuccessfulResponseBody<Store>> {
        val service = NetworkService.storesService
        return service.getAllStores("Bearer $jwt")
    }
}
