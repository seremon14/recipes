package com.example.recipes.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.R
import com.example.recipes.databinding.ActivityMainBinding
import com.example.recipes.domain.model.Recipe
import com.example.recipes.ui.view.adapter.RecipesAdapter
import com.example.recipes.ui.view.util.EmptyDataObserver
import com.example.recipes.ui.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var adapter: RecipesAdapter
    private lateinit var recipeArrayList: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeViewModel.onCreate()

        recipeViewModel.recipeModel.observe(this, Observer { recipes ->
            recipeArrayList = recipes
            initRecyclerView()
        })

        recipeViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        recipeViewModel.navigateToDetail.observe(this, Observer { recipe ->
            startActivity(DetailActivity.getStartIntent(this, recipe))
        })

        binding.srContainer.setOnRefreshListener {
            binding.srContainer.isRefreshing = false
            recipeViewModel.onCreate()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater

        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.actionSearch)

        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        val filtered = ArrayList<Recipe>()

        for (item in recipeArrayList) {
            if (item.name.toLowerCase().contains(text.lowercase(Locale.getDefault()))) {
                filtered.add(item)
            }
        }
        adapter.filterList(filtered)
    }

    private fun initRecyclerView() {
        binding.rvRecipesList.layoutManager = LinearLayoutManager(this)
        adapter = RecipesAdapter(recipeArrayList) { onItemSelected(it) }
        binding.rvRecipesList.adapter = adapter
        val emptyDataObserver = EmptyDataObserver(binding.rvRecipesList, findViewById<View>(R.id.emptyDataParent))
        adapter.registerAdapterDataObserver(emptyDataObserver)
    }

    private fun onItemSelected(recipe: Recipe) {
        //Go to detail
        recipeViewModel.navigateToDetail.postValue(recipe)
    }
}
