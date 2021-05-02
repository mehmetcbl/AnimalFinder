package com.ceng427.animalfinder

class Animal {
    var name: String? = null
    var image: Int? = null
    var sound: Int? = null
    var names = arrayOf(
            "cat",
            "chicken",
            "cock",
            "cow",
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

    var images = arrayOf(
            R.drawable.cat,
            R.drawable.chicken,
            R.drawable.cock,
            R.drawable.cow,
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

    var sounds = arrayOf(
            R.raw.catsound,
            R.raw.chickensound,
            R.raw.cocksound,
            R.raw.cowsound,
            R.raw.crowsound,
            R.raw.dogsound,
            R.raw.donkeysound,
            R.raw.ducksound,
            R.raw.elephantsound,
            R.raw.frogsound,
            R.raw.horsesound,
            R.raw.lionsound,
            R.raw.monkeysound,
            R.raw.mousesound,
            R.raw.owlsound,
            R.raw.penguinsound,
            R.raw.pigsound,
            R.raw.sheepsound,
            R.raw.snakesound,
            R.raw.wolfsound
    )

    constructor(name: String?, image: Int?, sound: Int?) {
        this.name = name
        this.image = image
        this.sound = sound
    }

    constructor()

    override fun toString(): String {
        return "Animal(name=$name, image=$image, sound=$sound)"
    }

    fun getAnimals() : ArrayList<Animal>{

        val animals = ArrayList<Animal>()
        for (i in 0..(names.size-1)){
            animals.add(Animal(names[i],images[i],sounds[i]))
        }
        /*animals.add(Animal(names[0],images[0],sounds[0]))
        animals.add(Animal(names[1],images[1],sounds[1]))
        animals.add(Animal(names[2],images[2],sounds[2]))
        animals.add(Animal(names[3],images[3],sounds[3]))
        animals.add(Animal(names[4],images[4],sounds[4]))
        animals.add(Animal(names[5],images[5],sounds[5]))
        animals.add(Animal(names[6],images[6],sounds[6]))
        animals.add(Animal(names[7],images[7],sounds[7]))
        animals.add(Animal(names[8],images[8],sounds[8]))
        animals.add(Animal(names[9],images[9],sounds[9]))
        animals.add(Animal(names[10],images[10],sounds[10]))
        animals.add(Animal(names[11],images[11],sounds[11]))
        animals.add(Animal(names[12],images[12],sounds[12]))
        animals.add(Animal(names[13],images[13],sounds[13]))
        animals.add(Animal(names[14],images[14],sounds[14]))
        animals.add(Animal(names[15],images[15],sounds[15]))
        animals.add(Animal(names[16],images[16],sounds[16]))
        animals.add(Animal(names[17],images[17],sounds[17]))
        animals.add(Animal(names[18],images[18],sounds[18]))
        animals.add(Animal(names[19],images[19],sounds[19]))*/



        return animals
    }


}