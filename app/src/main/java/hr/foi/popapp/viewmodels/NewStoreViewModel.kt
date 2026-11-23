package hr.foi.popapp.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.foi.popapp.core.login.Auth
import hr.foi.popapp.core.network.ResponseListener
import hr.foi.popapp.core.network.models.ErrorResponseBody
import hr.foi.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.popapp.ws.models.NewStoreBody
import hr.foi.popapp.ws.models.responses.Store
import hr.foi.popapp.ws.request_handlers.CreateStoreRequestHandler

class NewStoreViewModel : ViewModel() {
    val _storeName: MutableLiveData<String> = MutableLiveData("")
    private val _errorReason: MutableLiveData<String> = MutableLiveData("")
    private val _storeLatitude: MutableLiveData<Double> = MutableLiveData(0.0)
    private val _storeLongitude: MutableLiveData<Double> = MutableLiveData(0.0)

    val storeName: LiveData<String> = _storeName
    val errorReason: LiveData<String> = _errorReason

    fun setStoreName(newName: String) {
        _storeName.value = newName
    }

    fun setStoreLocation(latitude: Double, longitude: Double) {
        _storeLatitude.value = latitude
        _storeLongitude.value = longitude
    }

    fun createStore(onSuccessfulResponse: () -> Unit) {
        val requestHandler = CreateStoreRequestHandler(
            Auth.loggedInUserData!!.jwt,
            NewStoreBody(storeName.value!!, _storeLatitude.value!!, _storeLongitude.value!!)
        )
        requestHandler.sendRequest(
            object : ResponseListener<Store> {
                override fun onSuccessfulResponse(response: SuccessfulResponseBody<Store>) {
                    onSuccessfulResponse()
                }

                override fun onErrorResponse(response: ErrorResponseBody) {
                    _errorReason.value = response.message
                }

                override fun onNetworkFailure(t: Throwable) {
                    _errorReason.value = "Network error"
                }
            }
        )
    }
}