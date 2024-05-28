package Adapter

import Model.PlayerModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor

import androidx.recyclerview.widget.RecyclerView
import com.example.ipldetails.R
import com.example.ipldetails.databinding.RecyclerCustomItemBinding


class PlayerCustomAdapter(var playersList: List<PlayerModel>) : RecyclerView.Adapter<PlayerCustomAdapter.PlayerViewHolder>() {

    class PlayerViewHolder (val binding: RecyclerCustomItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(player: PlayerModel) {

            when (player.teamName) {
                "Chennai Super Kings" -> {
                    binding.root.setBackgroundColor(getColor(binding.root.context, R.color.CSK))
                    binding.teamCaptain.setTextColor(getColor(binding.root.context, R.color.black))
                    binding.teamCity.setTextColor(getColor(binding.root.context, R.color.black))
                    binding.teamName.setTextColor(getColor(binding.root.context, R.color.black))

                }
                "Mumbai Indians" -> {
                    binding.root.setBackgroundColor(getColor(binding.root.context, R.color.MI))
                }
                "Royal Challengers Bangalore" -> {
                    binding.root.setBackgroundColor(getColor(binding.root.context, R.color.RCB))

                }
                // Add more cases for other teams
                else -> {
                    binding.root.setBackgroundColor(getColor(binding.root.context, R.color.black))
                }
            }

            binding.teamCity.text = "Team City : "+player.teamCity
            binding.teamName.text = "Team Name : "+player.teamName
            binding.playerName.text = "Player Name: "+player.playerName
            binding.playerNationality.text = "Nationality: "+player.playerNationality
            binding.playerRole.text = "Role: "+player.playerRole
            binding.playerRuns.text = "Runs: "+player.playerRuns.toString()
            binding.playerStrikeRate.text = "Strike Rate: "+player.playerStrikeRate.toString()
            binding.playerMatches.text = "Matches: "+player.playerMatches.toString()
            binding.playerAge.text = "Age: "+player.playerAge.toString()
            binding.playerWickets.text = "Wickets: "+player.playerWickets.toString()
            binding.playerCurrentForm.text = "Current Form : "+player.playerCurrentForm
            binding.teamCaptain.text="Captain: "+player.teamCaptain

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
       val binding = RecyclerCustomItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
      holder.bind(playersList.get(position))
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    fun updatePlayerList(newplayerList: List<PlayerModel>?) {
        playersList = newplayerList!!
        notifyDataSetChanged()
    }


}