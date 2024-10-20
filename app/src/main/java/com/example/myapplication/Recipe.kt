package com.example.myapplication

class Recipe (
    var recipe_name : String?,
    var recipe_instructions: String?,
    var recipe_ingredients : String?,
    var recipe_num : Int?,
) {
    companion object
    {
        var recipes = arrayListOf<Recipe>(
//            Recipe("Sugar Cookies","Make cookies","Sugar,Flour,Butter,Milk",1),
//            Recipe("Brownies","Make brownies","Sugar,Chocolate,Butter,Milk",3),
//            Recipe("FuFu","Make Fufu","Chicken,FuFU Dough,Rice,Sauce",9),
            )
    }

    override fun toString(): String {
        return recipe_name?:""
    }
}