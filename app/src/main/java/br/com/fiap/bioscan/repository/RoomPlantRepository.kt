package br.com.fiap.bioscan.repository

import android.content.Context
import br.com.fiap.bioscan.dao.BioScanDatabase
import br.com.fiap.bioscan.dao.PlantDao
import br.com.fiap.bioscan.model.Plant

class RoomPlantRepository(context: Context) : PlantRepository {

    private val plantDao: PlantDao =
        BioScanDatabase.getDatabase(context).plantDao()

    override suspend fun savePlant(plant: Plant) {
        plantDao.insert(plant)
    }

    override suspend fun getPlantsByUser(userId: Long): List<Plant> {
        val userPlants = plantDao.getPlantByUser(userId)
        if (userPlants.isNotEmpty()) {
            return userPlants
        }
        // Fallback: se não encontrar pelo ID do usuário, recupera todas as plantas do banco
        return plantDao.getAllPlants()
    }

    override suspend fun getPlantById(plantId: Long): Plant? {
        return plantDao.getPlantById(plantId)
    }

    override suspend fun deletePlant(plant: Plant) {
        plantDao.delete(plant)
    }
}