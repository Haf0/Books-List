package com.tugas.buku.dao

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tugas.buku.model.Books
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavVMFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FavViewModel(application = application) as T
}
class FavViewModel(application: Application): ViewModel() {
    private val repository: FavRepo
    private var _fetchAll: LiveData<List<Books>>

    init {
        val favDb = DBbuku.getDb(application).favoriteDao()
        repository = FavRepo(favDb)
        _fetchAll = repository.getfav()
    }

    fun getFav() : LiveData<List<Books>> {
        return _fetchAll
    }

    fun addFavourite(data: Books) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBuku(data)
        }
    }

    fun deleteFavourite(data: Books) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBuku(data)
        }
    }

}