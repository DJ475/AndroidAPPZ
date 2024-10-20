package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView


class DetailRecipeFragment : Fragment() {
    private lateinit var getDetailText : TextView
    private var recipeID : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_recipe, container, false)

        getDetailText = view.findViewById(R.id.DetailText)

        return view
    }

    fun SetRecipe(recpieID: Int)
    {
        this.recipeID = recpieID
    }

    override fun onStart() {
        super.onStart()
        val recipeGetInfo = Recipe.recipes[recipeID]
        getDetailText.append("Recipe Name: " + recipeGetInfo.recipe_name + "\n")
        getDetailText.append("Recipe Ingredients: " + recipeGetInfo.recipe_ingredients + "\n")
        getDetailText.append("Recipe Instructions: " + recipeGetInfo.recipe_instructions + "\n")
        getDetailText.append("Recipe Number: " + recipeGetInfo.recipe_num.toString() + "\n")
    }
}