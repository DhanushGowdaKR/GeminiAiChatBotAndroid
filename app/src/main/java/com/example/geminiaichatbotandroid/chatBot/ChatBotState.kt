package com.example.geminiaichatbotandroid.chatBot

data class ChatBotState(
    val prompt: String = "",
    val result: String = "",
    var isLoading: Boolean = false,
)