package hr.foi.popapp.login_username_password

import hr.foi.popapp.core.LoginToken

class UsernamePasswordLoginToken : LoginToken {

    private val authorizers = emptyMap<String, String>()

    override fun getAuthorizers(): Map<String, String> {
        return authorizers
    }
}