package hr.foi.popapp.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person3
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class Role(
    val displayName: String,
    val icon: ImageVector,
    val apiValue: String
)

val availableRoles = listOf(
    Role("Buyer", Icons.Default.Person, "buyer"),
    Role("Seller", Icons.Default.ShoppingCart, "seller")
)