package hr.foi.popapp.navigation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import hr.foi.popapp.core.login.Auth
import hr.foi.popapp.core.network.ResponseListener
import hr.foi.popapp.core.network.models.ErrorResponseBody
import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ui.components.ProductCard
import hr.foi.popapp.ws.models.responses.Product
import hr.foi.popapp.ws.request_handlers.ProductsRequestHandler

@Composable
fun ProductPage() {
    val (products, setProducts) = remember { mutableStateOf<List<Product>>(emptyList()) }

    LaunchedEffect(Unit) {
        val productsRequestHandler = ProductsRequestHandler(Auth.loggedInUserData!!.jwt)

        productsRequestHandler.sendRequest(object : ResponseListener<Product> {
            override fun onSuccessfulResponse(response: SuccessfulResponseBody<Product>) {
                setProducts(response.data.toList())
            }

            override fun onErrorResponse(response: ErrorResponseBody) {
                println(response)
            }

            override fun onNetworkFailure(t: Throwable) {
                println("Error contacting network...")
            }
        })
    }

    if (products.isEmpty()) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize()
        )
    } else {
        LazyColumn {
            items(products) {
                ProductCard(product = it)
            }
        }
    }
}
