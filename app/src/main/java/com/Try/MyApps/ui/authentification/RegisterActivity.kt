package com.Try.MyApps.ui.authentification

//import android.content.Intent
//import android.os.Bundle
//import android.util.Patterns
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.Try.MyApps.R
//import com.Try.MyApps.database.User
//import com.Try.MyApps.database.UserDao
//import com.Try.MyApps.database.UserDatabase
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.util.concurrent.Executors
//class RegisterActivity : AppCompatActivity() {

//    // Mendeklarasikan variabel untuk menyimpan instance dari UserDatabase dan UserDao
//    private var userDatabase: UserDatabase? = null
//    private var userDao: UserDao? = null
//    private lateinit var et_username: EditText
//    private lateinit var et_password: EditText
//    private lateinit var et_github: EditText
//    private lateinit var et_email: EditText
//    private lateinit var et_button_register: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//
//        // Menginisialisasi variabel dengan instance dari UserDatabase dan UserDao
//        userDatabase = UserDatabase.getInstance(this)
//        userDao = userDatabase?.userDao()
//
//
//        et_username = findViewById(R.id.etUsername)
//        et_password = findViewById(R.id.etPassword)
//        et_github = findViewById(R.id.etGithubUsername)
//        et_email = findViewById(R.id.etEmail)
//        et_button_register = findViewById(R.id.btnRegister)
//
//        // Menambahkan listener pada tombol register
//
//            // Mendeklarasikan variabel untuk menyimpan instance dari UserDatabase dan UserDa
//
//                // Menginisialisasi variabel dengan instance dari UserDatabase dan UserDao
//                userDatabase = UserDatabase.getInstance(this)
//                userDao = userDatabase?.userDao()
//
//                // Menambahkan listener pada tombol register
//                et_button_register.setOnClickListener {
//                    // Mendapatkan input username, password, github, dan email dari user
//                    val username = et_username.text.toString()
//                    val password = et_password.text.toString()
//                    val github = et_github.text.toString()
//                    val email = et_email.text.toString()
//
//                    // Memeriksa apakah input tidak kosong dan valid
//                                        // Memeriksa apakah input tidak kosong dan valid
//                                        if (username.isNotEmpty() && password.isNotEmpty() && github.isNotEmpty() && email.isNotEmpty()) {
//
//                                            // Memanggil fungsi insertUser dengan coroutine
//                                            GlobalScope.launch (Dispatchers.IO){
//                                                // Menyimpan user ke database dengan metode insertUser
//                                                val exitinguser = userDao?.getUserByUsername(username)
//                                                if (exitinguser == null) {
//                                                    val newUser = User(
//                                                        username = username,
//                                                        password = password,
//                                                        github = github,
//                                                        email = email )
//                                                    userDao?.insertUser(newUser)
//                                                    runOnUiThread {
//                                                        Toast.makeText(
//                                                            this@RegisterActivity,
//                                                            "Registrasi berhasil",
//                                                            Toast.LENGTH_SHORT
//                                                        ).show()
//                                                        val intent =
//                                                            Intent(this@RegisterActivity, LoginActivity::class.java)
//                                                        startActivity(intent)
//                                                        finish()
//                                                    }
//                                                } else {
//                                                    runOnUiThread {
//                                                        Toast.makeText(
//                                                            this@RegisterActivity,
//                                                            "Email sudah terdaftar",
//                                                            Toast.LENGTH_SHORT
//                                                        ).show()
//                                                    }
//                                                }
//                                            }
//                                        } else {
//                                            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
//                                        }
//                                    }
//}