package hr.foi.popapp.core.login

interface LoginOutcomeListener {
    fun onSuccessfulLogin(loginUserData: LoginUserData)
    fun onFailedLogin(reason: String)
}