package com.Try.MyApps

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.Try.MyApps.github.GithubUser
import com.Try.MyApps.github.RetrofitClient
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class ProfileActivity : AppCompatActivity() {
    private lateinit var imageViewAvatar: ImageView
    private lateinit var textViewLogin: TextView
    private lateinit var textViewName: TextView
    private lateinit var textViewBio: TextView
    private lateinit var textViewRepos: TextView
    private lateinit var textViewFollowers: TextView
    private lateinit var textViewFollowing: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        imageViewAvatar = findViewById(R.id.imageViewAvatar)
        textViewLogin = findViewById(R.id.textViewLogin)
        textViewName = findViewById(R.id.textViewName)
        textViewBio = findViewById(R.id.textViewBio)
        textViewRepos = findViewById(R.id.textViewRepos)
        textViewFollowers = findViewById(R.id.textViewFollowers)
        textViewFollowing = findViewById(R.id.textViewFollowing)

        // TODO: Ambil data pengguna GitHub dari Realtime Database atau API GitHub
        // Contoh: Ambil data pengguna dari API GitHub
        val githubApiClient = RetrofitClient.api
        RetrofitClient.api.getUser("github", "token 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").enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                if (response.isSuccessful) {
                    // Update UI dengan data pengguna GitHub yang diterima
                    val user = response.body()
                    user?.let {
                        Glide.with(this@ProfileActivity)
                            .load(user.avatar_url)
                            .into(imageViewAvatar)
                        textViewLogin.text = user.login
                        textViewName.text = user.name
                        textViewBio.text = user.bio
                        textViewRepos.text = user.public_repos.toString()
                        textViewFollowers.text = user.followers.toString()
                        textViewFollowing.text = user.following.toString()
                    }
                } else {
                    // Handle kesalahan
                    Toast.makeText(this@ProfileActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                // Handle kegagalan permintaan
                Toast.makeText(this@ProfileActivity, "Failure: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
