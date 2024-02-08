package com.serhiitymoshenko.moviewdb.data.di.modules

import com.serhiitymoshenko.moviewdb.data.networking.apis.MoviesApi
import com.serhiitymoshenko.moviewdb.data.utils.ACCEPT_HEADER
import com.serhiitymoshenko.moviewdb.data.utils.AUTHORIZATION_HEADER
import com.serhiitymoshenko.moviewdb.data.utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val retrofitModule = module {

    single<Interceptor>(named("logging_interceptor")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    single<Interceptor>(named("headers_interceptor")) {
        Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("accept", ACCEPT_HEADER)
                .addHeader("Authorization", AUTHORIZATION_HEADER)
                .build()
            chain.proceed(request)
        }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named("headers_interceptor")))
            .addInterceptor(get<Interceptor>(named("logging_interceptor")))
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MoviesApi> {
        get<Retrofit>().create(MoviesApi::class.java)
    }
}