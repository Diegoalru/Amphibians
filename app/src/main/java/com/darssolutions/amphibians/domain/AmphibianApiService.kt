package com.darssolutions.amphibians.domain

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = retrofit2.Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AmphibiansApiService {
    @GET("android-basics-kotlin-unit-4-pathway-2-project-api.json")
    suspend fun getAmphibians(): List<Amphibian>?
}

object AmphibiansApi {
    val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }
}
