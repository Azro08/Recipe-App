package com.azrosk.recipeapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrosk.recipeapp.models.MealResponse
import com.azrosk.recipeapp.repository.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel
@Inject
constructor(private val mealsRepository: MealsRepository) : ViewModel()
{
    private val _response = MutableLiveData<MealResponse>()
    val responseMeals : LiveData<MealResponse>
        get() = _response


    fun getMeals(category : String) = viewModelScope.launch {
        mealsRepository.getMeals(category).let { response ->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }
            else{
                Log.e("error_response", response.message())
            }
        }
    }

}