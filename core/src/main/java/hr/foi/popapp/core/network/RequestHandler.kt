package hr.foi.popapp.core.network

interface RequestHandler<T> {
    fun sendRequest(responseListener: ResponseListener<T>)
}