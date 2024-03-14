package com.example.ig.Database

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(@Query("q") query: String):Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(@Path("username") username: String):Call<ItemsItem>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollowers(@Path("username") username: String):Call<ArrayList<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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fa")
    fun getUserFollowing(@Path("username") username: String):Call<ArrayList<ItemsItem>>
}