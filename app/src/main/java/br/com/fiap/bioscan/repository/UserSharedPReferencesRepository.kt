package br.com.fiap.bioscan.repository

import android.content.Context
import br.com.fiap.bioscan.model.User

class UserSharedPreferencesRepository(context: Context) : UserRepository {

    private val userPrefs = context.getSharedPreferences("userPreferences", Context.MODE_PRIVATE)

    override suspend fun saveUser(user: User) {
        userPrefs.edit()
            .putInt("id", user.id)
            .putString("name", user.name)
            .putString("email", user.email)
            .putString("password", user.password)
            .apply()
    }

    override suspend fun getUser(): User {
        val id = userPrefs.getInt("id", 0)
        val name = userPrefs.getString("name", "") ?: ""
        val email = userPrefs.getString("email", "") ?: ""
        val password = userPrefs.getString("password", "") ?: ""
        return User(id = id, name = name, email = email, password = password)
    }

    override suspend fun getUserById(id: Int): User? {
        val savedId = userPrefs.getInt("id", -1)
        return if (savedId == id) getUser() else null
    }

    override suspend fun getUserByEmail(email: String): User? {
        val savedEmail = userPrefs.getString("email", null)
        return if (savedEmail == email) getUser() else null
    }

    override suspend fun login(email: String, password: String): Boolean {
        val savedEmail = userPrefs.getString("email", null)
        val savedPassword = userPrefs.getString("password", null)
        return savedEmail == email && savedPassword == password
    }

    override suspend fun update(user: User): Int {
        saveUser(user)
        return 1
    }

    override suspend fun delete(user: User): Int {
        userPrefs.edit().clear().apply()
        return 1
    }
}