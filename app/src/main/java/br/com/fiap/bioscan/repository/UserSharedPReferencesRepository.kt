package br.com.fiap.bioscan.repository

import android.content.Context
import br.com.fiap.bioscan.model.User

class UserSharedPreferencesRepository(context: Context): UserRepository {

    private val userPrefs = context
        .getSharedPreferences("userPreferences", Context.MODE_PRIVATE)

    override
    fun saveUser(user: User) {
       userPrefs.edit()
           .putInt("id", user.id)
           .putString("name", user.name)
           .putString("email", user.email)
           .putString("password", user.password)
           .apply()
    }

    override
    fun getUser(): User {
        val id = userPrefs.getInt("id", 0)
        val name = userPrefs.getString("name", "")
        val email = userPrefs.getString("email", "")
        val password = userPrefs.getString("password", "")

        return User(id, name!!, email!!, password!!)
    }

    override fun getUserById(id: Int): User? {
        TODO("Not yet implemented")
    }


    override
    fun getUserByEmail(email: String): User? {
        TODO("Not yet implemented")
    }

    override
    fun login(email: String, password: String): Boolean {

        val  emailPrefs = userPrefs.getString("email", "")
        val passwordPrefs = userPrefs.getString("password", "")

        return emailPrefs == email && passwordPrefs == password
    }


}