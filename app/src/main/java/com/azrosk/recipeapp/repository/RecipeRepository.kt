package com.azrosk.recipeapp.repository

import androidx.room.Entity
import com.azrosk.recipeapp.api.ApiRecipeService
import com.azrosk.recipeapp.helper.Constants
import javax.inject.Inject


class RecipeRepository
@Inject constructor(private val apiRecipeService: ApiRecipeService){
    suspend fun getRecipe(mealId : String) = apiRecipeService.getRecipe(mealId)
}