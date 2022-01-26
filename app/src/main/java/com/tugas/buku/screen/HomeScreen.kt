package com.tugas.buku.screen

import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.tugas.buku.R
import com.tugas.buku.dao.FavViewModel
import com.tugas.buku.model.Book
import com.tugas.buku.model.Books
import com.tugas.buku.viewmodel.BookViewModel


@ExperimentalComposeUiApi
@Composable
fun HomeScreen (bookViewModel: BookViewModel, navController: NavController, favViewModel: FavViewModel,componentActivity: ComponentActivity){
    var Searchquery by remember { mutableStateOf("") }
    var data :List<Books>

    if(Searchquery.isNotEmpty()) {
        data = bookViewModel.BooksList.filter { e -> e.judul.lowercase().contains(Searchquery.lowercase()) || e.penulis.lowercase().contains(Searchquery.lowercase()) }
    } else {
        data = bookViewModel.BooksList
    }
    val focusManager = LocalFocusManager.current
    Scaffold (
        topBar = { TopBarapp(navController) },
        content = {
            Column {
                TextField(
                    value = Searchquery,
                    onValueChange = {Searchquery=it},
                    label = { Text(text = stringResource(R.string.LabelTextField)) },
                    placeholder = { Text(text = stringResource(R.string.SearchTextHolder)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    singleLine = true,
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password)
                )
                if (data.isNotEmpty()) {
                    Detaillist(book = data, navController = navController,bookViewModel =bookViewModel,favViewModel=favViewModel , componentActivity = componentActivity)
                }else{
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) { Text(stringResource(R.string.none)) }
                }

            }
        }
    )
}

@Composable
fun Detaillist(book: List<Books>, navController: NavController, bookViewModel: BookViewModel, favViewModel: FavViewModel,componentActivity: ComponentActivity) {
    if(book.size >0){
        LazyColumn{
            itemsIndexed(items=book){
                    _,
                    item -> DetailCard(data = item, navController = navController, bookViewModel=bookViewModel, favViewModel = favViewModel, componentActivity = componentActivity )
            }
        }
    }
}

@Composable
fun DetailCard(data: Books, navController: NavController, bookViewModel: BookViewModel, favViewModel: FavViewModel,componentActivity: ComponentActivity){
    var liked:Boolean by remember { mutableStateOf(false) }
    favViewModel.getFav().observe(componentActivity){
        liked = it.any{ e -> e.id == data.id}
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                bookViewModel.getDetailBooks(data.id)
                navController.navigate("detailScreen/${data.id}")
            },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ){

        Box(
            modifier = Modifier
                .height(500.dp)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Image(
                    painter = rememberImagePainter(data.gambar),
                    contentDescription = data.judul,
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 400f
                    )
                )
            )
            Box(modifier = Modifier
                .align(Alignment.TopEnd)
            ) {
                IconToggleButton(
                    checked = liked,
                    onCheckedChange = {
                        if(liked){
                            favViewModel.deleteFavourite(data)
                        }else{
                            favViewModel.addFavourite(data)
                        }
                    }
                ) {
                    val tint by animateColorAsState(
                        if (liked==false) Color.White
                        else Color.Red
                    )
                    Icon(
                        if (liked==false) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
                        contentDescription = "ntah lah",
                        tint = tint
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(text = data.judul,style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}

@Composable
fun TopBarapp( navController: NavController){

    TopAppBar(
        title = { Text(stringResource(R.string.TopBarText))},
        actions = {
            IconButton(
                onClick = {navController.navigate("favoriteScreen")}
            ){
                Icon(
                    Icons.Filled.Favorite,
                    "",
                    tint = Color.Red,
                )
            }
            IconButton(
                onClick = {navController.navigate("AboutScreen")}
            ){
                Icon(
                    Icons.Filled.AccountCircle,
                    "",
                    tint = Color.White,
                )
            }

        }
    )
}

