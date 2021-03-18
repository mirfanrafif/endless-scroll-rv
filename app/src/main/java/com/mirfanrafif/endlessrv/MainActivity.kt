package com.mirfanrafif.endlessrv

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        rv.addOnScrollListener( object:  EndlessScrollListener(layoutManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
            executor.execute {
                val awal = data.size
                for (i in 1..10) {
                    Thread.sleep(500)
                    data.add("Item ${awal + i}")
                }

                handler.post(Runnable {
                    rvAdapter.notifyItemInserted(data.size -1)
                })
            }
        }
    })
    }
}