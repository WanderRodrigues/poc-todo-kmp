package com.jetbrains.kmpapp.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.data.PlaceObject
import com.jetbrains.kmpapp.data.PlaceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ListViewModel(placeRepository: PlaceRepository) : ViewModel() {
    val places: StateFlow<List<PlaceObject>> =
        placeRepository.getPlaces()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
