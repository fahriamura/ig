package com.example.ig



import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ig.Helper.FancyAboutPage

class aboutme: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutme)
        title = "About Page"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        val fancyAboutPage: FancyAboutPage = findViewById(R.id.fancyaboutpage)
        fancyAboutPage.setCover(R.drawable.gd)
        fancyAboutPage.setName("Amura Maulidi Fachry")
        fancyAboutPage.setDescription("I'm a third-year Informatics student at Telkom University who's passionate about technology and dedicated to pursuing a career in Mobile Developer. With a strong foundation in Kotlin and Flutter.")
        fancyAboutPage.setAppIcon(R.drawable.github)
        fancyAboutPage.setAppName("Dicogram")
        fancyAboutPage.setVersionNameAsAppSubTitle("1.0.0")
        fancyAboutPage.setAppDescription("Cloning App based on Instagram, but used Github API rather than Instagram API")
        fancyAboutPage.addEmailLink("edogawaconax@gmail.com")
        fancyAboutPage.addLinkedinLink("https://www.linkedin.com/in/amura-maulidi-fachry-0978a9199/")
        fancyAboutPage.addGitHubLink("https://github.com/fahriamura")
        fancyAboutPage.addFacebookLink("https://www.instagram.com/sansssfahri/")
    }
}
