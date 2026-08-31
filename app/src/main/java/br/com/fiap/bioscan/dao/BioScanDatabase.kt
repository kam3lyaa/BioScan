package br.com.fiap.bioscan.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.bioscan.model.Plant
import br.com.fiap.bioscan.model.User

@Database(
    entities =  [
        User::class,
        Plant::class
                ],
    version = 2
)
abstract class BioScanDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun plantDao(): PlantDao

    companion object{
        private lateinit var instance: BioScanDatabase

        fun getDatabase(context: Context): BioScanDatabase{
            if(!::instance.isInitialized){
                instance = Room.databaseBuilder(
                    context,
                    BioScanDatabase::class.java,
                    "bioscan_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}