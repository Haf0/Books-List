package com.tugas.buku.screen

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tugas.buku.R

@Composable
fun AboutScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) { Identitas() }

}
@Preview(showBackground = true)
@Composable
fun Identitas() {
    val context = LocalContext.current
    val i = Intent(Settings.ACTION_LOCALE_SETTINGS)
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(250.dp, 250.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ku),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

        }
        Spacer(modifier = Modifier.padding(10.dp))
        TextIdentitas(jenisnya = stringResource(R.string.Name), isinya =": Hafit Abekrori" )
        TextIdentitas(jenisnya = stringResource(R.string.StudentNumber), isinya =": 19.11.2765" )
        TextIdentitas(jenisnya = stringResource(R.string.Class), isinya =": 19-S1IF-03" )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = stringResource(R.string.app_name),
            style = TextStyle(
                fontSize = 30.sp
            )
        )
        Divider(color = Color.LightGray, thickness = 2.dp,modifier = Modifier.padding(vertical = 20.dp))
        Text(text = stringResource(R.string.AboutApp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick =
                {
                    context.startActivity(i)
                },
                Modifier.padding(top = 20.dp)
            ) {
                Text(text = stringResource(R.string.TextButton))
            }
        }
    }
}

@Composable
fun TextIdentitas(jenisnya: String, isinya:String) {
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