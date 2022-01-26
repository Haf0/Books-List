package com.tugas.buku.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tugas.buku.R
import com.tugas.buku.dao.FavViewModel
import com.tugas.buku.model.Books
import com.tugas.buku.viewmodel.BookViewModel

@Composable
fun FavoriteScreen( navController: NavController, bookViewModel: BookViewModel, favViewModel: FavViewModel, componentActivity: ComponentActivity){
    var data: List<Books> by remember{ mutableStateOf(listOf())}

    favViewModel.getFav().observe(componentActivity){
        data = it
    }

    Scaffold (
        topBar ={
            TopAppBar {
                Text(stringResource(R.string.FavList))
            }
        } ,
        content = {
            if (data.isNotEmpty()) {
                LazyColumn {
                    itemsIndexed(items = data) { _, item -> DetailCard(componentActivity = componentActivity, data = item, favViewModel = favViewModel, navController = navController, bookViewModel = bookViewModel) }
                }
            } else {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(R.string.nol))
                }
            }
        }
    )

}