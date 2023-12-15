package com.example.instagram.models

class Post{
    var postUrl : String? = null
    var postCaption : String? = null
    var profilePhoto : String? = null
    var userName : String? = null

    constructor()
    constructor(postUrl: String?, postCaption: String?) {
        this.postUrl = postUrl
        this.postCaption = postCaption
    }

    constructor(postUrl: String?, postCaption: String?, profilePhoto: String?, userName: String?) {
        this.postUrl = postUrl
        this.postCaption = postCaption
        this.profilePhoto = profilePhoto
        this.userName = userName
    }


}

