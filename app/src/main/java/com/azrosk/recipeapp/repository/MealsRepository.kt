package com.azrosk.recipeapp.repository

import com.azrosk.recipeapp.api.ApiMealService
import javax.inject.Inject

class MealsRepository
@Inject constructor(private val apiMealService: ApiMealService)
{
    suspend fun getMeals (category : String) = apiMealService.getMeals(category)
}