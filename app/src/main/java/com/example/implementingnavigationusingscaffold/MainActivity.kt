package com.example.implementingnavigationusingscaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.implementingnavigationusingscaffold.ui.theme.ImplementingNavigationUsingScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImplementingNavigationUsingScaffoldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainTopBar(title: String, navController){
    var expanded by remember{ mutableStateOf(false) }
    TopBarApp(
        title = {Text(title)},
        actions = {
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ){
                Icon(Icons.Filled.MoreVert,contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false}){
                DropdownMenuItem(onClick = {navController.navigate("info")}){
                    Text("Info")
                }
                DropdownMenuItem(onClick = {navController.navigate("setting")}){
                    Text("Settings")
                }
            }
            )
        }
    )
}
@Composable
fun ScreenTopBar(title: String, navController: NavController){
    TopBarApp(
        title = {Text(title)},
        navigationIcon ={
            IconButton(onClick = {navController.navigateUp()}){
                Icon(Icons.Filled.ArrowBack,contentDescription = null)
            }
        }
    )
}
@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = {MainTopBar("My App",navController)},
        content = {Text(text = "Content for Home screen")},
    )
}
@Composable
fun InfoScreen(navController: NavController){
    Scaffold(
        topBar = {ScreenTopBar("info", navController)},
        content = {Text(text = "Content for Info screen")},
    )
}
@Composable
fun SettingsScreen(navController: NavController){
    Scaffold(
        topBar = {ScreenTopBar("Settings",navController)},
        content = {Text(text = "Content for Settings screen")}
    )
}
@Composable
fun ScaffoldApp(){
    val navController = rememberNavController()
    NavHost(
        navCOntroller = navController,
        startDestination = "Home"
    ){
        composable(route = "Home"){
            MainScreen(navController)
        }
        composable(route = "Info"){
            InfoScreen(navController)
        }
        composable(route = "Settings"){
            SettingsScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImplementingNavigationUsingScaffoldTheme {
        Greeting("Android")
    }
}