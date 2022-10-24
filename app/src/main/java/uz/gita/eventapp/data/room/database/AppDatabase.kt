package uz.gita.eventapp.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.eventapp.data.room.dao.EventDao
import uz.gita.eventapp.data.room.entity.EventData

@Database(entities = [EventData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): EventDao
}