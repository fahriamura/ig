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
    val GithubUser: LiveData<List<ItemsItem?>?> = _GithubUser

    private val _Follower = MutableLiveData<List<ItemsItem?>?>()
    val Follower: LiveData<List<ItemsItem?>?> = _Follower

    private val _Following = MutableLiveData<List<ItemsItem?>?>()
    val Following: LiveData<List<ItemsItem?>?> = _Following

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

    fun getGithubUser(query: String) {
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

    fun getGithubDetail(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserDetail(username)
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

    fun getGithubFollowers(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowers(username)
        client.enqueue(object : Callback<ArrayList<ItemsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ItemsItem>>,
                response: Response<ArrayList<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _Follower.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getGithubFollowing(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowing(username)
        client.enqueue(object : Callback<ArrayList<ItemsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ItemsItem>>,
                response: Response<ArrayList<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _Following.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }


}
