package com.example.features.details.di

import com.example.features.details.data.api.DetailsApi
import com.example.features.details.data.datasource.DetailsRemoteDataSource
import com.example.features.details.data.datasource.DetailsRemoteDataSourceImpl
import com.example.features.details.data.repository.DetailsRepositoryImpl
import com.example.features.details.domain.repository.DetailsRepository
import com.example.features.details.domain.usecase.LoadPokemonDataUseCase
import com.example.features.details.presentation.DetailsRouter
import com.example.features.details.presentation.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val detailsModule = module {

	factory { get<Retrofit>().create(DetailsApi::class.java) }

	factory<DetailsRemoteDataSource> { DetailsRemoteDataSourceImpl(get()) }

	factory<DetailsRepository> { DetailsRepositoryImpl(get()) }

	factory { LoadPokemonDataUseCase(get()) }

	viewModel { (router: DetailsRouter) ->
		DetailsViewModel(
			loadPokemonDataUseCase = get(),
			detailsRouter = router
		)
	}
}