package com.example.ig.Helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ig.ViewModel.FavAddUpdateViewModel
import com.example.ig.ViewModel.ThemeViewModel
import com.example.ig.ViewModel.ViewModel2

class ViewModelFactory(private val mApplication: Application, private val pref: SettingPreferences? = null) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application,pref: SettingPreferences? = null): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application,pref)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel2::class.java)) {
            return ViewModel2(mApplication) as T
        } else if (modelClass.isAssignableFrom(FavAddUpdateViewModel::class.java)) {
            return FavAddUpdateViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            return pref?.let { ThemeViewModel(it) } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}