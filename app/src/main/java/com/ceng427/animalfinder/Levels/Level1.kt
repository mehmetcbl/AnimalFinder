package com.ceng427.animalfinder.Levels

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ceng427.animalfinder.Animal
import com.ceng427.animalfinder.R
import com.ceng427.animalfinder.Result_Score
import java.util.*

class Level1 : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var animals: ArrayList<Animal>
    lateinit var correctAns: Animal
    lateinit var timer: CountDownTimer
    private var mediaPlayer: MediaPlayer? = null
    var mTTS: TextToSpeech? = null
    var counter = 0
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

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
        findViewById<TextView>(R.id.score).text = "Score: 0"
        countDown()
    }

    private fun countDown() {
        timer = object : CountDownTimer(20000, 1000) {
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
                    val intent = Intent(this@Level1, Result_Score::class.java)
                    startActivity(intent)
                    finish()
                }, 2000)
            }
        }
        timer.start()
    }

    private fun loadAnimals() {
        var animalModel = Animal()
        animals = animalModel.getAnimals()

        val animal1 = animals.random();
        animals.remove(animal1)
        val animal2 = animals.random();

        val animalName = findViewById<TextView>(R.id.animalName)

        val rnds = (1..2).random()

        if (rnds == 1) {
            correctAns = animal1
            Log.e("1", correctAns.name.toString())
        } else {
            correctAns = animal2
            Log.e("2", correctAns.name.toString())
        }
        animalName.text = correctAns.name
        Log.e("Random Int:", rnds.toString())
        Log.e("Correct Animal:", correctAns.name.toString())

        val firstAnimalView = findViewById<ImageView>(R.id.FirstAnimal)
        val secondAnimalView = findViewById<ImageView>(R.id.SecondAnimal)

        firstAnimalView.setImageResource(animal1.image!!)
        firstAnimalView.contentDescription = animal1.name
        Log.e("Content:Animal1", firstAnimalView.contentDescription.toString())
        secondAnimalView.setImageResource(animal2.image!!)
        secondAnimalView.contentDescription = animal2.name
        Log.e("Content:Animal2", secondAnimalView.contentDescription.toString())
        //Thread.sleep(2_000)
        //mTTS!!.speak("Find the ${correctAns.name}, ${correctAns.name} makes sound: ",TextToSpeech.QUEUE_FLUSH,null)
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

        //Thread.sleep(1_000)
        Handler().postDelayed({
            mediaPlayer = MediaPlayer.create(this@Level1, correctAns.sound!!)
            mediaPlayer!!.setVolume(0.15F, 0.15F)
            mediaPlayer!!.start()
        }, 2000)

    }

    fun ImgClicked(view: View) {
        Log.e("Content:Animal1", findViewById<ImageView>(R.id.FirstAnimal).contentDescription.toString())
        val view = view as ImageView
        Log.e("Clicked:View=", view.contentDescription.toString())
        Log.e("Clicked:ViewID=", view.id.toString())
        timer.cancel()
        Log.e("Clicked:Correct=", correctAns.name.toString())
        if (view.contentDescription == correctAns.name) {
            score = score + 10
            findViewById<TextView>(R.id.score).text = "Score: "+ score
            if (mediaPlayer != null) {
                mediaPlayer!!.stop()
            }
            if (counter == 2) {
                val intent = Intent(this, Level2::class.java)
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