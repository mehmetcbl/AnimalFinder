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

class Level5 : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var animals: ArrayList<Animal>
    lateinit var correctAns: Animal
    lateinit var timer: CountDownTimer
    private var mediaPlayer: MediaPlayer? = null
    var mTTS: TextToSpeech? = null
    var counter = 0
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level5)
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

        val animalClick5 = findViewById(R.id.FifthAnimal) as ImageView
        // set on-click listener
        animalClick5.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            ImgClicked(animalClick5)
        }

        val animalClick6 = findViewById(R.id.SixthAnimal) as ImageView
        // set on-click listener
        animalClick6.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            ImgClicked(animalClick6)
        }

        val animalClick7 = findViewById(R.id.SeventhAnimal) as ImageView
        // set on-click listener
        animalClick7.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            ImgClicked(animalClick7)
        }

        val animalClick8 = findViewById(R.id.EighthAnimal) as ImageView
        // set on-click listener
        animalClick8.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            ImgClicked(animalClick8)
        }
        var extra = intent.extras

        if (extra != null) {
            score = extra!!.getInt("score")
        }
        findViewById<TextView>(R.id.score).text = "Score:" + score
        countDown()
    }

    private fun countDown() {
        timer = object : CountDownTimer(8000, 1000) {
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
                    val intent = Intent(this@Level5, Result_Score::class.java)
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
        animals.remove(animal2)
        val animal3 = animals.random();
        animals.remove(animal3)
        val animal4 = animals.random();
        animals.remove(animal4)
        val animal5 = animals.random();
        animals.remove(animal5)
        val animal6 = animals.random();
        animals.remove(animal6)
        val animal7 = animals.random();
        animals.remove(animal7)
        val animal8 = animals.random();

        val animalName = findViewById<TextView>(R.id.animalName)

        val rnds = (1..3).random()

        if (rnds == 1) {
            correctAns = animal1
        } else if (rnds == 2) {
            correctAns = animal2
        } else if (rnds == 3) {
            correctAns = animal3
        } else if (rnds == 4) {
            correctAns = animal4
        } else if (rnds == 5) {
            correctAns = animal5
        } else if (rnds == 6) {
            correctAns = animal6
        } else if (rnds == 8) {
            correctAns = animal7
        } else {
            correctAns = animal8
        }
        animalName.text = correctAns.name

        val firstAnimalView = findViewById<ImageView>(R.id.FirstAnimal)
        val secondAnimalView = findViewById<ImageView>(R.id.SecondAnimal)
        val thirdAnimalView = findViewById<ImageView>(R.id.ThirdAnimal)
        val fourthAnimalView = findViewById<ImageView>(R.id.FourthAnimal)
        val fifthAnimalView = findViewById<ImageView>(R.id.FifthAnimal)
        val sixthAnimalView = findViewById<ImageView>(R.id.SixthAnimal)
        val seventhAnimalView = findViewById<ImageView>(R.id.SeventhAnimal)
        val eighthAnimalView = findViewById<ImageView>(R.id.EighthAnimal)

        firstAnimalView.setImageResource(animal1.image!!)
        firstAnimalView.contentDescription = animal1.name
        secondAnimalView.setImageResource(animal2.image!!)
        secondAnimalView.contentDescription = animal2.name
        thirdAnimalView.setImageResource(animal3.image!!)
        thirdAnimalView.contentDescription = animal3.name
        fourthAnimalView.setImageResource(animal4.image!!)
        fourthAnimalView.contentDescription = animal4.name
        fifthAnimalView.setImageResource(animal5.image!!)
        fifthAnimalView.contentDescription = animal5.name
        sixthAnimalView.setImageResource(animal6.image!!)
        sixthAnimalView.contentDescription = animal6.name
        seventhAnimalView.setImageResource(animal7.image!!)
        seventhAnimalView.contentDescription = animal7.name
        eighthAnimalView.setImageResource(animal8.image!!)
        eighthAnimalView.contentDescription = animal8.name

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
            mediaPlayer = MediaPlayer.create(this@Level5, correctAns.sound!!)
            mediaPlayer!!.setVolume(0.15F, 0.15F)
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
                val intent = Intent(this, Result_Score::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            } else {
                counter++;
                loadAnimals();
            }

            mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
                if (status != TextToSpeech.ERROR) {
                    mTTS!!.language = Locale.UK

                    mTTS!!.speak("You got it", TextToSpeech.QUEUE_FLUSH, null)
                }
            })

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