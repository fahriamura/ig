package com.example.ig
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ig.Helper.Splashy


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        setSplashy()

    }

    private fun setSplashy() {
        Splashy(this)
            .setAnimation(Splashy.Animation.GROW_LOGO_FROM_CENTER)
            .setBackgroundColor(R.color.white)
            .setLogo(R.drawable.github)
            .setTitle("Dicogram")
            .setTitleColor(R.color.black)
            .setTitleSize(40F)
            .setProgressColor(R.color.white)
            .setFullScreen(true)
            .setDuration(5000)
            .show()

        Splashy.onComplete(object : Splashy.OnComplete {
            override fun onComplete() { val intentToDemon = Intent(this@MainActivity, mainapp::class.java)
                startActivity(intentToDemon)
                finish()
            }
        })
    }


}

