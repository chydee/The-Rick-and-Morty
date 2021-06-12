package com.chidi.therickandmorty.presentation.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.chidi.therickandmorty.R
import com.chidi.therickandmorty.databinding.ActivityLauncherBinding
import com.chidi.therickandmorty.presentation.utils.Constants.SPLASH_TIMEOUT

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            // Check if we're running on Android 5.0 or higher
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // Apply activity transition
                startActivity(Intent(this, MainActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            } else {
                // Swap without transition
                startActivity(Intent(this, MainActivity::class.java))
            }

        }, SPLASH_TIMEOUT)

        val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        binding.appLogo.startAnimation(anim)
        binding.appQuoteTextView.startAnimation(anim)
    }
}