import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ig.Database.ItemsItem
import com.example.ig.R
import com.example.ig.databinding.FragmentFollowingBinding
import com.example.ig.databinding.FragmentMenuBinding
import com.example.ig.desc
import com.example.ig.ui.MenuViewModel

class following : Fragment() {
    private lateinit var binding: FragmentFollowingBinding
    private lateinit var mAdapter: ListDemonAdapter
    private lateinit var viewModel: MenuViewModel
    private lateinit var username: String

    companion object {
        fun newInstance(username: String): following {
            val fragment = following()
            val args = Bundle()
            args.putString("username", username)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            username = it.getString("username") ?: ""
        }
        setHasOptionsMenu(false)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val recyclerView: RecyclerView = binding.rv2
        mAdapter = ListDemonAdapter()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mAdapter
        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        recyclerView.addItemDecoration(itemDecoration)

        mAdapter.setOnItemCLickCallback(object : ListDemonAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemsItem, position: Int) {
                val intent = Intent(requireContext(), desc::class.java)
                intent.putExtra("item_data", data.login)
                startActivity(intent)
            }
        })

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.getGithubFollowing(username)
        viewModel.Following.observe(this) { following ->
            following?.let {
                mAdapter.submitList(it)
            }
        }

    }

    private fun showLoading(isLoading: Boolean) {
        val progressBar = requireView().findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}