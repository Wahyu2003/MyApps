package com.Try.MyApps.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.Try.MyApps.R
import com.squareup.picasso.Picasso

class DetailUserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detail_user, container, false)

        val imageView = root.findViewById<ImageView>(R.id.imageView)
        val namaTextView = root.findViewById<TextView>(R.id.nama)
        val linkGitHubTextView = root.findViewById<TextView>(R.id.linkgithub)

        val args = arguments
        if (args != null) {
            val avatarUrl = args.getString("avatar_url", "")
            val namaPengguna = args.getString("nama_pengguna", "")
            val profilGitHub = args.getString("profil_github", "")

            // Isi ImageView dengan gambar avatar pengguna menggunakan Picasso
            Picasso.get().load(avatarUrl).into(imageView)

            // Isi TextView dengan data pengguna
            namaTextView.text = namaPengguna
            linkGitHubTextView.text = profilGitHub
        }

        val kembaliButton = root.findViewById<Button>(R.id.kembali)
        kembaliButton.setOnClickListener {
            // Tambahkan logika untuk kembali ke halaman sebelumnya
            requireActivity().supportFragmentManager.popBackStack()
        }

        return root
    }
}
