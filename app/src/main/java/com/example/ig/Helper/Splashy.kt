package com.example.ig.Helper


import android.app.Activity
import android.content.Intent
import com.example.ig.SplashyActivity

class Splashy(private var activity: Activity) {

    private var intent: Intent = Intent(activity, SplashyActivity::class.java)

    fun setDuration(time: Long = 2000): Splashy {
        intent.putExtra(SplashyActivity.TIME, time)
        return this
    }



    fun setTitle(title: String): Splashy {
        intent.putExtra(SplashyActivity.TITLE, title)
        return this
    }


    fun setTitleColor(color: Int): Splashy {
        intent.putExtra(SplashyActivity.TITLE_COLOR, color)
        return this
    }


    fun setTitleSize(titleSize: Float): Splashy {
        intent.putExtra(SplashyActivity.TITLE_SIZE, titleSize)
        return this
    }


    fun setLogo(logo: Int): Splashy {
        intent.putExtra(SplashyActivity.LOGO, logo)
        return this
    }


    fun setProgressColor(color: Int): Splashy {
        intent.putExtra(SplashyActivity.PROGRESS_COLOR, color)
        return this
    }


    fun setBackgroundColor(color: Int): Splashy {
        intent.putExtra(SplashyActivity.BACKGROUND_COLOR, color)
        return this
    }


    fun setAnimation(type: Animation, duration: Long = 800): Splashy {
        intent.putExtra(SplashyActivity.ANIMATION_TYPE, type)
        intent.putExtra(SplashyActivity.ANIMATION_DURATION, duration)
        return this
    }


    fun setFullScreen(yes: Boolean)  : Splashy {
        intent.putExtra(SplashyActivity.FULL_SCREEN, yes)
        return this
    }


    fun show() {
        activity.startActivity(intent)


    }

    enum class Animation {
        SLIDE_IN_TOP_BOTTOM, SLIDE_IN_LEFT_BOTTOM, SLIDE_IN_LEFT_RIGHT,SLIDE_LEFT_ENTER, GLOW_LOGO, GLOW_LOGO_TITLE, GROW_LOGO_FROM_CENTER
    }

    companion object {


        fun onComplete(getComplete: OnComplete) {
            val splashy = SplashyActivity()
            splashy.setOnComplete(getComplete)
        }


    }


    interface OnComplete {
        fun onComplete()
    }


}