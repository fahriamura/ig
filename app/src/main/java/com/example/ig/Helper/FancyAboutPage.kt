package com.example.ig.Helper


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.ig.R
import com.intrusoft.squint.DiagonalView

class FancyAboutPage : RelativeLayout {
    private lateinit var l1: TextView
    private lateinit var l2: TextView
    private lateinit var l3: TextView
    private lateinit var l4: TextView
    private lateinit var l5: TextView
    private lateinit var diagonalView: DiagonalView
    private lateinit var img: ImageView
    private lateinit var email: ImageView
    private lateinit var fb: ImageView
    private lateinit var tw: ImageView
    private lateinit var lin: ImageView
    private lateinit var git: ImageView
    private var fburl: String? = null
    private var linkedinurl: String? = null
    private var githuburl: String? = null

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.aboutme, this, true)
        l1 = findViewById(R.id.name)
        l2 = findViewById(R.id.description)
        l3 = findViewById(R.id.appname)
        l4 = findViewById(R.id.appversion)
        l5 = findViewById(R.id.appdescription)
        img = findViewById(R.id.appicon)
        email = findViewById(R.id.imageView)
        fb = findViewById(R.id.imageView2)
        tw = findViewById(R.id.imageView3)
        lin = findViewById(R.id.imageView4)
        git = findViewById(R.id.imageView5)
        diagonalView = findViewById(R.id.background)
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }

    fun setName(name: String) {
        l1.text = name
    }

    fun setDescription(description: String) {
        l2.text = description
    }

    fun setCover(drawable: Int) {
        diagonalView.setImageResource(drawable)
    }

    fun addEmailLink(emailAddress: String) {
        email.setOnClickListener {
            Toast.makeText(context, emailAddress, Toast.LENGTH_SHORT).show()
        }
    }

    fun addFacebookLink(facebookAddress: String) {
        fb.visibility = View.VISIBLE
        fburl = facebookAddress
        fb.setOnClickListener {
            if (fburl?.let { !it.startsWith("http://") && !it.startsWith("https://") } == true) {
                fburl = "http://$fburl"
            }
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(fburl))
            context.startActivity(browserIntent)
        }
    }

    fun addLinkedinLink(linkedinAddress: String) {
        lin.visibility = View.VISIBLE
        linkedinurl = linkedinAddress
        lin.setOnClickListener {
            if (linkedinurl?.let { !it.startsWith("http://") && !it.startsWith("https://") } == true) {
                linkedinurl = "http://$linkedinurl"
            }
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedinurl))
            context.startActivity(browserIntent)
        }
    }

    fun addGitHubLink(githubAddress: String) {
        git.visibility = View.VISIBLE
        githuburl = githubAddress
        git.setOnClickListener {
            if (githuburl?.let { !it.startsWith("http://") && !it.startsWith("https://") } == true) {
                githuburl = "http://$githuburl"
            }
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(githuburl))
            context.startActivity(browserIntent)
        }
    }

    fun setAppIcon(icon: Int) {
        img.setImageResource(icon)
    }

    fun setAppName(appName: String) {
        l3.text = appName
    }

    @SuppressLint("SetTextI18n")
    fun setVersionNameAsAppSubTitle(appVersion: String) {
        l4.text = "Version $appVersion"
    }

    fun setAppDescription(appDescription: String) {
        l5.text = appDescription
    }
}
