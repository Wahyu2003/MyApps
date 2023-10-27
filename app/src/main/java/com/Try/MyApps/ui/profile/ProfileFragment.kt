package com.Try.MyApps.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.Try.MyApps.R
import com.Try.MyApps.databinding.FragmentProfileBinding
import com.Try.MyApps.ui.authentification.LoginActivity
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {
    private lateinit var profileViewModel: ProfileViewModel
    private var userId: Int = 0
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root = binding.root

        // Inisialisasi ViewModel dan DAO
        val userDao = AppDatabase.getInstance(requireContext()).userDao()
        val userRepository = UserRepository(userDao)
        profileViewModel = ViewModelProvider(
            this,
            ProfileViewModelFactory(userRepository)
        ).get(ProfileViewModel::class.java)

        // Mendapatkan data pengguna yang saat ini login
        profileViewModel.getLoggedInUser(userId).observe(viewLifecycleOwner, { user ->
            profileViewModel.setProfileImageUrl(user.profileImageUrl)
            binding.nameTextView.text = "Name: ${user.name}"
            binding.usernameTextView.text = "Username: ${user.username}"
            binding.emailTextView.text = "Email: ${user.email}"
            binding.githubTextview.text = "Github: ${user.github}"
        })

        // Handle Logout
        val logoutButton = binding.logoutButton
        logoutButton.setOnClickListener {
            // Implementasi logout di sini, misalnya:
            // 1. Hapus data sesi (token, dll.) untuk keluar dari sesi.
            // 2. Arahkan pengguna ke halaman login.
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
