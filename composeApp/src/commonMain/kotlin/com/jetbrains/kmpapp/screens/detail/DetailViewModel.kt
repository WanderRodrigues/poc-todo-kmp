package com.jetbrains.kmpapp.screens.detail

import androidx.lifecycle.ViewModel
import com.jetbrains.kmpapp.data.PlaceObject
import com.jetbrains.kmpapp.data.PlaceRepository
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val placeRepository: PlaceRepository) : ViewModel() {
    fun getPlace(placeId: Int): Flow<PlaceObject?> =
        placeRepository.getPlaceById(placeId)
}
