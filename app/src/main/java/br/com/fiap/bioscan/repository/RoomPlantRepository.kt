package br.com.fiap.bioscan.repository

import android.content.Context
import br.com.fiap.bioscan.dao.BioScanDatabase
import br.com.fiap.bioscan.dao.PlantDao
import br.com.fiap.bioscan.model.Plant

class RoomPlantRepository(context: Context): PlantRepository{

    private val plantDao: PlantDao =
        BioScanDatabase.getDatabase(context).plantDao()

    override
    fun savePlant(plant: Plant) {
        plantDao.insert(plant)
    }

    override
    fun getPlantsByUser(userId: Long): List<Plant> {
        return plantDao.getPlantByUser(userId)
    }

    override
    fun getPlantById(plantId: Long): Plant? {
        return  plantDao.getPlantById(plantId)
    }

    override
    fun deletePlant(plant: Plant) {
        plantDao.delete(plant)
    }

}