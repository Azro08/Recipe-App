package com.azrosk.recipeapp.models


import androidx.room.Entity
import com.azrosk.recipeapp.helper.Constants
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("meals")
    val meals: List<MealX>
)