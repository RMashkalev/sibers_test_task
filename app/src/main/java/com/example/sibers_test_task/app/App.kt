package com.example.sibers_test_task.app

import android.app.Application
import com.example.component.navigation.di.navigationModule
import com.example.features.details.di.detailsModule
import com.example.features.feed.di.feedModule
import com.example.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@App)
			modules(navigationModule)
			modules(networkModule)
			modules(feedModule)
			modules(detailsModule)
		}
	}
}