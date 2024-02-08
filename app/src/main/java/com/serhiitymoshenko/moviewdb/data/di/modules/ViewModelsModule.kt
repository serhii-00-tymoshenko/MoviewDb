package com.serhiitymoshenko.moviewdb.data.di.modules

import com.serhiitymoshenko.moviewdb.data.networking.apis.MoviesApi
import com.serhiitymoshenko.moviewdb.ui.movies.repositories.MoviesRepository
import com.serhiitymoshenko.moviewdb.ui.movies.viewmodel.MoviesHomeViewModel
import org.koin.dsl.module

val viewModelsModule = module {

    single<MoviesRepository> {
        MoviesRepository(get())
    }

    single<MoviesHomeViewModel> {
        MoviesHomeViewModel(get())
    }
}