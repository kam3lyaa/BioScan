package br.com.fiap.bioscan.repository

import br.com.fiap.bioscan.model.Plant

interface PlantRepository {

    fun savePlant(plant:Plant)

    fun getPlantsByUser(userId: Long): List<Plant>

    fun getPlantById(plantId: Long): Plant?

    fun deletePlant(plant: Plant)
}