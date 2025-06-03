package com.example.sibers_test_task.app

import android.app.Application
import com.example.network.di.networkModule
import com.example.sibers_test_task.di.navigation.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@App)
			modules(navigationModule)
			modules(networkModule)
//			modules(feedModule)
//			modules(detailModule)
		}
	}
}