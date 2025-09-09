package com.jetbrains.kmpapp.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PlaceRepository(
    private val placeApi: PlaceApi,
    private val placeStorage: PlaceStorage,
) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        placeStorage.savePlaces(placeApi.getData())
    }

    fun getPlaces(): Flow<List<PlaceObject>> = placeStorage.getPlaces()

    fun getPlaceById(placeId: Int): Flow<PlaceObject?> = placeStorage.getPlaceById(placeId)
}
