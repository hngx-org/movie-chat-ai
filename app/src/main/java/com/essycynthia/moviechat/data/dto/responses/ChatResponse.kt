package com.essycynthia.moviechat.data.dto.responses

import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("history")
    var history: ArrayList<String> = arrayListOf(),
    @SerializedName("user_input")
    var userInput: String? = null
)