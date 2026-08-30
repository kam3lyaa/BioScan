package br.com.fiap.bioscan.repository

import android.content.Context
import br.com.fiap.bioscan.dao.BioScanDatabase
import br.com.fiap.bioscan.model.User

class RoomUserRepository(context: Context): UserRepository {

    private val userDao = BioScanDatabase.getDatabase(context).userDao()
    override fun saveUser(user: User) {
        userDao.save(user)
    }

    override
    fun getUserById(id: Int): User{
        return userDao.getUserById(1) ?: User()
    }
    override fun getUser(): User {
        return userDao.getUserById(1) ?: User()
    }

    override fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    override fun login(email: String, password: String): Boolean {
        val user = userDao.login(email,password)
        return user != null
    }

}