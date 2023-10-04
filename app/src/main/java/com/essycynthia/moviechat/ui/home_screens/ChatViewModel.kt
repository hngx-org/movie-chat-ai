package com.essycynthia.moviechat.ui.home_screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.essycynthia.moviechat.data.Message

class ChatViewModel : ViewModel() {
    val messages = mutableStateOf(mutableListOf<Message>())
    var messagesSent = 0
    var showPaymentDialog = false

    fun sendMessage(userMessage: String, onUserMessageSent: (String) -> Unit) {
        if (userMessage.isNotBlank()) {
            // Add the user's message first
            messages.value.add(Message(text = userMessage, recipientId = "user", isOut = true))
            messagesSent++
            onUserMessageSent(userMessage)

            if (messagesSent == 3) {
                // Show the payment dialog
                showPaymentDialog = true
            }

            // Check if the user's message is "Hi" and generate a response from the bot
            if (userMessage.equals("Hi", ignoreCase = true)) {
                // Add the bot's response
                messages.value.add(Message(text = "Hello! How can I assist you?", recipientId = "bot", isOut = false))
            } else {
                messages.value.add(
                    Message(
                        text = "The question is not related to movies...Please ask again",
                        recipientId = "bot",
                        isOut = false
                    )
                )
            }
        }
    }
}
