package br.com.fiap.bioscan.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.bioscan.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun save(user: User): Long

    @Delete
    suspend fun delete(user: User): Int

    @Update
    suspend fun update(user: User): Int

    @Query("SELECT * FROM tb_user WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    @Query("SELECT * FROM tb_user WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM tb_user WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): User?
}