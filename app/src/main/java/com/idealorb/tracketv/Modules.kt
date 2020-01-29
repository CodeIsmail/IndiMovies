package com.idealorb.tracketv

import com.idealorb.tracketv.ui.TvShowDiscoverViewModel
import dev.codeismail.data.datasource.RemoteDataSource
import dev.codeismail.data.repository.TvShowRepository
import dev.codeismail.data.service.TvShowService
import dev.codeismail.domain.IRepository
import dev.codeismail.domain.usecase.LoadTvShowUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module{
    single{RemoteDataSource(get())}
    single {TvShowService.service}
}

val repositoryModule = module {
    single<IRepository> { TvShowRepository(get()) }
}

val usecaseModule = module {
    factory { LoadTvShowUseCase(get()) }
}

val viewmodelModule = module{
    viewModel{
        TvShowDiscoverViewModel(get())
    }
}
