package com.ceng427.animalfinder

class Animal {
    var name: String? = null
    var image: String? = null
    var sound: String? = null

    constructor(name: String?, image: String?, sound: String?) {
        this.name = name
        this.image = image
        this.sound = sound
    }

    override fun toString(): String {
        return "Animal(name=$name, image=$image, sound=$sound)"
    }


}