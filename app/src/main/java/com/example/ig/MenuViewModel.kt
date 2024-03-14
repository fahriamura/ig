package com.example.ig.ui

import android.content.ClipData.Item
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
        private const val Search = ""
        private const val USERNAME = "fahriamura"
    }

    init {
        getGithubUser(Search)
    }

    fun getGithubUser(query : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchUser(query)
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

    fun getGithubDetail(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserDetail(USERNAME)
        client.enqueue(object : Callback<ItemsItem> {
            override fun onResponse(
                call: Call<ItemsItem>,
                response: Response<ItemsItem>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val userDetails = response.body()
                    _GithubUser.value = listOf(userDetails)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ItemsItem>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getUsersList(): MutableLiveData<List<ItemsItem?>?> {
        return _GithubUser
    }

    fun getUsersInfo(): LiveData<List<ItemsItem?>?> {
        return GithubUser
    }
}
