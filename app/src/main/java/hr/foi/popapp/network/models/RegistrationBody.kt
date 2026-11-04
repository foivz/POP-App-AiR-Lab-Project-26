package hr.foi.popapp.network.models

// Property names match the JSON keys the API expects.
data class RegistrationBody(
    val first_name: String,
    val last_name: String,
    val username: String,
    val email: String,
    val password: String,
    val role: String,
)
