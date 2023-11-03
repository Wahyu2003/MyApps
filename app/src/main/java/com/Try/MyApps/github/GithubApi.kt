package com.Try.MyApps.github
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<GithubUser>

    companion object {
        @JvmStatic
        fun getUserSync(username: String): GithubUser { // Mengubah nama metode
            // Create a Retrofit instance
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            // Create a GithubApi service
            val service = retrofit.create(GithubApi::class.java)
            // Make a synchronous call to the API
            val response = service.getUser(username).execute()
            // Check if the response is successful
            if (response.isSuccessful) {
                // Return the user object from the response body
                return response.body() ?: throw Exception("User not found")
            } else {
                // Throw an exception with the error message
                throw Exception(response.message())
            }
        }
    }

}
