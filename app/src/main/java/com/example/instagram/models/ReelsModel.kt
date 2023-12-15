package com.example.instagram.models

class Reels {

    var reelUrl : String? = null
    var reelCaption : String? = null
    var profileImg : String? = null

    constructor()
    constructor(reelUrl: String?, reelCaption: String?) {
        this.reelUrl = reelUrl
        this.reelCaption = reelCaption
    }

    constructor(reelUrl: String?, reelCaption: String?, profileImg : String?) {
        this.reelUrl = reelUrl
        this.reelCaption = reelCaption
        this.profileImg = profileImg
    }

}