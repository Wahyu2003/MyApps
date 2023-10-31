package com.Try.MyApps.ui.authentification

//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.Try.MyApps.MainActivity
//import com.Try.MyApps.R
//import com.Try.MyApps.database.UserDao
//import com.Try.MyApps.database.UserDatabase
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class LoginActivity : AppCompatActivity() {

    // Mendeklarasikan variabel untuk menyimpan instance dari UserDatabase dan UserDao
//    private var userDatabase: UserDatabase? = null
//    private var userDao: UserDao? = null
//    private lateinit var Inputusername: EditText
//    private lateinit var Inputpassword: EditText
//    private lateinit var btnLogin: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        // Menginisialisasi variabel dengan instance dari UserDatabase dan UserDao
//        userDatabase = UserDatabase.getInstance(this)
//        userDao = userDatabase?.userDao()
//        Inputusername = findViewById(R.id.username)
//        Inputpassword = findViewById(R.id.password)
//        btnLogin = findViewById(R.id.login)
//
//        // Menambahkan listener pada tombol login
//        btnLogin.setOnClickListener {
//            // Mendapatkan input username dan password dari user
//            val username = Inputusername.text.toString()
//            val password = Inputpassword.text.toString()
//
//            // Memeriksa apakah input username dan password tidak kosong
//            if (username.isNotEmpty() && password.isNotEmpty()) {
//                // Memanggil fungsi getUserByUsername dengan coroutine
//                CoroutineScope(Dispatchers.IO).launch {
//                    // Mendapatkan user dari database berdasarkan username
//                    val user = userDao?.getUserByUsername(username)
//
//                    // Menjalankan aksi di thread utama dengan withContext
//                    withContext(Dispatchers.Main) {
//                        // Memeriksa apakah user tidak null dan password sesuai
//                        if (user != null && user.password == password) {
//                            // Menampilkan pesan login berhasil
//                            Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
//                            // Membuat intent untuk pindah ke halaman selanjutnya
//                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                            // Memulai aktivitas baru dengan intent tersebut
//                            startActivity(intent)
//                        } else {
//                            // Menampilkan pesan login gagal
//                            Toast.makeText(this@LoginActivity, "Login gagal", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            } else {
//                // Menampilkan pesan input tidak valid
//                Toast.makeText(this, "Input tidak valid", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        // Menghapus instance dari UserDatabase
//        UserDatabase.destroyInstance()
//    }
//}
