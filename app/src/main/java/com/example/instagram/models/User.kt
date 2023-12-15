package com.example.instagram.models

class User {
    var profileImg : String ? = null
    var name : String ? = null
    var email : String? = null
    var password : String? = null

    constructor()
    constructor(profileImg: String?, name: String?, email: String?, password: String?) {
        this.profileImg = profileImg
        this.name = name
        this.email = email
        this.password = password
    }

    constructor(name: String?, email: String?, password: String?) {
        this.name = name
        this.email = email
        this.password = password
    }


}