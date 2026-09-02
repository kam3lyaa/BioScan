package br.com.fiap.bioscan.repository

import br.com.fiap.bioscan.model.User

interface UserRepository {
    suspend fun saveUser(user: User)

    suspend fun getUser(): User

    suspend fun getUserById(id: Int): User?

    suspend fun getUserByEmail(email: String): User?

    suspend fun login(email: String, password: String): Boolean

    suspend fun update(user: User): Int

    suspend fun delete(user: User): Int
}