package com.sdk.data.remote.network

import com.sdk.data.remote.model.FoodResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodService {
    @GET("recipes/complexSearch")
    suspend fun getAllRecipes(
        @QueryMap map: Map<String, String>
    ): Response<FoodResponseDTO>

    // apiKey = 32423kjrfdkls
    // diet = mainCourse
    // number = 10

    @GET("recipes/complexSearch")
    suspend fun searchFood(
        @QueryMap searchQueries: Map<String, String>
    ): Response<FoodResponseDTO>
    // query = "shorva"
    // apiKey = 32423kjrfdkls
    // diet = mainCourse
    // number = 10

    // bolta-shorva.jpg
}