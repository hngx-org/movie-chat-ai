package com.essycynthia.moviechat.ui.home_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essycynthia.moviechat.R
import com.essycynthia.moviechat.data.Message
import com.essycynthia.moviechat.ui.theme.MovieChatTheme
import com.example.apilibrary.wrapperclass.OpenAiCaller
import com.shegs.hng_auth_library.authlibrary.AuthLibrary
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
    var title: String,
    var selectedIcon: ImageVector,
    var unselectedIcon: ImageVector,
    val clickable: () -> Unit

)

val viewModel = ChatScreenViewModel()


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navigateToPayment: () -> Unit,
    userId: String? = ""
) {
    var isDarkMode = false
    if (isDarkMode) {
        MovieChatTheme(darkTheme = false) {}

    } else {
        MovieChatTheme(darkTheme = true) {}

    }

    val chatState by viewModel.chatState.collectAsState()
    val apiService = AuthLibrary.createAuthService()
    val logoutRepository = AuthLibrary.createLogoutRepository(apiService)
    val profileRepository = AuthLibrary.createProfileRepository(apiService)
    val simpleDateFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
    val messageDummy = remember { mutableStateListOf<Message>() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    var messageSent by remember { mutableStateOf(0) }
    val items = remember {
        mutableStateListOf<NavigationItem>(
            NavigationItem(
                title = "Profile",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person
            ) {
                coroutineScope.launch {
                    profileRepository.profile()
                }
            },

            NavigationItem(
                title = "DarkMode",
                selectedIcon = Icons.Filled.DarkMode,
                unselectedIcon = Icons.Outlined.LightMode

            ) {

            },

            NavigationItem(
                title = "Logout",
                selectedIcon = Icons.Filled.Logout,
                unselectedIcon = Icons.Outlined.Logout
            ) {
                coroutineScope.launch {
                    logoutRepository.logout()
                }

            },

            )
    }

    ModalNavigationDrawer(
        //   modifier = Modifier.


        drawerContent = {


            ModalDrawerSheet {

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

                            .padding(NavigationDrawerItemDefaults.ItemPadding),


                        )
                }
            }

        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
        ) {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.app_name),

                    //added font family
                    fontFamily = FontFamily(Font(R.font.poppinsemibold)), fontSize = 17.sp
                )
            },
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
    }

    Column {
       Spacer(modifier = Modifier.height(48.dp)) // Add some spacing at the top
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp)
            //            reverseLayout = true
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
        val coroutine = rememberCoroutineScope()

        MessageSection({ userMessage ->
            // When the user sends a message, add it to the messages list
            coroutine.launch {
                val botResponse = OpenAiCaller.generateChatResponse(userMessage, userId!!)
                messageDummy.add(
                    Message(
                        text = userMessage,
                        recipientId = "user",
                        isOut = true
                    )
                )
                messageSent++
                if (messageSent >= 3) {
                    showDialog = true
                }


                if (chatState.botResponse != null) {
                    // You can use botResponse in your UI, e.g., display it in a Text composable
                    messageDummy.add(
                        Message(
                            text = botResponse,
                            recipientId = "bot",
                            isOut = false
                        )
                    )
                } else {
                    // Handle the case where there is no bot response or it's blank
                    // You might display a placeholder or loading indicator here
                    chatState.error
                }
            }


//            // Check if the user's message is "Hi" and generate a response from the bot
//            if (userMessage.equals("Hi", ignoreCase = true)) {
//                messageDummy.add(
//                    Message(
//                        text = "Hello! How can I assist you?",
//                        recipientId = "bot",
//                        isOut = false
//                    )
//                )
//            } else {
//                messageDummy.add(
//                    Message(
//                        text = "The question is not related to movies...Please ask again",
//                        recipientId = "bot",
//                        isOut = false
//                    )
//                )
//
//            }

        }, userId = userId)

    }
    if (showDialog) {
        AlertDialog(
            //modifier = Modifier
            //.background(Color.White)
            ///.size(250.dp), // Adjust the size as needed

            onDismissRequest = {
                // Dismiss the dialog and reset showDialog
                showDialog = false
            },

            title = {
                Text(
                    "LIMIT REACHED!!!",
                    fontFamily = FontFamily(Font(R.font.poppinsemibold))
                )
            },
            text = {
                Text(
                    "You've reached your free trial",
                    fontFamily = FontFamily(Font(R.font.poppinslight))
                )
            },
            confirmButton = {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color(0xFF209AFD)),
                    shape = RoundedCornerShape(10.dp),

                    // Adjust the size as needed
                    onClick = {
                        // Perform any action you want when the dialog is confirmed
                        navigateToPayment()
                        showDialog = false
                    },

                    ) {
                    Icon(
                        imageVector = Icons.Default.ArrowRight,
                        contentDescription = "PAYMENT BUTTON"
                    )
                    Text(
                        "PROCEED TO PAYMENT",
                        modifier = Modifier.fillMaxWidth(),
                        fontFamily = FontFamily(Font(R.font.poppinsemibold)),
                        color = Color.White
                    )
                }
            }
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageSection(onUserMessageSent: (String) -> Unit, userId: String?) {
    val messageDummy = remember { mutableStateListOf<Message>() }
    var messageSent = 0
    val chatState by viewModel.chatState.collectAsState()

    Card(
        modifier = Modifier

            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD0E9FD)
        )


    ) {
        OutlinedTextField(
            value = message.value,
            placeholder = {
                Text(text = "Send a message")
            },
            onValueChange = { message.value = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0xFF209AFD),
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(25.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Send,
                    contentDescription = "SEND",
                    tint = Color(0xFF209AFD),
                    modifier = Modifier
                        .clickable {
                            val userMessage = message.value
                            if (userMessage.isNotBlank()) {
                                // Add the user's message first
                                viewModel.getChats(userMessage, userId!!)
                                messageDummy.add(
                                    Message(
                                        text = userMessage,
                                        recipientId = "user",
                                        isOut = true
                                    )
                                )
                                onUserMessageSent(userMessage)
                                messageSent++

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
                            if (isOut) Color(0xFF209AFD) else Color(0xFFF9F6EE),
                            shape = if (isOut) AuthorChatBubbleShape else BotChatBubbleShape
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = messageText,
                        fontFamily = FontFamily(Font(R.font.poppinslight)),
                        color = if (isOut) Color.White
                        else Color.Black
                    )

                }
            }
        }
        Text(
            text = time,
            fontFamily = FontFamily(Font(R.font.poppinslight)),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )

    }
}
