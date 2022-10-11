package com.azrosk.recipeapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.azrosk.recipeapp.R
import com.azrosk.recipeapp.adapter.CategoryAdapter
import com.azrosk.recipeapp.adapter.MealsAdapter
import com.azrosk.recipeapp.databinding.FragmentMealsBinding
import com.azrosk.recipeapp.helper.Constants
import com.azrosk.recipeapp.models.Category
import com.azrosk.recipeapp.models.Meal
import com.azrosk.recipeapp.viewModel.CategoryViewModel
import com.azrosk.recipeapp.viewModel.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsFragment : Fragment() {
    private var _binding : FragmentMealsBinding?=null
    private val binding get() = _binding!!
    private val viewModel: MealsViewModel by viewModels()
    private var adapter : MealsAdapter?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val category = arguments?.getString(Constants.MY_CATEGORY)
        getMeals(category!!)
    }

    private fun getMeals(category : String) {
        viewModel.getMeals(category)
        viewModel.responseMeals.observe(requireActivity()){ myMeal ->
            adapter = MealsAdapter(myMeal.meals, ({
                findNavController().navigate(R.id.nav_to_rec, bundleOf("meal_id" to it.idMeal))
            }))
            binding.rvMeals.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvMeals.setHasFixedSize(true)
            binding.rvMeals.adapter = adapter
        }
    }

}