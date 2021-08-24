package com.example.firebasebasic

class User {

    var name: String? = null
        private set
    var email: String? = null
        private set

    internal constructor() {

    }

    constructor(name: String?, email: String?) {
        this.name = name
        this.email = email
    }
}