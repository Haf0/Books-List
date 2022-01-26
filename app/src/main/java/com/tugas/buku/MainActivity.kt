package com.tugas.buku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tugas.buku.dao.FavVMFactory
import com.tugas.buku.dao.FavViewModel
import com.tugas.buku.screen.AboutScreen
import com.tugas.buku.screen.DetailScreen
import com.tugas.buku.screen.FavoriteScreen
import com.tugas.buku.screen.HomeScreen
import com.tugas.buku.ui.theme.BukuTheme
import com.tugas.buku.viewmodel.BookViewModel

class MainActivity : ComponentActivity() {

    private val modelViewModel by viewModels<BookViewModel>()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val componentActivity = this
        val favViewModel : FavViewModel by viewModels<FavViewModel> {
            FavVMFactory(application = componentActivity.application)
        }
        setContent {
            val navController = rememberNavController()
            BukuTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "homeScreen") {
                        composable("homeScreen") {
                            HomeScreen(
                                bookViewModel = modelViewModel,
                                navController = navController,
                                favViewModel = favViewModel,
                                componentActivity = componentActivity
                            )
                        }
                        composable("AboutScreen"){
                            AboutScreen()
                        }
                        composable(
                            "detailScreen/{id}",
                            arguments = listOf(navArgument("id"){type= NavType.IntType})
                        ){
                            navBackStackEntry ->  DetailScreen(BookViewModel = modelViewModel, id = navBackStackEntry.arguments?.getInt("id")!!)
                        }
                        composable("favoriteScreen") {
                            FavoriteScreen(
                                bookViewModel = modelViewModel,
                                navController = navController,
                                favViewModel = favViewModel,
                                componentActivity = componentActivity
                            )
                        }
                    }
                }
            }
        }
    }
}
