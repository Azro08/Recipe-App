package com.azrosk.recipeapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrosk.recipeapp.models.MealResponse
import com.azrosk.recipeapp.models.RecipeResponse
import com.azrosk.recipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel
@Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel()
{
    private val _response = MutableLiveData<RecipeResponse>()
    val responseMeals : LiveData<RecipeResponse>
        get() = _response

    fun getRecipe(mealId : String) = viewModelScope.launch {
        recipeRepository.getRecipe(mealId).let { response ->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }
            else{
                Log.e("error_response_rec", response.message())
            }
        }
    }
}