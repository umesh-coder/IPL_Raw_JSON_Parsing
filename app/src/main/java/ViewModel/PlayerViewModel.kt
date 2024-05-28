package com.example.ipldetails

import Model.PlayerModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

/**
 * @class PlayerViewModel
 *
 */

class PlayerViewModel(application: Application) : AndroidViewModel(application) {
    private val _playerList = MutableLiveData<List<PlayerModel>>()
    val playerList: LiveData<List<PlayerModel>> get() = _playerList

    init {
        loadPlayerData()
    }

    /**
     * @function loadPlayerData
     * to Load the json file and parse the data
     *
     */

    private fun loadPlayerData() {


        viewModelScope.launch(Dispatchers.IO) {
            val jsonFiles = getApplication<Application>().assets.list("json")

            val players = mutableListOf<PlayerModel>()
            jsonFiles?.forEach { jsonFileName ->
                val jsonString = getApplication<Application>().assets.open("json/$jsonFileName").bufferedReader().use { it.readText() }
                val jsonObject = JSONObject(jsonString)

                val teamsArray = jsonObject.getJSONObject("IPL").getJSONArray("teams")
                for (i in 0 until teamsArray.length()) {
                    val teamObject = teamsArray.getJSONObject(i)
                    val teamName = teamObject.getString("name")
                    val teamCity = teamObject.getString("city")
                    val teamStadium = teamObject.getString("stadium")
                    val teamCoach = teamObject.getString("coach")
                    val teamCaptain = teamObject.getString("captain")

                    val playersArray = teamObject.getJSONArray("players")
                    for (j in 0 until playersArray.length()) {
                        val playerObject = playersArray.getJSONObject(j)

                        val playerModel = PlayerModel(
                            playerName = playerObject.getString("name"),
                            playerAge = playerObject.getInt("age"),
                            playerNationality = playerObject.getString("nationality"),
                            playerCurrentForm = playerObject.getString("currentForm"),
                            teamStadium = teamStadium,
                            teamName = teamName,
                            playerRole = playerObject.getString("role"),
                            playerMatches = playerObject.getInt("matches"),
                            playerRuns = playerObject.getInt("runs"),
                            playerWickets = playerObject.getInt("wickets"),
                            playerBattingAverage = playerObject.optDouble("battingAverage", Double.NaN),
                            playerStrikeRate = playerObject.optDouble("strikeRate", Double.NaN),
                            teamCoach = teamCoach,
                            teamCaptain = teamCaptain,
                            teamCity = teamCity
                        )

                        players.add(playerModel)
                    }
                }
            }

            _playerList.postValue(players)
        }
    }
}
