package com.jetbrains.kmpapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface PlaceStorage {
    suspend fun savePlaces(newPlaces: List<PlaceObject>)

    fun getPlaceById(placeId: Int): Flow<PlaceObject?>

    fun getPlaces(): Flow<List<PlaceObject>>
}

class InMemoryPlaceStorage : PlaceStorage {
    private val storedPlaces = MutableStateFlow(emptyList<PlaceObject>())

    override suspend fun savePlaces(newPlaces: List<PlaceObject>) {
        storedPlaces.value = newPlaces
    }

    override fun getPlaceById(placeId: Int): Flow<PlaceObject?> {
        return storedPlaces.map { places ->
            places.find { it.id == placeId }
        }
    }

    override fun getPlaces(): Flow<List<PlaceObject>> = storedPlaces
}
