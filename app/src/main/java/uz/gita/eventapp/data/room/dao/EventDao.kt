package uz.gita.eventapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.gita.eventapp.data.room.entity.EventData

@Dao
interface EventDao {

    @Insert
    fun insert(event: EventData)

    @Insert
    fun insert(events: List<EventData>)

    @Query("Select * From event")
    fun getAllEvent(): Flow<List<EventData>>

    @Query("Select * From event Where event.state != 0")
    fun getAllSwitchEvent(): Flow<List<EventData>>

    @Query("Select * From event Where id =:id")
    fun getEventGetById(id: Int): EventData

    @Update
    fun changeEventState(event: EventData)
}