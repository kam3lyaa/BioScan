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
        return plantDao.getPlantByUser(userId)
    }

    override suspend fun getPlantById(plantId: Long): Plant? {
        return plantDao.getPlantById(plantId)
    }

    override suspend fun deletePlant(plant: Plant) {
        plantDao.delete(plant)
    }
}