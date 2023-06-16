package com.dicoding.c23_pc713.gihariapp

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface BotApi {
    @POST("predict_text")
    fun predictText(@Body body: RequestBody): Call<MessageResponse>
}