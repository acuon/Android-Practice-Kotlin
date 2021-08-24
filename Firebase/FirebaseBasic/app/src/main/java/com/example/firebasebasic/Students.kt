package com.example.firebasebasic

class Students {
    var name: String? = null
        private set
    var roll = 0
        private set

    internal constructor() {}
    constructor(name: String?, roll: Int) {
        this.name = name
        this.roll = roll
    }
}