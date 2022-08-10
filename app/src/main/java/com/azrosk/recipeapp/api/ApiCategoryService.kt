package com.azrosk.recipeapp.api

import com.azrosk.recipeapp.helper.Constants
import com.azrosk.recipeapp.models.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiCategoryService {
    @GET(Constants.END_CATEGORY_POINT)
    suspend fun getCategories() : Response<CategoryResponse>
}