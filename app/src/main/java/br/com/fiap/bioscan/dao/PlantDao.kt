package br.com.fiap.bioscan.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.bioscan.model.Plant

@Dao
interface PlantDao {

    @Insert
    suspend fun insert(plant: Plant)

    @Query("SELECT * FROM tb_plant WHERE user_id = :userId")
    suspend fun getPlantByUser(userId: Long): List<Plant>

    @Query("SELECT * FROM tb_plant WHERE id = :plantId")
    suspend fun getPlantById(plantId: Long): Plant?

    @Delete
    suspend fun delete(plant: Plant)
}