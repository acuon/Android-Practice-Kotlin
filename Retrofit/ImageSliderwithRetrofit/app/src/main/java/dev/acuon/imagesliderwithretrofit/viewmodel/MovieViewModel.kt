package dev.acuon.imagesliderwithretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.acuon.imagesliderwithretrofit.api.MovieItem
import dev.acuon.imagesliderwithretrofit.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel constructor(private val repository: MovieRepository) : ViewModel() {

    val movieList = MutableLiveData<List<MovieItem>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<List<MovieItem>> {
            override fun onResponse(call: Call<List<MovieItem>>, response: Response<List<MovieItem>>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<MovieItem>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

}

