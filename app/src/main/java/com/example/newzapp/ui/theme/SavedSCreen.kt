package com.example.newzapp.ui.theme

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.EmptyPath
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newzapp.DataBase.Entity

@Composable
fun SavedScreen(modifier: Modifier = Modifier, vieww: vieww) {


    val result by vieww.offline.collectAsState(initial = emptyList())
    vieww.screen=false




    Box(
        modifier = modifier
            .fillMaxSize(), contentAlignment = Alignment.TopCenter
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(result) { data ->
                var expanded by remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 1000,
                                easing = LinearOutSlowInEasing, delayMillis = 50
                            )
                        )

                        .padding(14.dp), colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF6650a4)
                    ), shape = RoundedCornerShape(10)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row {
                            if (!data.image_URL.equals("NOTHING")) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            Color(0xFF9B7BFA),
                                            shape = RoundedCornerShape(
                                                topEnd = 5.dp,
                                                bottomEnd = 5.dp
                                            )
                                        )
                                        .size(height = 150.dp, width = 180.dp)
                                ) {
                                    AsyncImage(
                                        model = data.image_URL,
                                        contentDescription = "web image",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(
                                                RoundedCornerShape(
                                                    topEnd = 5.dp,
                                                    bottomEnd = 5.dp
                                                )
                                            ),
                                        contentScale = ContentScale.Crop
                                    )

                                }

                            } else {
                                Box(
                                    modifier = Modifier.background(
                                        Color.LightGray,
                                        shape = RoundedCornerShape(
                                            topEnd = 5.dp,
                                            bottomEnd = 5.dp
                                        )
                                    )
                                ) {
                                    Icon(
                                        Icons.Default.Error,
                                        tint = Color.Gray,
                                        contentDescription = null,
                                        modifier = Modifier.size(
                                            height = 150.dp,
                                            width = 180.dp
                                        )
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .height(150.dp)
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                Color(0xFF9B7BFA),
                                                Color(0xFF9879F5),
                                                Color(0xFF8569D7),
                                                Color(0xFF6650a4),
                                                Color(0xFF382C5B)
                                            )
                                        )
                                    )
                                    .padding(5.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .height(150.dp)
                                        .padding(5.dp)
                                ) {
                                    Text(
                                        data.title ?: "No Title Available",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    IconButton(
                                        onClick = {
                                            vieww.delete(Id = data.id)
                                        }, modifier = Modifier.offset(x = 10.dp, y = -10.dp)
                                    ) {

                                        Icon(
                                            Icons.Outlined.Delete,
                                            contentDescription = null,
                                            modifier = Modifier.size(25.dp),
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()

                                .background(
                                    Color(0xFFE2DBFC)
                                )
                                .padding(12.dp)
                        ) {
                            Text(
                                data.description ?: "No Description Available",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF6650a4),
                                maxLines = if (expanded) Int.MAX_VALUE else 3,
                                overflow = TextOverflow.Ellipsis
                            )

                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFF9B7BFA),
                                            Color(0xFF9879F5),
                                            Color(0xFF8569D7),
                                            Color(0xFF6650a4),
                                            Color(0xFF382C5B)
                                        )
                                    )
                                )
                                .clickable { expanded = !expanded }
                        ) {
                            Text(
                                if (!expanded) "View More" else "View Less",
                                color = Color.White
                            )
                            Spacer(Modifier.width(5.dp))
                            Icon(
                                if (!expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                                tint = Color.White,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }


                    }
                }

            }
        }
    }

}

