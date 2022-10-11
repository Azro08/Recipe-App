package com.azrosk.recipeapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.azrosk.recipeapp.databinding.FragmentRecipeBinding
import com.azrosk.recipeapp.utilities.GlideLoader
import com.azrosk.recipeapp.viewModel.RecipeViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeFragment : Fragment() {
    private var _binding : FragmentRecipeBinding?=null
    private val binding get() = _binding!!
    private val viewModel : RecipeViewModel by viewModels()
    private var vidId = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mealId = arguments?.getString("meal_id")

        if (mealId != null) {
            getRecipe(mealId)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun getRecipe(mealId : String) {
        viewModel.getRecipe(mealId)
        viewModel.responseMeals.observe(requireActivity()){ recipe->
            binding.tvMeal.text = "${binding.tvMeal.text} ${recipe.meals[0].strMeal}"
            binding.tvArea.text = binding.tvArea.text.toString() + recipe.meals[0].strArea
            binding.tvMealCategory.text = binding.tvMealCategory.text.toString() + recipe.meals[0].strCategory
            binding.tvInstruct.text = binding.tvInstruct.text.toString() + recipe.meals[0].strInstructions
            GlideLoader(requireContext()).loadImage(Uri.parse(recipe.meals[0].strMealThumb), binding.ivRecImage)

            for (i in (recipe.meals[0].strYoutube.length -11) until recipe.meals[0].strYoutube.length){
                vidId += recipe.meals[0].strYoutube[i]
            }

            Log.d("vidId", vidId)

            binding.btnPlayVid.setOnClickListener{
                binding.btnPlayVid.visibility = View.GONE
                binding.ivRecImage.visibility = View.GONE
                binding.ivAddToFav.visibility = View.GONE
                binding.youtubePlayerView.visibility = View.VISIBLE
                playVideo(vidId)
            }

            binding.ivYoutubeLink.setOnClickListener{
                val uri = Uri.parse(recipe.meals[0].strYoutube)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

        }
    }

    private fun playVideo(videoId: String){
        lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
        _binding = null
    }

}