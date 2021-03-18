package com.mirfanrafif.endlessrv

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = arrayListOf<String>()

        for (i in 1..10) {
            data.add("Item $i")
        }

        rv = findViewById(R.id.recyclerView)
        val rvAdapter = RvAdapter(data)
        val layoutManager = LinearLayoutManager(this)

        rv.layoutManager = layoutManager
        rv.adapter = rvAdapter


        rv.addOnScrollListener( object:  EndlessScrollListener(layoutManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
            lifecycleScope.launch(Dispatchers.Default) {
                val awal = data.size
                for (i in 1..10) {
                    delay(500)
                    Log.d("MakeData", "Make data ${awal + i}")
                    data.add("Item ${awal + i}")
                }

                withContext(Dispatchers.Main) {
                    rvAdapter.notifyItemRangeInserted(data.size - 1, 10)
                }
            }
        }
    })
    }
}