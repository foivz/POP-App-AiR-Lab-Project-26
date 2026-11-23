package hr.foi.popapp.navigation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.foi.popapp.ui.components.ProductCard
import hr.foi.popapp.ws.models.responses.Product
import hr.foi.popapp.viewmodels.ProductsViewModel

@Composable
fun ProductPage(viewModel: ProductsViewModel = viewModel()) {
    val products by viewModel.products.observeAsState(initial = emptyList())

    if (products.isEmpty()) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize()
        )
    } else {
        LazyColumn {
            items(products) { product ->
                ProductCard(product = product)
            }
        }
    }
}
