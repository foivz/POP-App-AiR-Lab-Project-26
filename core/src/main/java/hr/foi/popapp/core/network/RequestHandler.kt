package hr.foi.popapp.core.network

interface RequestHandler {
    fun sendRequest(responseListener: ResponseListener)
}