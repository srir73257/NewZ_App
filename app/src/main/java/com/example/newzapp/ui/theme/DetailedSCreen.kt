package com.example.newzapp.ui.theme

import android.R.attr.data
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newzapp.R

@Composable
fun DetailedScreen(vieww: vieww, navController: NavController) {
    val article = vieww.artichle()
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible=true
    }
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth }
        ) + fadeIn(),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth }
        ) + fadeOut()

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.fillMaxWidth(0.04f))
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "menu",
                        modifier = Modifier.size(35.dp),
                        tint = Color(0xFF6650a4)
                    )
                }
                Spacer(Modifier.fillMaxWidth(0.15f))
                Image(
                    painter = painterResource(id = R.drawable.loo1),
                    contentDescription = "name",
                    modifier = Modifier.size(height = 80.dp, width = 200.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFCFCFF),
                                Color(0xFFDAD0FD),
                                Color(0xFF9879F5),
                                Color(0xFF8569D7),
                                Color(0xFF6650a4),
                            )
                        ), shape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp)
                    )
            ) {
                if (article != null) {
                    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Spacer(Modifier.fillMaxWidth(0.03f))
                                if (article.source_icon == null) {
                                    Icon(
                                        imageVector = Icons.Default.ErrorOutline,
                                        contentDescription = null,
                                        modifier = Modifier.size(100.dp), tint = Color(0xFF4D3D7A)
                                    )
                                } else {
                                    AsyncImage(
                                        model = article.source_icon,
                                        contentDescription = "source image",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clip(RoundedCornerShape(20)),
                                        contentScale = ContentScale.Crop,

                                        )
                                }
                                Spacer(Modifier.fillMaxWidth(0.05f))
                                Text(
                                    " ${article.source_name ?: "No Source Name provided"}",
                                    fontSize = 21.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF4D3D7A)
                                )
                            }
                        }
                        item {
                            val context = LocalContext.current
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text("Source url: ", fontSize = 16.sp, color = Color(0xFF413465))
                                Text(
                                    article.source_url ?: "No Source URL has been provided",
                                    fontSize = 15.sp,
                                    color = Color.Blue,
                                    modifier = Modifier.clickable {
                                        if (article.source_url !=null) {
                                            val intent = Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(article.source_url)
                                            )
                                            context.startActivity(intent)
                                        }else Toast.makeText(context,"No Valid URL", Toast.LENGTH_SHORT).show()
                                    },
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        }
                        item {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text("T", fontSize = 40.sp, color = Color(0xFF4D3D7A))
                                    Text(
                                        "ITLE:",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFF6650a4)
                                    )
                                }
                                Spacer(Modifier.height(3.dp))
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        article.title ?: "No Title provided",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium, color = Color(0xFF4D3D7A)
                                    )
                                }
                                Spacer(Modifier.height(10.dp))
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.LightGray
                                    )
                                ) {
                                    if (article.image_url != null) {
                                        AsyncImage(
                                            model = article.image_url,
                                            contentDescription = "web image",
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    } else {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(Color.LightGray, shape = RoundedCornerShape(30)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ErrorOutline,
                                                contentDescription = null,
                                                modifier = Modifier.fillMaxHeight(0.7f).fillMaxWidth(0.7f),
                                                tint = Color.Gray
                                            )
                                        }
                                    }

                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text("Published : ", fontSize = 16.sp, color = Color.White)
                                    Text(
                                        article.pubDate ?: "No Published date provided",
                                        fontSize = 15.sp,
                                        color = Color.White,
                                        fontStyle = FontStyle.Italic
                                    )
                                }

                                Text(
                                    "Description:",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                                Spacer(Modifier.height(10.dp))
                                Box(
                                    contentAlignment = Alignment.TopStart,

                                    ) {
                                    Text(
                                        article.description ?: "No Description Provided",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )

                                }
                            }
                        }
                        item {
                            Column {
                                Spacer(Modifier.height(35.dp))
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            "No Article Found !!!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF4D3D7A)
                        )
                    }
                }

            }
        }

    }


}
