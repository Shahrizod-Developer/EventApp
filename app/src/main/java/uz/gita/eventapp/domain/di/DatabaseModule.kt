package uz.gita.eventapp.domain.di

import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.eventapp.data.room.dao.EventDao
import uz.gita.eventapp.data.room.database.AppDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "events.db")
            .allowMainThreadQueries().build()
    }

    @[Provides Singleton]
    fun provideDao(database: AppDatabase): EventDao = database.dao()
}