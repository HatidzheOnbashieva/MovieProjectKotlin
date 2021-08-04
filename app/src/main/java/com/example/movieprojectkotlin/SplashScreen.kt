package com.example.movieprojectkotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.movieprojectkotlin.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private var viewBinding: ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        Handler().postDelayed(Runnable() {
            run() {
                val homeActivity = Intent(this, MainActivity::class.java)
                startActivity(homeActivity)
            }
        },1000)

        finish()
    }
}