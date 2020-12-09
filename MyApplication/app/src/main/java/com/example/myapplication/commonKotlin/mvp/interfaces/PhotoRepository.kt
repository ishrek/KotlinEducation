package com.example.myapplication.commonKotlin.mvp.interfaces

import com.example.myapplication.commonKotlin.mvp.models.PhotoModel

public interface PhotoRepository {
    fun getPhotos(): List<PhotoModel>
    fun getListPhotos(byUsername: String): Boolean
}