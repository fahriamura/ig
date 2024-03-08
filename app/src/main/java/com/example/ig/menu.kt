package com.example.ig

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.ListFragment
import com.example.ig.R
import java.util.*

class menu : ListFragment(), SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    private var mAllValues: MutableList<String> = ArrayList()
    private lateinit var mAdapter: ArrayAdapter<String>
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()
        setHasOptionsMenu(true)
        populateList()
    }

    override fun onListItemClick(listView: ListView, v: View, position: Int, id: Long) {
        val item = listView.adapter.getItem(position) as String
        if (activity is OnItem1SelectedListener) {
            (activity as OnItem1SelectedListener).onItem1SelectedListener(item)
        }
        parentFragmentManager.popBackStack()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_menu, container, false)
        val listView: ListView = layout.findViewById(android.R.id.list)
        val emptyTextView: TextView = layout.findViewById(android.R.id.empty)
        listView.emptyView = emptyTextView
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

        val filteredValues = mAllValues.filter { value ->
            value.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))
        }.toMutableList()

        mAdapter = ArrayAdapter(mContext, android.R.layout.simple_list_item_1, filteredValues)
        listAdapter = mAdapter

        return false
    }

    private fun resetSearch() {
        mAdapter = ArrayAdapter(mContext, android.R.layout.simple_list_item_1, mAllValues)
        listAdapter = mAdapter
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

    private fun populateList() {
        mAllValues = mutableListOf(
            "Afghanistan", "Åland Islands", "Albania", "Algeria", "American Samoa", "AndorrA", "Angola", "Anguilla",
            // ... (your country list continues here)
            "Yemen", "Zambia", "Zimbabwe"
        )

        mAdapter = ArrayAdapter(mContext, android.R.layout.simple_list_item_1, mAllValues)
        listAdapter = mAdapter
    }
}
