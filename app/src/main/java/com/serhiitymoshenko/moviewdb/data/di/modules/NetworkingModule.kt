package com.serhiitymoshenko.moviewdb.data.di.modules

import org.koin.dsl.module

val networkingModule = module {
    includes(retrofitModule)
}