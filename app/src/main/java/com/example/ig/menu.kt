package com.example.ig

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ig.R
import com.example.ig.SplashyActivity.Companion.activity
import com.example.ig.databinding.FragmentMenuBinding
import com.google.android.material.search.SearchBar
import java.util.*
import kotlin.collections.ArrayList

class menu : Fragment(), SearchView.OnQueryTextListener{
    private lateinit var binding: FragmentMenuBinding
    private lateinit var mAdapter: ListDemonAdapter
    private lateinit var mContext: Context
    val mAllValues = ArrayList<ListIg>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()
        setHasOptionsMenu(true)
        mAllValues.addAll(getListDemon())

    }


    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val parentContainer = FrameLayout(requireContext())
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        parentContainer.addView(binding.root)
        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    Toast.makeText(requireContext(), searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
            mAdapter = ListDemonAdapter(mAllValues)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = mAdapter

        }

        // Assuming mAllValues is your data source for the RecyclerView



        return parentContainer
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        if (newText.isBlank()) {
            resetSearch()
            return false
        }

        val filteredValues = getListDemon().filter { value ->
            value.name.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))
        }.toMutableList()

        mAdapter.filterList(filteredValues as ArrayList<ListIg>)
        return false
    }

    private fun resetSearch() {
        mAdapter.filterList(getListDemon())
    }


    interface OnItem1SelectedListener {
        fun onItem1SelectedListener(item: String)
    }

    private fun getListDemon(): ArrayList<ListIg> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listDemon = ArrayList<ListIg>()
        for (i in dataName.indices) {
            val dicogram = ListIg(dataName[i],  dataPhoto.getResourceId(i, -1))
            listDemon.add(dicogram)
        }

        return listDemon
    }


}
