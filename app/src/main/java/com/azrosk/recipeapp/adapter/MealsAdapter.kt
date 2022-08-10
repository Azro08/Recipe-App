package com.azrosk.recipeapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azrosk.recipeapp.databinding.MealHolderBinding
import com.azrosk.recipeapp.models.Meal
import com.azrosk.recipeapp.utilities.GlideLoader

class MealsAdapter(
    private val mealsList : List<Meal>,
    var listener:(meal : Meal)  -> Unit) : RecyclerView.Adapter<MealsAdapter.MyViewHolder>() {

    class MyViewHolder(listener:(meal: Meal) ->Unit,
                       var binding : MealHolderBinding
    ) : RecyclerView.ViewHolder(binding.root){
        private val context = itemView.context!!
        var meal : Meal? =null

        fun bind(myMeal: Meal){
            GlideLoader(context).loadImage(Uri.parse(myMeal.strMealThumb), binding.ivMeal)
            binding.tvMealName.text = myMeal.strMeal
            meal = myMeal
        }

        init {
            binding.ivMeal.setOnClickListener { listener(meal!!) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(listener, MealHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mealsList[position])
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

}