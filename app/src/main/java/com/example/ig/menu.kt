package com.example.ig

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.ig.R
import com.example.ig.SplashyActivity.Companion.activity
import com.example.ig.databinding.FragmentMenuBinding
import com.google.android.material.search.SearchBar
import java.util.*
import kotlin.collections.ArrayList

class menu : Fragment(){
    private lateinit var binding: FragmentMenuBinding
    private lateinit var mAdapter: ListDemonAdapter
    private lateinit var mContext: Context
    private lateinit var searchView: SearchView
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
        searchView = layout.findViewById(R.id.searchView)


        mAdapter = ListDemonAdapter(mAllValues)
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

        if (query != null) {
            val filteredList = ArrayList<ListIg>()
            for (i in mAllValues) {
                if (i.name.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                mAdapter.setFilteredList(filteredList)
            }
        }
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
