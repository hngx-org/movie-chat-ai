package com.essycynthia.moviechat.ui.home_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essycynthia.moviechat.ui.register_screens.SignUpScreenState
import com.example.apilibrary.wrapperclass.OpenAiCaller
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ChatScreenViewModel : ViewModel() {
    private val _chatState = MutableStateFlow(ChatScreenState())
    val chatState: MutableStateFlow<ChatScreenState> = _chatState
    fun getChats(userPrompt: String, userId: String) {
        // Set the loading state while making the API call
        _chatState.value = _chatState.value.copy(isLoading = true)

        if (isMovieOrSeriesQuestion(userPrompt)) {
            viewModelScope.launch {
                try {
                    val response = OpenAiCaller.generateChatResponse(userPrompt, userId)

                    // Update the bot response and user prompt
                    _chatState.value = _chatState.value.copy(
                        botResponse = response,
                        userPrompt = userPrompt,
                        isLoading = false, // Turn off loading state
                        error = null // Clear any previous error message
                    )
                } catch (e: Exception) {
                    // Handle API call errors
                    _chatState.value = _chatState.value.copy(
                        isLoading = false, // Turn off loading state
                        error = "An error occurred: ${e.message}"
                    )
                }
            }
        } else {
            // User prompt is not related to movies or series
            // Provide a specific response
            val nonRelatedResponse =
                "The question is not related to movies or series. Please ask again."

            // Update the response and user prompt fields
            _chatState.value = _chatState.value.copy(
                botResponse = nonRelatedResponse,
                userPrompt = userPrompt,
                isLoading = false, // Turn off loading state
                error = null // Clear any previous error message
            )
        }
    }
    private fun isMovieOrSeriesQuestion(question: String): Boolean {
        // Implement your logic to determine if the question is related to movies or series
        // For example, you can check for keywords like "movie," "film," "series," etc. in the question.
        // Modify this logic as per your requirements.
        val keywords = listOf("movie", "film", "series")
        val lowerCaseQuestion = question.toLowerCase()
        return keywords.any { keyword -> lowerCaseQuestion.contains(keyword) }
    }
}


