package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.reycleViewDemo.RecycleViewOnClickListener
import com.example.myapplication.reycleViewDemo.MovieModel
import com.example.myapplication.reycleViewDemo.RecycleViewAdapter
import kotlinx.android.synthetic.main.activity_demo_init_main.*



class DemoInitMainActivity : AppCompatActivity(), RecycleViewOnClickListener {
    class StartActivityWithParams(val roomColor: String, val numOfRooms: Int) {
        companion object {
            private const val ROOM_COLOR_TAG = "roomColorTag"
            private const val NUM_OF_WALLS_TAG = "numOfWallsTag"
            private const val DEFAULT_NUM_OF_WALLS = 4

            fun createFromIntent(intent: Intent): StartActivityWithParams {
                return StartActivityWithParams(
                    roomColor = intent.getStringExtra(ROOM_COLOR_TAG),
                    numOfRooms = intent.getIntExtra(NUM_OF_WALLS_TAG, DEFAULT_NUM_OF_WALLS))
            }
        } // companion object

        fun startActivity(context: Context) {
            val intent = Intent(context, DemoInitMainActivity::class.java)
            intent.putExtra(ROOM_COLOR_TAG, roomColor)
            intent.putExtra(NUM_OF_WALLS_TAG, numOfRooms)
            context.startActivity(intent)
        }

    }

    private val movieList = ArrayList<MovieModel>()
    private lateinit var recycleViewAdapter: RecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_init_main)

        val args = StartActivityWithParams.createFromIntent(intent)
        Log.d("Shrek", args.roomColor)
        title = "Recycle View"

//        val tableView: RecyclerView = findViewById(R.id.recyclerView)
        recycleViewAdapter = RecycleViewAdapter(movieList, this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = recycleViewAdapter
        prepareMovieData()

    }

    private fun prepareMovieData() {
        var movie = MovieModel("Mad Max: Fury Road", "Action & Adventure", "2015")
        movieList.add(movie)
        movie = MovieModel("Inside Out", "Animation, Kids & Family", "2015")
        movieList.add(movie)
        movie = MovieModel("Star Wars: Episode VII - The Force Awakens", "Action", "2015")
        movieList.add(movie)
        movie = MovieModel("Shaun the Sheep", "Animation", "2015")
        movieList.add(movie)
        movie = MovieModel("The Martian", "Science Fiction & Fantasy", "2015")
        movieList.add(movie)
        movie = MovieModel("Mission: Impossible Rogue Nation", "Action", "2015")
        movieList.add(movie)
        movie = MovieModel("Up", "Animation", "2009")
        movieList.add(movie)
        movie = MovieModel("Star Trek", "Science Fiction", "2009")
        movieList.add(movie)
        movie = MovieModel("The LEGO MovieModel", "Animation", "2014")
        movieList.add(movie)
        movie = MovieModel("Iron Man", "Action & Adventure", "2008")
        movieList.add(movie)
        movie = MovieModel("Aliens", "Science Fiction", "1986")
        movieList.add(movie)
        movie = MovieModel("Chicken Run", "Animation", "2000")
        movieList.add(movie)
        movie = MovieModel("Back to the Future", "Science Fiction", "1985")
        movieList.add(movie)
        movie = MovieModel("Raiders of the Lost Ark", "Action & Adventure", "1981")
        movieList.add(movie)
        movie = MovieModel("Goldfinger", "Action & Adventure", "1965")
        movieList.add(movie)
        movie = MovieModel("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014")
        movieList.add(movie)
        recycleViewAdapter.notifyDataSetChanged()
    }

    override fun onDidSelected() {
        Toast.makeText(this,"Cell clicked", Toast.LENGTH_SHORT).show()
    }

    override fun didSelectedItem(position: Int) {
        Toast.makeText(this, "index: " + position, Toast.LENGTH_SHORT).show()
    }
}