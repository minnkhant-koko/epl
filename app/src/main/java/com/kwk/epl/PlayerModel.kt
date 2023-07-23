package com.kwk.epl

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerModel(
    @SerialName("player_number")
    val playerNumber: Long,
    @SerialName("full_name")
    val playerFullName: String
)