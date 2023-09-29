package com.essycynthia.moviechat.ui.home_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essycynthia.moviechat.data.messageDummy
import java.text.SimpleDateFormat
import java.util.Locale

val AuthorChatBubbleShape: Shape = RoundedCornerShape(
    topStart = 48f,
    topEnd = 48f, bottomStart = 48f, bottomEnd = 0f
)
val message = mutableStateOf("")
val BotChatBubbleShape: Shape = RoundedCornerShape(
    topStart = 48f,
    topEnd = 48f, bottomStart = 0f, bottomEnd = 48f
)

@Composable
fun ChatScreen() {
    val simpleDateFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // This makes the LazyColumn take up available space
                .padding(16.dp),
            reverseLayout = true
        ) {
            items(messageDummy) { chat ->
                MessageItem(
                    messageText = chat.text,
                    time = simpleDateFormat.format(chat.time),
                    isOut = chat.isOut
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        MessageSection()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        OutlinedTextField(
            value = message.value,
            placeholder = {
                          Text(text = "Send a message")
            },
            onValueChange = { message.value = it },
            shape = RoundedCornerShape(25.dp),
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.Send, contentDescription ="SEND",tint =Color(0xFF209AFD), modifier = Modifier.clickable {  } )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            )


    }
}

@Composable
fun MessageItem(
    messageText: String?,
    time: String,
    isOut: Boolean
) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
    ) {
        if (messageText != null) {
            if (messageText.isNotBlank()) {

                Box(
                    modifier = Modifier
                        .background(
                            if (isOut) Color(0xFF209AFD) else Color(0xFFF9F6EE),
                            shape = if (isOut) AuthorChatBubbleShape else BotChatBubbleShape
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = messageText,
                       color =  if (isOut) Color.White
                        else  Color.Black
                    )

                }
            }
        }
        Text(
            text = time,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )

    }
}
