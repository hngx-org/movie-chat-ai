package com.essycynthia.moviechat.data

import java.util.Calendar


data class Message(
    var text: String? = null,
    var recipientId: String,
    var time: Long = Calendar.getInstance().timeInMillis,
    var isOut: Boolean = false


)

val messageDummy = listOf<Message>(
    Message(text = "Great!", recipientId = "bot", isOut = false),
    Message(text = "I am Good!", recipientId = "user", isOut = true),
    Message(text = "Hi,How are you?", recipientId = "bot", isOut = false),
    Message(text = "Hi", recipientId = "user", isOut = true)
)
