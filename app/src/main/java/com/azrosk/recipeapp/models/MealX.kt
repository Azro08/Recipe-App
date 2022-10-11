package com.azrosk.recipeapp.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.azrosk.recipeapp.helper.Constants
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.TABLE_NAME)
data class MealX(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @SerializedName("idMeal")
    val idMeal: String = "",
    @SerializedName("strArea")
    val strArea: String = "",
    @SerializedName("strCategory")
    val strCategory: String = "",
    @SerializedName("strInstructions")
    val strInstructions: String = "",
    @SerializedName("strMeal")
    val strMeal: String = "",
    @SerializedName("strMealThumb")
    val strMealThumb: String = "",
    @SerializedName("strYoutube")
    val strYoutube: String = ""
)