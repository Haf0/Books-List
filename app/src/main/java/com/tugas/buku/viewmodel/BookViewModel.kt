package com.tugas.buku.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugas.buku.api.ApiService
import com.tugas.buku.model.Books
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    private val api: ApiService = ApiService.getInstance()
    var BooksList : List<Books> by mutableStateOf(listOf())
    var books : Books? = null

    init{
        getBooksList()
    }

    private fun getBooksList(){
        viewModelScope.launch {
            try {
                BooksList = api.getBooks().result
            }catch (e: Exception){
                Log.d("Error: ", e.message.toString())
            }
        }
    }

    fun getDetailBooks(id: Int){
        viewModelScope.launch {
            try {
                books=BooksList.first {it.id==id}
                Log.d("DBG VM", books.toString())
            }catch (e: Exception){
                Log.d("Error: ", e.message.toString())
            }
        }
    }
}