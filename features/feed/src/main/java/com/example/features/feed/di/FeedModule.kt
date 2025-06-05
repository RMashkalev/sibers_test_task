package com.example.features.feed.di

import com.example.features.feed.data.api.FeedApi
import com.example.features.feed.data.datasource.FeedRemoteDataSource
import com.example.features.feed.data.datasource.FeedRemoteDataSourceImpl
import com.example.features.feed.data.repository.FeedRepositoryImpl
import com.example.features.feed.domain.repository.FeedRepository
import com.example.features.feed.domain.usecase.LoadPokemonsUseCase
import com.example.navigation_contract.routers.FeedRouter
import com.example.features.feed.presentation.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val feedModule = module {

	factory { get<Retrofit>().create(FeedApi::class.java) }

	factory<FeedRemoteDataSource> { FeedRemoteDataSourceImpl(get()) }

	factory<FeedRepository> { FeedRepositoryImpl(get()) }

	factory { LoadPokemonsUseCase(get()) }

	viewModel { (router: FeedRouter) ->
		FeedViewModel(
			loadPokemonsUseCase = get(),
			feedRouter = router
		)
	}
}