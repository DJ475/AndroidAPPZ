package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private lateinit var GetListview : ListView
    private lateinit var getUserINName : EditText
    private lateinit var getUserINIngredients : EditText
    private lateinit var getUserINInstructions : EditText
    private lateinit var getUserINNumber : EditText


    private lateinit var adapter : ArrayAdapter<Recipe>
    private lateinit var SwitchDelete : SwitchCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        if(savedInstanceState != null)
//        {
//            val getRecipe = savedInstanceState.getString("RecipeSaved").toString()
//            println("Saved Recipe is " + getRecipe)
//        }

        GetListview = findViewById(R.id.ListViewRecipe)

        getUserINName = findViewById(R.id.UserName)
        getUserINIngredients = findViewById(R.id.UserIngredient)
        getUserINInstructions = findViewById(R.id.UserInstruction)
        getUserINNumber = findViewById(R.id.UserNumber)

        SwitchDelete = findViewById(R.id.SwitchDelete)

        adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            Recipe.recipes
        )

        GetListview.adapter = adapter

        val recipe = supportFragmentManager.findFragmentById(R.id.FragmentContainerView) as? DetailRecipeFragment

        if(recipe == null)
        {
            GetListview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                if(SwitchDelete.isChecked)
                {
                    Recipe.recipes.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
                else
                {
                    val DetailFragment = DetailRecipeFragment()
                    DetailFragment.SetRecipe(position)

                    val ft = supportFragmentManager.beginTransaction() // required
                    ft.replace(R.id.FragmentContainerView, DetailFragment) // required
                    ft.addToBackStack(null)
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    ft.commit()
                }
            }
        }
    }

    fun SubmitRecipe(view: View) {
        val userName = getUserINName.text.toString()
        val useringredients = getUserINIngredients.text.toString()
        val getUserRecInstr = getUserINInstructions.text.toString()
        val getUserRecNum = getUserINNumber.text.toString()


        if(userName.isNotEmpty() && userName != "\n" && userName != "\n\n" &&
            useringredients.isNotEmpty() && useringredients != "\n" && useringredients != "\n\n" &&
            getUserRecInstr.isNotEmpty() && getUserRecInstr != "\n" && getUserRecInstr != "\n\n" &&
            getUserRecNum.isNotEmpty() && getUserRecNum != "\n" && getUserRecNum != "\n\n")
        {

            Recipe.recipes.add(Recipe(userName,useringredients,getUserRecInstr, getUserRecNum.toInt()))
            adapter.notifyDataSetChanged()
            getUserINName.text.clear()
            getUserINIngredients.text.clear()
            getUserINInstructions.text.clear()
            getUserINNumber.text.clear()
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("RecipeSaved",Recipe.recipes.toString())
//    }
}