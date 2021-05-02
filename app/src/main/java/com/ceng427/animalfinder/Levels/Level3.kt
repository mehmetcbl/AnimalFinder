package com.ceng427.animalfinder.Levels

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ceng427.animalfinder.Animal
import com.ceng427.animalfinder.R
import com.ceng427.animalfinder.Result_Score
import java.util.*
import kotlin.collections.ArrayList

class Level3 : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var animals: ArrayList<Animal>
    lateinit var correctAns: Animal
    lateinit var timer: CountDownTimer
    private var mediaPlayer: MediaPlayer? = null
    var mTTS: TextToSpeech? = null
    var counter = 0
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level3)
        mTTS = TextToSpeech(this, this)
        loadAnimals()
        val animalClick = findViewById(R.id.FirstAnimal) as ImageView
        // set on-click listener
        animalClick.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            ImgClicked(animalClick)
        }

        val animalClick2 = findViewById(R.id.SecondAnimal) as ImageView
        // set on-click listener
        animalClick2.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            ImgClicked(animalClick2)
        }

        val animalClick3 = findViewById(R.id.ThirdAnimal) as ImageView
        // set on-click listener
        animalClick3.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            ImgClicked(animalClick3)
        }

        val animalClick4 = findViewById(R.id.FourthAnimal) as ImageView
        // set on-click listener
        animalClick4.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            ImgClicked(animalClick4)
        }
        var extra = intent.extras

        if (extra != null) {
            score = extra!!.getInt("score")
        }
        findViewById<TextView>(R.id.score).text = "Score:" + score
        countDown()
    }

    private fun loadAnimals() {
        var animalModel = Animal()
        animals = animalModel.getAnimals()

        val animal1 = animals.random();
        animals.remove(animal1)
        val animal2 = animals.random();
        animals.remove(animal2)
        val animal3 = animals.random();
        animals.remove(animal3)
        val animal4 = animals.random();

        val animalName = findViewById<TextView>(R.id.animalName)

        val rnds = (1..3).random()

        if (rnds == 1) {
            correctAns = animal1
        } else if (rnds == 2) {
            correctAns = animal2
        } else if (rnds == 3) {
            correctAns = animal3
        } else {
            correctAns = animal4
        }
        animalName.text = correctAns.name

        val firstAnimalView = findViewById<ImageView>(R.id.FirstAnimal)
        val secondAnimalView = findViewById<ImageView>(R.id.SecondAnimal)
        val thirdAnimalView = findViewById<ImageView>(R.id.ThirdAnimal)
        val fourthAnimalView = findViewById<ImageView>(R.id.FourthAnimal)

        firstAnimalView.setImageResource(animal1.image!!)
        firstAnimalView.contentDescription = animal1.name
        secondAnimalView.setImageResource(animal2.image!!)
        secondAnimalView.contentDescription = animal2.name
        thirdAnimalView.setImageResource(animal3.image!!)
        thirdAnimalView.contentDescription = animal3.name
        fourthAnimalView.setImageResource(animal4.image!!)
        fourthAnimalView.contentDescription = animal4.name

        if (counter > 0) {
            if (!mTTS!!.isSpeaking()) {
                mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
                    if (status != TextToSpeech.ERROR) {
                        mTTS!!.language = Locale.UK

                        mTTS!!.speak("You got it. Find the ${correctAns.name}, ${correctAns.name} makes sound: ", TextToSpeech.QUEUE_FLUSH, null)
                    }
                })
            }
            timer.start()
        } else {
            if (!mTTS!!.isSpeaking()) {
                mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
                    if (status != TextToSpeech.ERROR) {
                        mTTS!!.language = Locale.UK

                        mTTS!!.speak("Find the ${correctAns.name}, ${correctAns.name} makes sound: ", TextToSpeech.QUEUE_FLUSH, null)
                    }
                })
            }
        }

        Handler().postDelayed({
            mediaPlayer = MediaPlayer.create(this@Level3, correctAns.sound!!)
            mediaPlayer!!.setVolume(0.09F, 0.09F)
            mediaPlayer!!.start()
        }, 2000)
    }

    fun ImgClicked(view: View) {

        val view = view as ImageView
        timer.cancel()
        Log.e("Clicked:Correct=", correctAns.name.toString())
        if (view.contentDescription == correctAns.name) {
            score = score + 10
            findViewById<TextView>(R.id.score).text = "Score: "+ score
            if (mediaPlayer != null) {
                mediaPlayer!!.stop()
            }

            if (counter == 2) {
                val intent = Intent(this, Level4::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            } else {
                counter++;
                loadAnimals();
            }
        } else {
            Toast.makeText(this, "Answer is Wrong", Toast.LENGTH_SHORT).show()
            if (mediaPlayer != null) {
                mediaPlayer!!.stop()
            }
            if (!mTTS!!.isSpeaking()) {
                mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
                    if (status != TextToSpeech.ERROR) {
                        mTTS!!.language = Locale.UK

                        mTTS!!.speak("Answer is Wrong", TextToSpeech.QUEUE_FLUSH, null)
                    }
                })
            }
            Handler().postDelayed({
                val intent = Intent(this, Result_Score::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }, 2000)
        }


    }

    private fun countDown() {
        timer = object : CountDownTimer(14000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                findViewById<TextView>(R.id.timer).text = "Time Remain: " + (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                if (mediaPlayer != null) {
                    mediaPlayer!!.stop()
                }
                if (!mTTS!!.isSpeaking()) {
                    mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
                        if (status != TextToSpeech.ERROR) {
                            mTTS!!.language = Locale.UK

                            mTTS!!.speak("Time is Up", TextToSpeech.QUEUE_FLUSH, null)
                        }
                    })
                }
                Handler().postDelayed({
                    val intent = Intent(this@Level3, Result_Score::class.java)
                    startActivity(intent)
                    finish()
                }, 2000)
            }
        }
        timer.start()
    }

    override fun onDestroy() {
        mTTS!!.shutdown()
        super.onDestroy()

        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            mTTS!!.setLanguage(Locale.UK)
        }
    }
}