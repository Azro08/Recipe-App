package com.azrosk.recipeapp.api

import com.azrosk.recipeapp.helper.Constants
import com.azrosk.recipeapp.models.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRecipeService {
    @GET(Constants.END_POINT_RECIPE)
    suspend fun getRecipe(@Query("i") mealId : String) : Response<RecipeResponse>
}