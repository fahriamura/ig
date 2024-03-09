package com.example.ig

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ig.R
import com.example.ig.SplashyActivity.Companion.activity
import java.util.*
import kotlin.collections.ArrayList

class menu : Fragment(), SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

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
        val layout = inflater.inflate(R.layout.fragment_menu, container, false)
        val recyclerView: RecyclerView = layout.findViewById(R.id.recyclerView)

        // Assuming mAllValues is your data source for the RecyclerView

        mAdapter = ListDemonAdapter(mAllValues)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mAdapter


        return layout
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search"
        super.onCreateOptionsMenu(menu, inflater)
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

    override fun onMenuItemActionExpand(item: MenuItem): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
        return true
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
