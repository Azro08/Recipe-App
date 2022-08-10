package com.azrosk.recipeapp.utilities

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.azrosk.recipeapp.R
import com.bumptech.glide.Glide

class GlideLoader(private val context: Context) {

    fun loadImage(imageUri : Uri, image : ImageView){
        try {
            Glide
                .with(context)
                .load(Uri.parse(imageUri.toString()))
                .centerCrop()
                .placeholder(R.drawable.food_img) // if image failed to upload
                .into(image)
        } catch (ex : Exception){}
    }

}