package uz.gita.eventapp.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.eventapp.domain.usecase.UseCase
import uz.gita.eventapp.domain.usecase.impl.UseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bind(impl: UseCaseImpl): UseCase
}