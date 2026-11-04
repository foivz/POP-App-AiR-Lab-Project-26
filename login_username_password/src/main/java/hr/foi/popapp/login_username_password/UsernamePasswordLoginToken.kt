package hr.foi.popapp.login_username_password

import hr.foi.popapp.core.LoginToken

class UsernamePasswordLoginToken(username: String, password: String) : LoginToken {

    private val authorizers = mapOf(
        "username" to username,
        "password" to password,
    )

    override fun getAuthorizers(): Map<String, String> {
        return authorizers
    }
}