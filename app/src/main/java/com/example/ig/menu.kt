package com.example.ig

import ListDemonAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ig.Database.ItemsItem
import com.example.ig.ViewModel.MenuViewModel
import com.example.ig.databinding.FragmentMenuBinding

class menu : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var mAdapter: ListDemonAdapter
    private lateinit var mContext: Context
    private lateinit var searchView: SearchView
    private lateinit var viewModel: MenuViewModel
    private var mAllValues: List<ItemsItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()

        setHasOptionsMenu(true)
        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MenuViewModel::class.java]
        mainViewModel.GithubUser.observe(this) { consumerReviews ->
            mAllValues = (consumerReviews ?: emptyList()) as List<ItemsItem>
            filterList(searchView.query.toString())
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        val progressBar = requireView().findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        val recyclerView: RecyclerView = binding.recyclerView
        searchView = binding.searchView
        viewModel = ViewModelProvider(this)[MenuViewModel::class.java]
        mAdapter = ListDemonAdapter()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mAdapter
        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        recyclerView.addItemDecoration(itemDecoration)
        mAdapter.setOnItemCLickCallback(object : ListDemonAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemsItem, position: Int) {
                val intent = Intent(requireContext(), desc::class.java)
                intent.putExtra("item_data", data.login)
                intent.putExtra("avatar",data.avatarUrl)
                startActivity(intent)
            }
        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    showLoading(true)
                    viewModel.getGithubUser(query)
                }
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
        return binding.root
    }

    private fun filterList(query: String?) {
        query?.let { q ->
            val filteredList = mAllValues.filter { item ->
                item.login?.contains(q, ignoreCase = true) ?: false
            }
            mAdapter.submitList(filteredList)
            showLoading(false)
        }
    }
}
