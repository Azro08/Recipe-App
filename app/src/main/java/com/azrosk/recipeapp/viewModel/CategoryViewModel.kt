package com.azrosk.recipeapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrosk.recipeapp.models.CategoryResponse
import com.azrosk.recipeapp.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
@Inject
constructor(private val categoryRepository: CategoryRepository) : ViewModel(){
    private val _response = MutableLiveData<CategoryResponse>()
    val responseCategory : LiveData<CategoryResponse>
        get() = _response

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch{
        categoryRepository.getCategories().let { response ->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }
            else{
                Log.d("myErr", response.message())
            }
        }
    }


}