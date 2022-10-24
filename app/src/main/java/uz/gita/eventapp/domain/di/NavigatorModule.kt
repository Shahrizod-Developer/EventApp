package uz.gita.eventapp.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.eventapp.presentation.navigation.Handler
import uz.gita.eventapp.presentation.navigation.NavigationDispatcher
import uz.gita.eventapp.presentation.navigation.Navigator

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {

    @Binds
    fun bindsNavigator(impl: NavigationDispatcher): Navigator

    @Binds
    fun bindsHandler(impl: NavigationDispatcher): Handler
}