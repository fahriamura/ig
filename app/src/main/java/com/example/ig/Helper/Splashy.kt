package com.example.ig.Helper


import android.app.Activity
import android.content.Intent
import android.widget.ImageView

class Splashy(private var activity: Activity) {

    private var intent: Intent = Intent(activity, SplashyActivity::class.java)

    fun setTime(time: Long = 2000): Splashy {
        intent.putExtra(SplashyActivity.TIME, time)
        return this
    }

    fun setDuration(time: Long = 2000): Splashy {
        intent.putExtra(SplashyActivity.TIME, time)
        return this
    }

    fun setInfiniteDuration(yes: Boolean): Splashy {
        intent.putExtra(SplashyActivity.INFINITE_TIME, yes)
        return this

    }


    fun showTitle(show: Boolean): Splashy {
        intent.putExtra(SplashyActivity.SHOW_TITLE, show)
        return this
    }

    fun setTitle(title: String): Splashy {
        intent.putExtra(SplashyActivity.TITLE, title)
        return this
    }



    fun setTitle(title: Int): Splashy {
        intent.putExtra(SplashyActivity.TITLE_RESOURCE, title)
        return this
    }


    fun setTitleColor(color: Int): Splashy {
        intent.putExtra(SplashyActivity.TITLE_COLOR, color)
        return this
    }


    fun setTitleColor(colorValue: String): Splashy {
        intent.putExtra(SplashyActivity.TITLE_COLOR_VALUE, colorValue)
        return this
    }

    fun setTitleSize(titleSize: Float): Splashy {
        intent.putExtra(SplashyActivity.TITLE_SIZE, titleSize)
        return this
    }

    fun setTitleFontStyle(fontName: String): Splashy {
        intent.putExtra(SplashyActivity.TITLE_FONT_STYLE, fontName)
        return this
    }


    fun setSubTitle(subtitle: String): Splashy {
        intent.putExtra(SplashyActivity.SUBTITLE, subtitle)
        return this
    }

    fun setSubTitle(subtitle: Int): Splashy {
        intent.putExtra(SplashyActivity.SUBTITLE_RESOURCE, subtitle)
        return this
    }


    fun setSubTitleColor(color: Int): Splashy {
        intent.putExtra(SplashyActivity.SUBTITLE_COLOR, color)
        return this
    }

    fun setSubTitleColor(colorValue: String): Splashy {
        intent.putExtra(SplashyActivity.SUBTITLE_COLOR_VALUE, colorValue)
        return this
    }

    fun setSubTitleSize(subtitleSize: Float): Splashy {
        intent.putExtra(SplashyActivity.SUBTITLE_SIZE, subtitleSize)
        return this
    }


    fun setSubTitleItalic(italic: Boolean): Splashy {
        intent.putExtra(SplashyActivity.SUBTITLE_ITALIC, italic)
        return this
    }

    fun setSubTitleFontStyle(fontName: String): Splashy {
        intent.putExtra(SplashyActivity.SUBTITLE_FONT_STYLE, fontName)
        return this
    }


    fun showLogo(show: Boolean): Splashy {
        intent.putExtra(SplashyActivity.SHOW_LOGO, show)
        return this
    }


    fun setLogo(logo: Int): Splashy {
        intent.putExtra(SplashyActivity.LOGO, logo)
        return this
    }

    fun setLogoWHinDp(width: Int, height: Int): Splashy {
        intent.putExtra(SplashyActivity.LOGO_WIDTH, width)
        intent.putExtra(SplashyActivity.LOGO_HEIGHT, height)
        return this
    }

    fun setLogoScaleType(scaleType: ImageView.ScaleType): Splashy {
        intent.putExtra(SplashyActivity.LOGO_SCALE_TYPE, scaleType)
        return this
    }


    fun showProgress(show: Boolean): Splashy {
        intent.putExtra(SplashyActivity.SHOW_PROGRESS, show)
        return this
    }

    fun setProgressColor(color: Int): Splashy {
        intent.putExtra(SplashyActivity.PROGRESS_COLOR, color)
        return this
    }


    fun setProgressColor(color: String): Splashy {
        intent.putExtra(SplashyActivity.PROGRESS_COLOR_VALUE, color)
        return this
    }



    fun setBackgroundColor(color: Int): Splashy {
        intent.putExtra(SplashyActivity.BACKGROUND_COLOR, color)
        return this
    }


    fun setBackgroundColor(colorValue: String): Splashy {
        intent.putExtra(SplashyActivity.BACKGROUND_COLOR_VALUE, colorValue)
        return this
    }

    fun setBackgroundResource(backgroundResource: Int): Splashy {
        intent.putExtra(SplashyActivity.BACKGROUND_RESOURCE, backgroundResource)
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
    fun setClickToHide(hide : Boolean) : Splashy {
        intent.putExtra(SplashyActivity.CLICK_TO_HIDE, hide)

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


        fun hide() {
            SplashyActivity.hideSplashy()
        }



    }


    interface OnComplete {
        fun onComplete()
    }


}