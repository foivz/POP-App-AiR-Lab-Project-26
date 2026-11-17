package hr.foi.popapp.ws.models.responses

import com.google.gson.annotations.SerializedName

/**
 * The backend sends stores in two shapes:
 *  - nested in login responses: {"store_id", "store_name"}
 *  - from /stores and nested in products: {"id", "store_name", "latitude", "longitude"}
 * `alternate` lets this one model read the id from either.
 */
data class Store(
    @SerializedName(value = "id", alternate = ["store_id"]) var id: Int? = null,
    @SerializedName("store_name") var name: String? = null,
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null
)
