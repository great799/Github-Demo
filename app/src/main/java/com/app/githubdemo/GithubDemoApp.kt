package com.app.githubdemo

import android.app.Application
import com.app.githubdemo.injection.component.AppComponent
import com.app.githubdemo.injection.component.DaggerAppComponent
import com.app.githubdemo.injection.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class GithubDemoApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: AndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    companion object {
        private var appComponent: AppComponent? = null
        private var instance: GithubDemoApp? = null

        fun getAppComponent(): AppComponent {
            return appComponent!!
        }

        fun getInstance(): GithubDemoApp {
            return instance ?: GithubDemoApp()
        }
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}