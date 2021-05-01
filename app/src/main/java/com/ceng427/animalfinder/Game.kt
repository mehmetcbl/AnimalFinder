package com.ceng427.animalfinder

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class Game : AppCompatActivity() {

    var animalList = ArrayList<Animal>()

    var names = arrayOf(
        "cat",
        "chicken",
        "cock",
        "crow",
        "dog",
        "donkey",
        "duck",
        "elephant",
        "frog",
        "horse",
        "lion",
        "monkey",
        "mouse",
        "owl",
        "penguin",
        "pig",
        "sheep",
        "snake",
        "wolf"
    )

    var images = intArrayOf(
        R.drawable.cat,
        R.drawable.chicken,
        R.drawable.cock,
        R.drawable.crow,
        R.drawable.dog,
        R.drawable.donkey,
        R.drawable.duck,
        R.drawable.elephant,
        R.drawable.frog,
        R.drawable.horse,
        R.drawable.lion,
        R.drawable.monkey,
        R.drawable.mouse,
        R.drawable.owl,
        R.drawable.penguin,
        R.drawable.pig,
        R.drawable.sheep,
        R.drawable.snake,
        R.drawable.wolf
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


    }

    class CustomAdapter(
        var itemModel: ArrayList<Animal>,
        var context: Context
    ) : BaseAdapter(){

        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            var view = view;
            if (view == null){
                view = layoutInflater.inflate(R.layout.row_animals,viewGroup,false)
            }

            var ImageView= view?.findViewById<ImageView>(R.id.imageView);

            ImageView?.setImageResource(itemModel[position].image!!)


        }

        override fun getItem(p0: Int): Any {
            return itemModel[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return itemModel.size
        }

    }

}