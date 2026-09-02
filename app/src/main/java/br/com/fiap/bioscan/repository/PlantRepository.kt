package br.com.fiap.bioscan.repository

import br.com.fiap.bioscan.model.Plant

interface PlantRepository {
    suspend fun savePlant(plant: Plant)
    suspend fun getPlantsByUser(userId: Long): List<Plant>
    suspend fun getPlantById(plantId: Long): Plant?
    suspend fun deletePlant(plant: Plant)
}