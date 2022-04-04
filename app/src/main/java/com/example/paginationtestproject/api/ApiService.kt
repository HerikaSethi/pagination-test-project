package com.example.paginationtestproject.api

import com.example.paginationtestproject.model.PicResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/*const val BASE_URL:String = "https://picsum.photos"

interface ApiInterface {
    @GET("v2/list")
   fun getPic(@Query("page")page:Int, @Query("limit")limit:Int):Response<PicResult>
}

object ApiService {
    val picInstance: ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        picInstance = retrofit.create(ApiInterface::class.java)
    }
}*/


const val BASE_URL:String = "https://picsum.photos"

interface ApiInterface {

    @GET("v2/list")
    fun getPic(@Query("page")page:Int, @Query("limit")limit:Int): Call<PicResult>
}


object ApiService{
    val picInstance: ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        picInstance = retrofit.create(ApiInterface::class.java)
    }
}