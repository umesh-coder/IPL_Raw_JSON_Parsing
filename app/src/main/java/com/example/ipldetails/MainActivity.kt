package com.example.ipldetails

import Adapter.PlayerCustomAdapter
import Model.PlayerModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val playerViewModel: PlayerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Ensure you have set the correct layout

        val recyclerView: RecyclerView = findViewById(R.id.Player_Recyler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = PlayerCustomAdapter(listOf())
        recyclerView.adapter = adapter

        playerViewModel.playerList.observe(this, Observer { playerList ->
            adapter.updatePlayerList(playerList)
        })

    }

}
