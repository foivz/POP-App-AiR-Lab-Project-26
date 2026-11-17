package hr.foi.popapp.core.login

interface LoginToken {
    fun getAuthorizers(): Map<String, String>
}