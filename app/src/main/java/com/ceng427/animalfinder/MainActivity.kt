package com.ceng427.animalfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.ceng427.animalfinder.Levels.Level1

class MainActivity : AppCompatActivity() {
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var extra = intent.extras

        if (extra != null) {
            score = extra!!.getInt("score")
        }
        val sp = this.getSharedPreferences(packageName,android.content.Context.MODE_PRIVATE)
        val bestScore = sp.getInt("score",0)

        if(score > bestScore){
            sp.edit().putInt("score", score).apply()
            findViewById<TextView>(R.id.score).text = score.toString()
        } else{
            findViewById<TextView>(R.id.score).text = bestScore.toString()
        }

    }

    fun play(view: View) {

        val intent= Intent(this,Level1::class.java)
        startActivity(intent)
        finish()
    }
}