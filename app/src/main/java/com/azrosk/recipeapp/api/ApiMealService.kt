package com.azrosk.recipeapp.api

import com.azrosk.recipeapp.helper.Constants
import com.azrosk.recipeapp.models.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMealService {
    @GET(Constants.END_POINT_MEAL)
    suspend fun getMeals(@Query("c") categoryName : String) : Response<MealResponse>
}