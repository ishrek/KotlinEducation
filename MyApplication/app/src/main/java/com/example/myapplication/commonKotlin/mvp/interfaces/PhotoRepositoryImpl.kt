package com.example.myapplication.commonKotlin.mvp.interfaces

import android.content.Context
import com.example.myapplication.commonKotlin.mvp.models.PhotoModel


public class PhotoRepositoryImpl: PhotoRepository {
    private var context: Context? = null
    private var listData: List<PhotoModel> = getPhotos()

    constructor (context: Context?) {
        this.context = context
    }
    override fun getPhotos(): List<PhotoModel> {
        val listData: MutableList<PhotoModel>
        val imageLink =
            "http://www.chomeo.com/wp-content/uploads/2016/04/huan-luyen-cho-khong-can-bay.jpg"
        listData = ArrayList()
        listData.add(PhotoModel(imageLink, "Mèo", "Đây là con mèo!"))
        listData.add(PhotoModel(imageLink, "Chó", "Đây là con mèo!"))
        listData.add(PhotoModel(imageLink, "Gà", "Đây là con mèo!"))
        listData.add(PhotoModel(imageLink, "Lợn", "Đây là con mèo!"))
        listData.add(PhotoModel(imageLink, "Bò", "Đây là con mèo!"))
        listData.add(PhotoModel(imageLink, "Trâu", "Đây là con mèo!"))
        listData.add(PhotoModel(imageLink, "Gà", "Đây là con mèo!"))
        listData.add(PhotoModel(imageLink, "Ngựa", "Đây là con mèo!"))
        listData.add(PhotoModel(imageLink, "Dê", "Đây là con mèo!"))
        listData.add(PhotoModel(imageLink, "Rắn", "Đây là con mèo!"))
        return listData
    }

    override fun getListPhotos(byUsername: String): Boolean {
        for (index in listData.indices) {
            val model = listData[index]
            if (byUsername == model.name) {
                return true
            }
        }
        return false
    }
}