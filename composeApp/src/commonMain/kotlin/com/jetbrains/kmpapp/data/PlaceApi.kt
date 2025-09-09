package com.jetbrains.kmpapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.CancellationException

interface PlaceApi {
    suspend fun getData(): List<PlaceObject>
}

class KtorPlaceApi(private val client: HttpClient) : PlaceApi {
    companion object {
        private const val API_URL = "https://raw.githubusercontent.com/WanderRodrigues/poc-todo-kmp/main/places.json"
    }

    override suspend fun getData(): List<PlaceObject> {
        return try {
            client.get(API_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }
}
