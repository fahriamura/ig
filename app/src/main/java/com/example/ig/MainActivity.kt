package com.example.ig
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        setSplashy()
        val homeFragment =menu()
        val fragment = supportFragmentManager.findFragmentByTag(menu::class.java.simpleName)
        if (fragment !is menu) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + menu::class.java.simpleName)
            supportFragmentManager
                .beginTransaction()
                .add(homeFragment, menu::class.java.simpleName)
                .commit()
        }
    }

    private fun setSplashy() {
        Splashy(this)
            .setAnimation(Splashy.Animation.GROW_LOGO_FROM_CENTER)
            .setBackgroundColor(R.color.white)
            .setTitle("Geometry Dash Demon List")
            .setTitleColor(R.color.white)
            .setTitleSize(40F)
            .setProgressColor(R.color.white)
            .setFullScreen(true)
            .setDuration(5000)
            .show()

        Splashy.onComplete(object : Splashy.OnComplete {
            override fun onComplete() {
                val intentToDemon = Intent(this@MainActivity, menu::class.java)
                startActivity(intentToDemon)
                finish() // Optional: finish the current activity if needed
            }
        })
    }


}

