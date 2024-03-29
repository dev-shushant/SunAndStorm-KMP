package dev.shushant.sun_and_storm_kmp.data

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val touristPlaces: List<TouristPlace>
)

@Serializable
data class TouristPlace(
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val images: List<String>
)