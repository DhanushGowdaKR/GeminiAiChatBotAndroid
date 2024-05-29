package com.example.geminiaichatbotandroid.chatBot

sealed class ChatBotEvent {
    data object OnSubmitClick: ChatBotEvent()
    data class PromptField(val prompt: String): ChatBotEvent()
}