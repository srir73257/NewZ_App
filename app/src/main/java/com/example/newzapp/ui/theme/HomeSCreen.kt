package com.example.newzapp.ui.theme


import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newzapp.R
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import kotlin.collections.emptyList


@Composable
fun Homescreen(vieww: vieww, navController: NavController) {

    var content by rememberSaveable { mutableStateOf("") }


    var selectedindex by remember { mutableIntStateOf(0) }
    val list = listOf(
        Navitem(
            icon = Icons.Default.Home,
            label = "Home",
            countt = 0
        ),
        Navitem(
            icon = Icons.Default.Bookmarks,
            label = "Saved News",
            countt = 0
        )
    )


    val context = LocalContext.current


    Scaffold(
        topBar = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(108.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF6752A8),
                                Color(0xFF9B7BFA),
                                Color(0xFF9879F5),
                                Color(0xFF8569D7),
                                Color(0xFF6650a4),
                            )
                        ), shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                    )
            ) {
                AnimatedVisibility(
                    visible = !vieww.screen,
                    enter = fadeIn(animationSpec = tween(500)) +
                            slideInVertically(
                                initialOffsetY = { fullHeight -> fullHeight / 2 },
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioHighBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            ),
                    exit = fadeOut(animationSpec = tween(300)) +
                            slideOutVertically(
                                targetOffsetY = { fullHeight -> fullHeight },
                                animationSpec = tween(durationMillis = 500)
                            )
                ) {
                    Box(
                        modifier = Modifier

                            .fillMaxSize()
                            .offset(y = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.looo),
                            contentDescription = "name",
                            modifier = Modifier.size(width = 220.dp, height = 70.dp)
                        )
                    }
                }
                if (vieww.screen) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(17.dp))
                        TextField(
                            value = content,
                            onValueChange = { content = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFE3DCF8),
                                focusedContainerColor = Color(0xFFF1EFFC)
                            ),
                            shape = RoundedCornerShape(30),
                            trailingIcon = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = "search",
                                    tint = Color(0xFF6650a4),
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clickable {
                                            if (content.isNotBlank()) {
                                                vieww.getall(content)
                                                vieww.content=content
                                                content = ""
                                            } else {

                                                content = ""
                                                Toast.makeText(
                                                    context,
                                                    "Enter Something to Search",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                )
                            },
                            label = { Text("Search News") },
                            enabled = vieww.screen

                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }, containerColor = Color(0xFFF1EFFC), bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()

                    .height(111.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF9B7BFA),
                                Color(0xFF9879F5),
                                Color(0xFF8569D7),
                                Color(0xFF6650a4),
                                Color(0xFF473873).copy(alpha = 0.8f)
                            )
                        ), shape = RoundedCornerShape(topEnd = 80.dp, topStart = 80.dp)
                    ), contentAlignment = Alignment.Center
            ) {
                AnimatedNavigationBar(
                    modifier = Modifier.height(70.dp),
                    selectedIndex = selectedindex,
                    barColor = Color.Transparent,
                    ballColor = Color.White,
                    cornerRadius = shapeCornerRadius(cornerRadius = 0.9f),
                    ballAnimation = Parabolic(tween(durationMillis = 500)),
                    indentAnimation = Height(tween(durationMillis = 500))
                ) {
                    list.forEachIndexed { index, navitem ->
                        val cornerSize by animateDpAsState(
                            targetValue = if (selectedindex == index) 27.dp else 30.dp,
                            animationSpec = spring(Spring.DampingRatioHighBouncy)
                        )
                        Box(
                            modifier = Modifier
                                .noRippleClickable { selectedindex = index }
                                .fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = navitem.icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(cornerSize),
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    navitem.label,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            }

                        }


                    }
                }
            }

        }

    ) { inner ->
        when (selectedindex) {
            0 -> Home(modifier = Modifier.padding(inner), vieww, content, navController)
            1 -> SavedScreen(modifier = Modifier.padding(inner), vieww)
        }


    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    vieww: vieww,
    content: String,
    navController: NavController
) {
    vieww.screen = true
    val resultt by vieww.result.collectAsStateWithLifecycle()
    val newsList = resultt?.results ?: emptyList()

    val stateee by vieww.staee.collectAsState()
    var loading by remember { mutableStateOf(false) }
    var loading1 by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf<String?>("") }
    LaunchedEffect(Unit) {

        if (resultt?.results.isNullOrEmpty()) {
            vieww.getall("Sports")
        }
    }
    when (stateee) {
        is State_d.Success -> {
            loading = false
            message = null
            loading1 = false
        }

        is State_d.loading -> {
            loading = true
            message = null
        }

        is State_d.Error -> {
            message =
                (stateee as State_d.Error).message ?: "An unknown error occurred"
            loading = false
        }

        else -> {}
    }
    val contect = LocalContext.current
    LaunchedEffect(Unit) {
        vieww.toaast.collect { mesage ->
            Toast.makeText(contect, mesage, Toast.LENGTH_SHORT).show()
        }

    }
    val lazy = rememberLazyListState()

    LaunchedEffect(content) {
        vieww.isInternetAvailable(contect)
        lazy.scrollToItem(0)
    }

    LaunchedEffect(lazy) {

        snapshotFlow { lazy.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { index ->

                if (
                    index != null &&
                    index >= newsList.size - 2 &&
                    resultt?.nextPage != null &&
                    !loading1
                ) {

                    loading1 = true
                    vieww.nextpage(resultt?.nextPage)

                }
            }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {


        if (loading && newsList.isEmpty() ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .offset(y = 5.dp), contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(strokeWidth = 3.dp, color = Color(0xFF6650a4))
            }
        } else if (message != null) {
            val scrollState = rememberScrollState()
                PullToRefreshBox(
                    isRefreshing = loading,
                    onRefresh = {

                        vieww.getall(if (vieww.content.isBlank()) "Sports" else vieww.content)


                    },modifier = Modifier.fillMaxSize()
                ) {
                    Box(Modifier.fillMaxSize().verticalScroll(scrollState), contentAlignment = Alignment.Center) {

                    Text(
                        "$message",
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        color = Color(0xFF6650a4)
                    )
                }

            }

        } else {
            PullToRefreshBox(
                isRefreshing = loading,
                onRefresh = {

                    vieww.getall(if (vieww.content.isBlank()) "Sports" else vieww.content)


                }
            ) {
                LazyColumn(
                    state = lazy,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    items(newsList, key = { it.article_id }) { data ->
                        var expanded by remember { mutableStateOf(false) }

                        ElevatedCard(
                            modifier = Modifier
                                .padding(14.dp)
                                .fillMaxWidth()
                                .animateContentSize(
                                    animationSpec = tween(
                                        durationMillis = 1000,
                                        easing = LinearOutSlowInEasing, delayMillis = 50
                                    )
                                )
                                .clickable(onClick = {
                                    if (data.article_id == null) {
                                        Toast.makeText(
                                            contect,
                                            "Unexpected Error",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else {
                                        vieww.id = data.article_id
                                        navController.navigate(Screen.Detail)
                                    }
                                }),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF6650a4)
                            ),
                            shape = RoundedCornerShape(10),
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row {
                                    if (data.image_url != null) {
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
                                                model = data.image_url,
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
                                                    vieww.insert(
                                                        title = data.title,
                                                        Description = data.description,
                                                        url = data.image_url ?: " "
                                                    )
                                                }, modifier = Modifier.offset(x = 7.dp, y = -7.dp)
                                            ) {

                                                Icon(
                                                    Icons.Outlined.Bookmarks,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(20.dp),
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
                    item {
                        if (loading1) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(Modifier.height(7.dp))
                                CircularProgressIndicator(
                                    strokeWidth = 3.dp,
                                    color = Color(0xFF6650a4)
                                )
                                Spacer(Modifier.height(18.dp))
                            }
                        }
                    }
                }
            }


        }

    }}
