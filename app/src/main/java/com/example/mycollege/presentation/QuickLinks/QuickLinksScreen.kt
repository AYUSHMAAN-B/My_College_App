package com.example.mycollege.presentation.QuickLinks

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycollege.R
import com.example.mycollege.presentation.theme.appBackgroundColor
import com.example.mycollege.presentation.theme.cardColor
import com.example.mycollege.presentation.theme.topAppBarColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun QuickLinksRoute(
    navigator: DestinationsNavigator
)
{
    QuickLinks(
        navigator = navigator
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun QuickLinks(
    navigator: DestinationsNavigator
)
{
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("QUICK LINKS") },
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navigator.navigateUp() },
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topAppBarColor
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(appBackgroundColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 32.dp, top = 8.dp)
                    .size(width = 190.dp, height = 65.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://iitdh.ac.in/academic-calendar"))
                        context.startActivity(intent)
                    },
                colors = CardDefaults.cardColors(cardColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Calendar",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(1.dp, Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "Edit",
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 32.dp, top = 8.dp)
                    .size(width = 190.dp, height = 65.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://iitdh.ac.in/circular"))
                        context.startActivity(intent)
                    },
                colors = CardDefaults.cardColors(cardColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Circular",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(1.dp, Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "Edit",
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 32.dp, top = 8.dp)
                    .size(width = 190.dp, height = 65.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://iitdh.ac.in/curriculum"))
                        context.startActivity(intent)
                    },
                colors = CardDefaults.cardColors(cardColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Curriculum",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(1.dp, Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "Edit",
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 32.dp, top = 8.dp)
                    .size(width = 190.dp, height = 65.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://iitdh.ac.in/exams"))
                        context.startActivity(intent)
                    },
                colors = CardDefaults.cardColors(cardColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Exams",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(1.dp, Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "Edit",
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 32.dp, top = 8.dp)
                    .size(width = 190.dp, height = 65.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://iitdh.ac.in/holidays"))
                        context.startActivity(intent)
                    },
                colors = CardDefaults.cardColors(cardColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Holidays",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(1.dp, Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "Edit",
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 32.dp, top = 8.dp)
                    .size(width = 190.dp, height = 65.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://iitdh.ac.in/timetable-autumn-2024-25"))
                        context.startActivity(intent)
                    },
                colors = CardDefaults.cardColors(cardColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "TimeTable",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(1.dp, Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "Edit",
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 32.dp, top = 8.dp)
                    .size(width = 190.dp, height = 65.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://moodle.iitdh.ac.in/"))
                        context.startActivity(intent)
                    },
                colors = CardDefaults.cardColors(cardColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Moodle",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(1.dp, Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "Edit",
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 32.dp, top = 8.dp)
                    .size(width = 190.dp, height = 65.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://portal.iitdh.ac.in/asc/index.jsp"))
                        context.startActivity(intent)
                    },
                colors = CardDefaults.cardColors(cardColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Portal",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(1.dp, Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "Edit",
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}