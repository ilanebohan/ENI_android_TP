package com.example.tp_01.bo

import java.util.Date
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article (var id:Long, var titre:String, var description:String, var prix:Double, var urlImage:String, var dateSortie: Date) :
    Parcelable {

    constructor() : this(0, "", "", 0.0, "", Date())
    {

    }

    override fun toString(): String {
           return "Article(id=$id, titre='$titre', description='$description', prix=$prix, urlImage='$urlImage', dateSortie=$dateSortie)"
    }
}