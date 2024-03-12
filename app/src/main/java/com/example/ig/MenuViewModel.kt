package com.example.ig.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ig.Database.ApiConfig
import com.example.ig.Database.ItemsItem
import com.example.ig.Database.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel : ViewModel() {

    private val _GithubUser = MutableLiveData<List<ItemsItem?>?>()
    val GithubUser : LiveData<List<ItemsItem?>?> = _GithubUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MenuViewModel"
        private const val USERNAME = "fahriamura"
    }

    init {
        getGithubUser()
    }

    private fun getGithubUser() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().findUserByUsername(USERNAME)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _GithubUser.value = response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
