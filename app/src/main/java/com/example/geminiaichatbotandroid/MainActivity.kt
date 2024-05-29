package com.example.geminiaichatbotandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geminiaichatbotandroid.chatBot.ChatBotEvent
import com.example.geminiaichatbotandroid.chatBot.ChatBotViewModel
import com.example.geminiaichatbotandroid.ui.theme.GeminiAiChatBotAndroidTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeminiAiChatBotAndroidTheme {
                val chatBotViewModel = viewModels<ChatBotViewModel>().value
                val state = chatBotViewModel.chatBotState.collectAsState().value

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(title = { Text(text = "Gemini Api Testing") })
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState()),
                            text = state.result,

                            )
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                modifier = Modifier
                                    .weight(1f),
                                placeholder = {
                                    Text(text = "Enter the prompt")
                                },
                                shape = RoundedCornerShape(20),
                                value = state.prompt,
                                onValueChange = {
                                    chatBotViewModel.onEvent(ChatBotEvent.PromptField(it))
                                },
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            IconButton(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(50))
                                    .background(Color.Gray),
                                onClick = {
                                    chatBotViewModel.onEvent(ChatBotEvent.OnSubmitClick)
                                }
                            ) {
                                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
                            }
                        }
                    }
                }
            }
        }
    }
}
