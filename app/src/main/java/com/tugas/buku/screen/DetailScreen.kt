package com.tugas.buku.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.tugas.buku.R
import com.tugas.buku.model.Books
import com.tugas.buku.viewmodel.BookViewModel

@Composable
fun DetailScreen(BookViewModel: BookViewModel, id: Int){
    BookViewModel.getDetailBooks(id=id)
    val data: Books =BookViewModel.books!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
            ){
                Card(
                    modifier = Modifier.width(200.dp).height(300.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(data.gambar),
                        contentDescription = data.judul,
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

        }
        Divider(color = androidx.compose.ui.graphics.Color.LightGray, thickness = 2.dp,modifier = Modifier.padding(vertical = 5.dp))
        TextRow(jenisnya = stringResource(R.string.Title), isinya = ": ${data.judul}" )
        TextRow(jenisnya = stringResource(R.string.author), isinya = ": ${data.penulis}")
        TextRow(jenisnya = stringResource(R.string.publisher), isinya = ": ${data.penerbit}")
        TextRow(jenisnya = stringResource(R.string.Year), isinya = ": ${data.tahun_terbit}")
        TextRow(jenisnya = stringResource(R.string.Category), isinya = ": ${data.kategori}")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(
                onClick ={},
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ){
                Text("Rp. ${data.harga};")
            }
        }
        Divider(color = androidx.compose.ui.graphics.Color.LightGray, thickness = 1.dp,modifier = Modifier.padding(vertical = 5.dp))
        Text(text = stringResource(R.string.Synopsis),
            style = TextStyle(
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = data.sinopsis)
        Divider(color = androidx.compose.ui.graphics.Color.LightGray, thickness = 1.dp,modifier = Modifier.padding(vertical = 5.dp))

    }
}

@Composable
fun TextRow(jenisnya: String, isinya:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .weight(1.0f)) {
            Text(text = jenisnya)
        }
        Row(modifier = Modifier
            .weight(2.0f)) {
            Text(text = isinya)
        }
    }
}