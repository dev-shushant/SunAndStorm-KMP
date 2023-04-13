package dev.shushant.models

import kotlinx.serialization.Serializable

@Serializable
data class LocationData(
    val accuracy: Double = 0.0,
    val altitude: Double = 0.0,
    val altitudeAccuracy: Double = 0.0,
    val heading: Double = 0.0,
    val speed: Double = 0.0,
    val coordinates: Coordinates = Coordinates(),
    val address: Address = Address(),
    val dateTime: String = ""
)

@Serializable
data class Address(
    var country: String = "",
    var locality: String = "",
    var subLocality: String = "Gurgaon",
    var thoroughfare: String = "",
    var state: String = "",
    var postalCode: String = "",
)

@Serializable
data class Coordinates(
    val latitude: Double = 28.459497,
    val longitude: Double = 77.026634,
)