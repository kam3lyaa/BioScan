package br.com.fiap.bioscan.repository

import br.com.fiap.bioscan.model.User

interface UserRepository {
    fun saveUser(user: User)

    fun getUser(): User

    fun getUserById(id: Int): User?

    fun getUserByEmail(email: String): User?

    fun login(email: String, password: String): Boolean
}