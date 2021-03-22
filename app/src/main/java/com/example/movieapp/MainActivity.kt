package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            //TODO change this to list view
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, MovieDetailsFragment.newInstance(), "movieDetails")
                .commit()
        }
    }
}