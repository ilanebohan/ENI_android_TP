package com.example.tp_01

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.tp_01.bo.Article
import com.example.tp_01.dao.ArticleDAO
import com.example.tp_01.utils.DateConverter

@Database(entities = [Article::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articledao() : ArticleDAO

    companion object {
        private var INSTANCE : AppDatabase? = null;

        fun getInstance(context : Context) : AppDatabase{
            var instance = INSTANCE;
            if(instance == null){
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "articles")
                    .fallbackToDestructiveMigration().build();

                INSTANCE = instance;
            }
            return instance;
        }
    }


}