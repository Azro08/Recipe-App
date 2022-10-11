package com.azrosk.recipeapp.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.azrosk.recipeapp.adapter.CategoryAdapter
import com.azrosk.recipeapp.databinding.FragmentCategoryBinding
import com.azrosk.recipeapp.models.Category
import com.azrosk.recipeapp.viewModel.CategoryViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azrosk.recipeapp.R
import com.azrosk.recipeapp.helper.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private var _binding : FragmentCategoryBinding?=null
    private val binding get() = _binding!!
    private val viewModel: CategoryViewModel by viewModels()
    private var adapter : CategoryAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadCategories()
    }

    private fun loadCategories() {
        viewModel.responseCategory.observe(requireActivity()){ categories ->
            adapter = CategoryAdapter(categories.categories, ({
                findNavController().navigate(R.id.nav_to_meals, bundleOf(Constants.MY_CATEGORY to it.strCategory))
            }))
            binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())
            binding.rvCategories.setHasFixedSize(true)
            binding.rvCategories.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}