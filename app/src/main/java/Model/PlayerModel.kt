package Model

/**
 * Data class for player model
 */

data class PlayerModel(
    // Team details
    val teamName: String,
    val teamCity: String,
    val teamStadium: String,
    val teamCoach: String,
    val teamCaptain: String,

    // Player details
    val playerName: String,
    val playerRole: String,
    val playerNationality: String,
    val playerAge: Int,
    val playerMatches: Int,
    val playerRuns: Int,
    val playerWickets: Int,
    val playerBattingAverage: Double,
    val playerStrikeRate: Double,
    val playerCurrentForm: String
)
