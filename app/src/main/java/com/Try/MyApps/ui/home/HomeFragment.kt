package com.Try.MyApps.ui.home
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import androidx.appcompat.widget.SearchView
//import com.Try.MyApps.R
//import com.Try.MyApps.UserAdapter
//import com.Try.MyApps.databinding.FragmentHomeBinding
//import com.Try.MyApps.model.DataItem
//import com.Try.MyApps.model.ResponseUser
//import com.Try.MyApps.network.ApiConfig
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class HomeFragment : Fragment() {
//
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var adapter: UserAdapter
//    private lateinit var recyclerView: RecyclerView
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        recyclerView = root.findViewById(R.id.rv_users)
//        adapter = UserAdapter(mutableListOf())
//
//        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.adapter = adapter
//
//        setupSearchView()
//        getUser()
//
//        return root
//    }
//
//    private fun setupSearchView() {
//        val searchView = binding.searchView
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                val filteredData = filterDataByName(newText)
//                adapter.setData(filteredData)
//                return true
//            }
//        })
//    }
//
//    private fun filterDataByName(query: String?): List<DataItem> {
//        val dataArray = adapter.getData()
//        if (query.isNullOrBlank()) {
//            return dataArray
//        } else {
//            return dataArray.filter { user ->
//                (user.firstName + " " + user.lastName).contains(query, ignoreCase = true)
//            }
//        }
//    }
//
//    private fun getUser() {
//        val client = ApiConfig.getApiService().getListUsers("2")
//
//        client.enqueue(object : Callback<ResponseUser> {
//            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
//                if (response.isSuccessful) {
//                    val dataArray = response.body()?.data ?: emptyList()
//                    adapter.setData(dataArray)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
//                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
//                t.printStackTrace()
//            }
//        })
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Try.MyApps.R
import com.Try.MyApps.model.GitHubUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.Try.MyApps.network.ApiService

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var apiService: ApiService
    private lateinit var searchView: SearchView
    private lateinit var navController: NavController
    private lateinit var favoriteUserDao: FavoriteUserDao // Tambahkan ini
    private val userList: List<GitHubUser> = mutableListOf()

    private val searchHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        searchView = view.findViewById(R.id.searchView)
        recyclerView = view.findViewById(R.id.rv_users)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
        favoriteUserDao = FavoriteUserDatabase.getDatabase(requireContext()).favoriteUserDao() // Inisialisasi favoriteUserDao
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)

        setupSearchView()

        fetchDataFromGitHubApi()

        return view
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                // Tidak perlu tindakan khusus saat pengguna menekan tombol "Submit" pada keyboard
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Delayed search with a 1.5-second delay
                searchHandler.removeCallbacksAndMessages(null)
                if (!newText.isNullOrBlank()) {
                    searchHandler.postDelayed({
                        performSearch(newText)
                    }, 1500)
                }

                return true
            }
        })
    }

    private fun performSearch(query: String?) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getUsers()
                val filteredData = response.filter { user ->
                    user.login.contains(query.orEmpty(), ignoreCase = true)
                }
                withContext(Dispatchers.Main) {
                    val adapter = UserAdapter(filteredData) { user ->
                        openDetailUser(user)
                        toggleFavorite(user)
                    }
                    recyclerView.adapter = adapter
                }
            } catch (e: Exception) {
                // Handle errors
                e.printStackTrace()
            }
        }
    }

    private fun fetchDataFromGitHubApi() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getUsers()
                withContext(Dispatchers.Main) {
                    val adapter = UserAdapter(response) { user ->
                        openDetailUser(user)
                        toggleFavorite(user)
                    }
                    recyclerView.adapter = adapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle errors
            }
        }
    }

    private fun openDetailUser(user: GitHubUser) {
        val bundle = Bundle()
        bundle.putString("avatar_url", user.avatar_url)
        bundle.putString("nama_pengguna", user.login)
        bundle.putString("profil_github", user.html_url)

        val action = HomeFragmentDirections.actionNavigationHomeToDetailUserFragment()
        navController.navigate(action)
    }

    private fun toggleFavorite(user: GitHubUser) {
        user.isFavorite = !user.isFavorite // Toggle status favorit

        if (user.isFavorite) {
            // Tambahkan ke daftar favorit
            // Misalnya, jika Anda menggunakan Room untuk database, Anda bisa menambahkannya seperti ini:
            GlobalScope.launch(Dispatchers.IO) {
                val favoriteUser = FavoriteUser(
                    login = user.login,
                    avatar_url = user.avatar_url,
                    html_url = user.html_url
                )
                favoriteUserDao.insertFavoriteUser(favoriteUser)
            }
        } else {
            // Hapus dari daftar favorit
            // Misalnya, jika Anda menggunakan Room untuk database, Anda bisa menghapusnya seperti ini:
            GlobalScope.launch(Dispatchers.IO) {
                favoriteUserDao.deleteFavoriteUserByLogin(user.login)
            }
        }

        // Memanggil notifyItemChanged untuk memperbarui tampilan item yang bersangkutan
        val position = userList.indexOf(user)
        if (position != -1) {
            recyclerView.adapter?.notifyItemChanged(position)
        }
    }
}


