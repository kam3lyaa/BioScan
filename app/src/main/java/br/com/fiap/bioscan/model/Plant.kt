package br.com.fiap.bioscan.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tb_plant",
    foreignKeys = [
        ForeignKey(
            entity= User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["user_id"])]
)
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name= "user_id")
    val userId: Long,

    @ColumnInfo(name= "scientific_name")
    val scientificName: String,

    @ColumnInfo(name= "common_name")
    val commonName: String?,

    val family: String?,

    val genus: String?,

    @ColumnInfo(name= "image_url")
    val imageUrl: String?,

    val score: Double
)