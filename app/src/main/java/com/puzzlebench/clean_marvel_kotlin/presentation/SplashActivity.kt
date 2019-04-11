package com.puzzlebench.clean_marvel_kotlin.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.puzzlebench.clean_marvel_kotlin.R.layout.activity_splash)

        val rotateAnimation = AnimationUtils.loadAnimation(applicationContext, com.puzzlebench.clean_marvel_kotlin.R.anim.rotating_image)
        image_rotation.startAnimation(rotateAnimation)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        }, SPLASH_DURATION)
    }

    companion object {
        const val SPLASH_DURATION: Long = 2000
    }
}
