package com.app.githubdemo.injection.component

import com.app.githubdemo.injection.module.AppModule
import com.app.githubdemo.injection.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, NetworkModule::class]
)
interface AppComponent {
//    fun inject(routesInfoViewModel: RoutesInfoViewModel)
}