package uz.gita.eventapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "event")
data class EventData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val imageId:Int,
    val state: Int = 0
)