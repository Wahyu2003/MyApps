package com.Try.MyApps.github

import com.Try.MyApps.database.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Callback
import retrofit2.Response

interface GithubService {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String, @Query("access_token") token: String): Call<GithubUser>
}
fun getGithubUser(username: String, token: String, callback: (GithubUser?) -> Unit) {
    RetrofitClient.api.getUser(username, token).enqueue(object : Callback<GithubUser> {
        override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
            if (response.isSuccessful) {
                // Jika berhasil, kembalikan data pengguna GitHub
                callback(response.body())
            } else {
                // Jika gagal, kembalikan null
                callback(null)
            }
        }

        override fun onFailure(call: Call<GithubUser>, t: Throwable) {
            // Jika terjadi kegagalan, kembalikan null
            callback(null)
        }
    })
}