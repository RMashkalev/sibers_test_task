package com.example.component.navigation.di

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.component.navigation.routers.DetailsRouterImpl
import com.example.component.navigation.routers.FeedRouterImpl
import com.example.navigation_contract.routers.DetailsRouter
import com.example.navigation_contract.routers.FeedRouter
import org.koin.dsl.module

val navigationModule = module {

	single<FeedRouter> { (navController: NavController) ->
		FeedRouterImpl(navController)
	}

	single<DetailsRouter> { (navController: NavController) ->
		DetailsRouterImpl(navController)
	}
}