package com.kwk.epl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kwk.epl.ui.theme.EPLTheme
import com.squareup.sqldelight.android.AndroidSqliteDriver
import comkwkeplsqldelighthockeydata.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val driver = AndroidSqliteDriver(Database.Schema, this, null)
        val database = Database(driver)
        val playerQueries = database.playerQueries

        val player = Player(19, "Ronald McDonald")

        lifecycleScope.launch(Dispatchers.IO) {
            playerQueries.insertFullPlayerObject(player)
            playerQueries.insert(player_number = 17, full_name = "Corey Perry")
            println(playerQueries.selectAll().executeAsList())
        }


        setContent {
            MainScreenView()
        }
    }
}

sealed class BottomNavItem(var title: String, var icon: Int, var screenRoute: String) {
    object Home : BottomNavItem("Home", R.drawable.icons8_apple_logo, "home")
    object Matches : BottomNavItem("Matches", R.drawable.icons8_bookmark, "matches")
    object Players : BottomNavItem("Players", R.drawable.icons8_cloudshot, "players")
    object Stats : BottomNavItem("Stats", R.drawable.icons8_refresh, "stats")
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { EPLBottomNavigation(navController = navController) },
        content = { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
//                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
            ) {
                NavigationGraph(navController = navController)
            }
        }
    )
}

@Composable
fun HomeScreen() {
    Simple()
}

@Composable
fun MatchesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_200))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Matches Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun PlayersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_200))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Players Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun StatsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Stats Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Matches.screenRoute) {
            MatchesScreen()
        }
        composable(BottomNavItem.Players.screenRoute) {
            PlayersScreen()
        }
        composable(BottomNavItem.Stats.screenRoute) {
            StatsScreen()
        }
    }
}

@Composable
fun EPLBottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Matches,
        BottomNavItem.Players,
        BottomNavItem.Stats
    )

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                },
                label = {
                    Text(text = item.title, fontSize = 9.sp)
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                })
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Simple() {
    MaterialTheme {
        var text by remember {
            mutableStateOf("hello")
        }
        Column {
            TextField(value = text, onValueChange = {text = it},  label = { Text(text = "Label")})
            HomeNewsScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EPLTheme {
        Greeting("Android")
    }
}