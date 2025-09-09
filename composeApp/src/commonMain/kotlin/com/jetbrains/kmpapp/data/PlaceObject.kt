package com.jetbrains.kmpapp.data

import kotlinx.serialization.Serializable

@Serializable
data class PlaceObject(
    val id: Int,
    val name: String,
    val city: String,
    val country: String,
    val description: String,
    val primaryImage: String,
    val coordinates: Coordinates,
    val yearBuilt: String,
    val height: String,
    val category: String,
    val status: String,
    val visitorsPerYear: Int,
    val price: Price,
    val openingHours: OpeningHours,
    val tags: List<String>,
    val rating: Double,
    val difficulty: String,
    val bestTimeToVisit: List<String>,
    val languages: List<String>,
    val accessibility: Accessibility,
    val nearbyAttractions: List<String>,
    val website: String,
    val phone: String,
    val email: String
)

@Serializable
data class Coordinates(
    val latitude: Double,
    val longitude: Double
)

@Serializable
data class Price(
    val adult: Double,
    val child: Double,
    val currency: String
)

@Serializable
data class OpeningHours(
    val monday: String,
    val tuesday: String,
    val wednesday: String,
    val thursday: String,
    val friday: String,
    val saturday: String,
    val sunday: String
)

@Serializable
data class Accessibility(
    val wheelchairAccessible: Boolean,
    val elevatorAvailable: Boolean,
    val audioGuide: Boolean
)
