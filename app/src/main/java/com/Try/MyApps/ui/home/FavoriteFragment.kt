import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Try.MyApps.R
import com.Try.MyApps.ui.home.FavoriteUser
import com.Try.MyApps.ui.home.FavoriteUserAdapter
import com.Try.MyApps.ui.home.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        recyclerView = view.findViewById(R.id.rvFavoriteUsers)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = FavoriteUserAdapter { favoriteUser ->
            // Handle item click
        }

        recyclerView.adapter = adapter

        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        favoriteViewModel.allFavoriteUsers.observe(viewLifecycleOwner, { favoriteUsers ->
            adapter.submitList(favoriteUsers)
        })

        return view
    }
}
