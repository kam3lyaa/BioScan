package br.com.fiap.bioscan.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface PlantNetApiService {

    @Multipart
    @POST("v2/identify/all")
    suspend fun identifyPlant(
        @Query("api-key") apiKey: String,
        @Query("lang") lang: String,
        @Part images: MultipartBody.Part,
        @Part("organs") organs: okhttp3.RequestBody
    ): Response<PlantNetResponse>
}