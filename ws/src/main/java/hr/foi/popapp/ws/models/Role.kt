package hr.foi.popapp.ws.models

import androidx.compose.ui.graphics.vector.ImageVector

data class Role(
    val displayName: String,
    val icon: ImageVector,
    val apiValue: String
)
