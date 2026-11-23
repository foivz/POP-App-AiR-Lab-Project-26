package hr.foi.popapp.ws.request_handlers

import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.responses.Product
import hr.foi.popapp.ws.network.NetworkService
import retrofit2.Call

class ProductsRequestHandler(private val jwt: String) : TemplateRequestHandler<Product>() {
    override fun getServiceCall(): Call<SuccessfulResponseBody<Product>> {
        val service = NetworkService.productsService
        return service.getProducts("Bearer $jwt")
    }
}