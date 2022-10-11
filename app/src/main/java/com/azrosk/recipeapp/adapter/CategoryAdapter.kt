package com.azrosk.recipeapp.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azrosk.recipeapp.databinding.CategoryHolderBinding
import com.azrosk.recipeapp.models.Category
import com.azrosk.recipeapp.utilities.GlideLoader

class CategoryAdapter (
    private val categoryList : List<Category>,
    private var listener:(category : Category)  -> Unit) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    class MyViewHolder(listener:(category:Category) ->Unit,
                       var binding : CategoryHolderBinding) : RecyclerView.ViewHolder(binding.root){
        private val context = itemView.context!!
        var category : Category ? =null

        fun bind(myCategory: Category){
            GlideLoader(context).loadImage(Uri.parse(myCategory.strCategoryThumb), binding.ivCategory)
            Log.d("my_path", myCategory.strCategoryThumb)
            binding.tvCategory.text = myCategory.strCategory
            binding.tvCategoryDes.text = myCategory.strCategoryDescription
            category = myCategory
        }

        init {
            binding.fbOpenCategory.setOnClickListener { listener(category!!) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(listener, CategoryHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

}