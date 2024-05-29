package com.example.geminiaichatbotandroid.chatBot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatBotViewModel: ViewModel() {

    private val _chatBotState = MutableStateFlow(ChatBotState())
    val chatBotState = _chatBotState.asStateFlow()

    val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = ""
    )



    fun onEvent(event: ChatBotEvent) {
        when(event) {
            is ChatBotEvent.PromptField -> {
                _chatBotState.update {
                    it.copy(
                        prompt = event.prompt
                    )
                }
            }

            ChatBotEvent.OnSubmitClick -> {
                viewModelScope.launch {
                    _chatBotState.update {
                        it.copy(
                            result = generativeModel.generateContent(it.prompt).text ?: "",
                            prompt = "",
                        )
                    }
                }
            }
        }
    }
}
