import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ig.Database.ItemsItem
import com.example.ig.R
import com.example.ig.databinding.FragmentFollowerBinding
import com.example.ig.desc
import com.example.ig.ViewModel.MenuViewModel

class follower : Fragment() {
    private lateinit var binding: FragmentFollowerBinding
    private lateinit var mAdapter: ListDemonAdapter
    private lateinit var viewModel: MenuViewModel
    private lateinit var username: String
    companion object {
        fun newInstance(username: String): follower {
            val fragment = follower()
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
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        val recyclerView: RecyclerView = binding.rv1
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

        viewModel.getGithubFollowers(username)
        viewModel.Follower.observe(this) { followers ->
            followers?.let {
                mAdapter.submitList(it)

            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        val progressBar = requireView().findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
