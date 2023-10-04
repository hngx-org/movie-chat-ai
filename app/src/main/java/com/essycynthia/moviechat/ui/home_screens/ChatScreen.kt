package com.essycynthia.moviechat.ui.home_screens

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.essycynthia.moviechat.R
import com.essycynthia.moviechat.data.Message
import com.essycynthia.moviechat.ui.navigation.NavigationRoutes
import kotlinx.coroutines.launch
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
data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector

)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navigateToPayment: () -> Unit) {
    val viewModel = remember { ChatViewModel() }
    val simpleDateFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
    val messageDummy = remember { mutableStateListOf<Message>() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    var showPaymentDialog by remember { mutableStateOf(false) }
    val items = remember { mutableStateListOf<NavigationItem>(
        NavigationItem(
            title = "All",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavigationItem(
            title = "Dark Mode",
            selectedIcon = Icons.Filled.DarkMode,
            unselectedIcon = Icons.Outlined.LightMode,

            ),
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        )
    ) }


    ModalNavigationDrawer(drawerContent = { ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Text(text = item.title)
                },
                selected = index == selectedItemIndex,
                onClick = {
//                                            navController.navigate(item.route)
                    selectedItemIndex = index
                    scope.launch {
                        drawerState.close()
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },

                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }},
      drawerState =  drawerState
  ) {

            Scaffold {
                TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Toggle drawer"
                            )

                        }
                    },
                    actions = {
                        IconButton(onClick = { TODO() }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add chat")

                        }
                    })
            }
            if (showPaymentDialog) {
                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog
                        showPaymentDialog = false
                    },
                    title = {
                        Text(text = "Payment Required")
                    },
                    text = {
                        Text(text = "You have sent 3 messages. To continue, please proceed to payment.")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                // Handle the "PROCEED TO PAYMENT" button click here
                                // You can navigate to a payment screen or perform payment logic.
                                    navigateToPayment()
                                // Dismiss the dialog
                                showPaymentDialog = false
                            }
                        ) {
                            Text(text = "PROCEED TO PAYMENT")
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    

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
            items(viewModel.messages.value.reversed()) { chat ->
                MessageItem(
                    messageText = chat.text,
                    time = simpleDateFormat.format(chat.time),
                    isOut = chat.isOut
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        MessageSection { userMessage ->
            viewModel.sendMessage(userMessage) { userMessage ->
                // Handle the user message sent callback here if needed
            }
        }
    }

    if (viewModel.showPaymentDialog) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog
                viewModel.showPaymentDialog = false
            },
            title = {
                Text(text = "Payment Required")
            },
            text = {
                Text(text = "You have sent 3 messages. To continue, please proceed to payment.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Handle the "PROCEED TO PAYMENT" button click here
                        navigateToPayment()
                        // Dismiss the dialog
                        viewModel.showPaymentDialog = false
                    }
                ) {
                    Text(text = "PROCEED TO PAYMENT")
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageSection(onUserMessageSent: (String) -> Unit) {
    val messageDummy = remember { mutableStateListOf<Message>() }
    var messagesSent by remember { mutableStateOf(0) }
    var showPaymentDialog by remember { mutableStateOf(false) }

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
                Icon(
                    imageVector = Icons.Outlined.Send,
                    contentDescription = "SEND",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        val userMessage = message.value
                        if (userMessage.isNotBlank()) {
                            // Add the user's message first
                            messageDummy.add(Message(text = userMessage, recipientId = "user", isOut = true))
                            messagesSent++
                            onUserMessageSent(userMessage)
                            if (messagesSent == 3) {
                                // Show the payment dialog
                                showPaymentDialog = true
                            }

                            // Check if the user's message is "Hi" and generate a response from the bot
                            if (userMessage.equals("Hi", ignoreCase = true)) {
                                // Add the bot's response
                                messageDummy.add(Message(text = "Hello! How can I assist you?", recipientId = "bot", isOut = false))
                            }
                            else{
                                messageDummy.add(Message(text = "The question is not related to movies...Please ask again", recipientId = "bot", isOut = false))

                            } 

                            // Clear the message field
                            message.value = ""
                        }

                    }
                )
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
                            if (isOut) MaterialTheme.colorScheme.primary else Color(0xFFF9F6EE),
                            shape = if (isOut) AuthorChatBubbleShape else BotChatBubbleShape
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = messageText,
                        color = if (isOut) Color.White
                        else Color.Black
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
