package com.ceng427.animalfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.ceng427.animalfinder.Levels.Level1

class Result_Score : AppCompatActivity() {
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var extra = intent.extras

        if (extra != null) {
            score = extra!!.getInt("score")
        }
        findViewById<TextView>(R.id.score).text = "Score:" + score
    }

    fun playagain(view: View) {

        val intent= Intent(this, MainActivity::class.java)
        intent.putExtra("score",score)
        startActivity(intent)
        finish()
    }
}