package br.com.fiap.bioscan.repository

import android.content.Context
import br.com.fiap.bioscan.dao.BioScanDatabase
import br.com.fiap.bioscan.model.User

class RoomUserRepository(context: Context) : UserRepository {

    private val userDao = BioScanDatabase.getDatabase(context).userDao()

    override suspend fun saveUser(user: User) {
        userDao.save(user)
    }

    override suspend fun getUserById(id: Int): User {
        return userDao.getUserById(id) ?: User()
    }

    override suspend fun getUser(): User {
        return userDao.getUserById(1) ?: User()
    }

    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    override suspend fun login(email: String, password: String): Boolean {
        val user = userDao.login(email, password)
        return user != null
    }

    override suspend fun update(user: User): Int {
        return userDao.update(user)
    }

    override suspend fun delete(user: User): Int {
        return userDao.delete(user)
    }
}