package hr.foi.popapp.core

interface LoginToken {
    fun getAuthorizers(): Map<String, String>
}