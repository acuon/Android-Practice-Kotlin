package dev.acuon.imagesliderwithretrofit.repository

import dev.acuon.imagesliderwithretrofit.api.ApiService

class MovieRepository constructor(private val service: ApiService) {

    fun getAllMovies() = service.getAllMovies()

}