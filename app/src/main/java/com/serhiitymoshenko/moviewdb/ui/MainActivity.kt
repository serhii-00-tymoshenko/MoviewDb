package com.serhiitymoshenko.moviewdb.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.serhiitymoshenko.moviewdb.R
import com.serhiitymoshenko.moviewdb.data.di.modules.networkingModule
import com.serhiitymoshenko.moviewdb.data.di.modules.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureKoin(this)
    }

    private fun configureKoin(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(networkingModule, viewModelsModule)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}