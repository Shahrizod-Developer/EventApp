package uz.gita.eventapp.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.eventapp.domain.repository.AppRepository
import uz.gita.eventapp.domain.repository.impl.AppRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bind(impl: AppRepositoryImpl): AppRepository
}