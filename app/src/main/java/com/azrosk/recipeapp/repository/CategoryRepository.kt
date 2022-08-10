package com.azrosk.recipeapp.repository

import com.azrosk.recipeapp.api.ApiCategoryService
import javax.inject.Inject

class CategoryRepository
@Inject
constructor(private val apiCategoryService: ApiCategoryService){
    suspend fun getCategories() = apiCategoryService.getCategories()
}