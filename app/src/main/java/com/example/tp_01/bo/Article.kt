package com.example.tp_01.bo

import java.util.Date
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Article (
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var titre:String,
    var description:String,
    var prix:Double,
    var urlImage:String,
    var dateSortie: Date) :
    Parcelable {

    constructor() : this(0, "", "", 0.0, "", Date())
    {

    }

    override fun toString(): String {
           return "Article(id=$id, titre='$titre', description='$description', prix=$prix, urlImage='$urlImage', dateSortie=$dateSortie)"
    }
}