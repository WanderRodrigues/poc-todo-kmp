package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.InMemoryPlaceStorage
import com.jetbrains.kmpapp.data.KtorPlaceApi
import com.jetbrains.kmpapp.data.PlaceApi
import com.jetbrains.kmpapp.data.PlaceRepository
import com.jetbrains.kmpapp.data.PlaceStorage
import com.jetbrains.kmpapp.screens.detail.DetailViewModel
import com.jetbrains.kmpapp.screens.list.ListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<PlaceApi> { KtorPlaceApi(get()) }
    single<PlaceStorage> { InMemoryPlaceStorage() }
    single {
        PlaceRepository(get(), get()).apply {
            initialize()
        }
    }
}

val viewModelModule = module {
    factoryOf(::ListViewModel)
    factoryOf(::DetailViewModel)
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            viewModelModule,
        )
    }
}
