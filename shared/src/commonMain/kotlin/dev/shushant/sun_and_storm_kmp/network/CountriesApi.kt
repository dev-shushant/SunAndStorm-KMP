package dev.shushant.sun_and_storm_kmp.network

import dev.shushant.sun_and_storm_kmp.listing.Country

interface CountriesApi {
    suspend fun getCountriesList(): List<Country>
}