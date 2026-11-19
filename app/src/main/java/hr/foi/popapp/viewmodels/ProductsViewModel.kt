package hr.foi.popapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.foi.popapp.core.login.Auth
import hr.foi.popapp.core.network.ResponseListener
import hr.foi.popapp.core.network.models.ErrorResponseBody
import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.responses.Product
import hr.foi.popapp.ws.request_handlers.ProductsRequestHandler

class ProductsViewModel : ViewModel() {
    private val _products: MutableLiveData<List<Product>> = MutableLiveData(mutableListOf())
        val products: LiveData<List<Product>> = _products

    init {
        val productsRequestHandler = ProductsRequestHandler(Auth.loggedInUserData!!.jwt)

        productsRequestHandler.sendRequest(object : ResponseListener<Product> {
            override fun onSuccessfulResponse(response: SuccessfulResponseBody<Product>) {
                val productsArray = response.data
                _products.value = productsArray.toMutableList()
            }

            override fun onErrorResponse(response: ErrorResponseBody) {
                println("Error receiving response: ${response.error_message}")
            }

            override fun onNetworkFailure(t: Throwable) {
                println("Error contacting network...")
            }
        })
    }
}