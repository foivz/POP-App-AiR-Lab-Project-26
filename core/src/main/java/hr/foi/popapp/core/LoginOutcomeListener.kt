package hr.foi.popapp.core

interface LoginOutcomeListener {
    fun onSuccessfulLogin(username: String)
    fun onFailedLogin(reason: String)
}