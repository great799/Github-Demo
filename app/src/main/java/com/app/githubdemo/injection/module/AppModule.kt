package com.app.githubdemo.injection.module

import com.app.githubdemo.GithubDemoApp
import com.app.githubdemo.utils.ImageUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: GithubDemoApp) {

    @Singleton
    @Provides
    internal fun providesApplication(): GithubDemoApp {
        return application
    }

    @Singleton
    @Provides
    internal fun providesImageUtils(): ImageUtils {
        return ImageUtils()
    }
}