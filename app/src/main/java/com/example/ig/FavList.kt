package com.example.ig

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ig.Adapter.FavAdapter
import com.example.ig.Helper.ViewModelFactory
import com.example.ig.ViewModel.ViewModel2
import com.example.ig.databinding.FavListBinding
import com.example.ig.db.Fav

class FavList : AppCompatActivity() {

    private lateinit var adapter: FavAdapter

    private lateinit var binding: FavListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = FavAdapter()
        val mainViewModel = obtainViewModel(this@FavList)
        mainViewModel.getAllFav().observe(this) { Fav ->
            if (Fav != null) {
                adapter.submitList(Fav)
                if (Fav.isEmpty()) {
                    binding.textView2.visibility = View.VISIBLE
                    binding.top10.visibility = View.GONE
                } else {
                    binding.textView2.visibility = View.GONE
                    binding.top10.visibility = View.VISIBLE
                }
            }
        }
        binding.top10.layoutManager = LinearLayoutManager(this)
        binding.top10.adapter = adapter
        binding.top10.setHasFixedSize(true)
        adapter.setOnItemCLickCallback(object : FavAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Fav, position: Int) {
                val intent = Intent(this@FavList, desc::class.java)
                intent.putExtra("item_data", data.username)
                startActivity(intent)
            }
        })

    }
    private fun obtainViewModel(activity: AppCompatActivity): ViewModel2 {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[ViewModel2::class.java]
    }

}
