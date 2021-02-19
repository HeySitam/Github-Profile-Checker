package com.sitamrock11.networkingdemo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users")
   suspend fun getAllUser(): Response<List<User>>
    @GET("users/{id}")
    suspend fun getUserById(@Path("id")id:String):Response<User>
}