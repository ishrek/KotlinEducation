package com.example.myapplication.reycleViewDemo

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.myapplication.R

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    @BindView(R.id.title)
    lateinit var title: TextView

    @BindView(R.id.year)
    lateinit var year: TextView

    @BindView(R.id.genre)
    lateinit var genre: TextView

    @BindView(R.id.btnSave)
    lateinit var saveBtn: Button

    @BindView(R.id.btnGetPosition)
    lateinit var getPositionBtn: Button

    private var movie: MovieModel? = null
    private var v: View = view

    init {
        ButterKnife.bind(this, view)
        v.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d("iShrek", "onClick")
    }

    fun bindPhoto(movie: MovieModel) {
        this.movie = movie
        this.title.text = movie.title
        this.year.text = movie.year
        this.genre.text = movie.genre
    }
}