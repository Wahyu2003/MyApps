package com.Try.MyApps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.Try.MyApps.R

class SplashScreenn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screenn)

        val iv_note = findViewById<ImageView>(R.id.iv_note)


        iv_note.alpha =0f
        iv_note.animate().setDuration(1500).alpha(1f).withEndAction {
            val i = Intent(this, MotionActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}