package com.example.ig

import ListDemonAdapter
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.ig.Database.ItemsItem
import com.example.ig.databinding.FragmentMenuBinding
import com.example.ig.ui.MenuViewModel
import java.util.*
import kotlin.collections.ArrayList

class menu : Fragment(){
    private lateinit var binding: FragmentMenuBinding
    private lateinit var mAdapter: ListDemonAdapter
    private lateinit var mContext: Context
    private lateinit var searchView: SearchView
    val mAllValues : List<ItemsItem?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()

        setHasOptionsMenu(true)
        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MenuViewModel::class.java
        )
        mainViewModel.GithubUser.observe(this) { consumerReviews ->
            getListUser(consumerReviews)
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }
    private fun showLoading(isLoading: Boolean) {
        val progressBar = requireView().findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
        override fun onDetach() {
            super.onDetach()
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {
            val layout = inflater.inflate(R.layout.fragment_menu, container, false)
            val recyclerView: RecyclerView = layout.findViewById(R.id.recyclerView)
            searchView = layout.findViewById(R.id.searchView)



            mAdapter = ListDemonAdapter(object : ListDemonAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ItemsItem, position: Int) {
                    // Handle item click
                }
            })
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = mAdapter


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterList(newText)
                    return true
                }

            })
            return layout
        }

    private fun filterList(query: String?) {
        query?.let { q ->
            val filteredList = ArrayList<ItemsItem>()
            for (item in mAllValues) {
                item?.login?.let { name ->
                    if (name.lowercase(Locale.ROOT).contains(q.lowercase(Locale.ROOT))) {
                        filteredList.add(item)
                    }
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                mAdapter.submitList(filteredList)
            }
        }
    }


        private fun getListUser(user: List<ItemsItem?>?) {
            val adapter = mAdapter
            adapter.submitList(user)
            binding.recyclerView.adapter=mAdapter
        }
    }


