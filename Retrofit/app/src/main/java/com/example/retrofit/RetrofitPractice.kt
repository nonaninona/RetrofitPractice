package com.example.retrofit

import com.google.gson.annotations.SerializedName
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class MyRetrofit {
    companion object {
        private val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        private val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://7222-222-109-251-172.ngrok-free.app")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}

interface RetrofitInterface {

    @GET("/mydata/get")
    fun getAllMyDataInCall(

    ) : Call<BaseResponse<List<MyData>>>

    @GET("/mydata/get/{id}")
    fun getAllMyDataByGetInCall(
        @Path("id") id: Int
    ) : Call<BaseResponse<MyData>>

    @POST("/mydata/post")
    fun getAllMyDataByPostInCall(
        @Body myDataRequestBody: MyDataRequestBody
    ) : Call<BaseResponse<MyData>>



    @GET("/mydata/get")
    suspend fun getAllMyDataInResponse(

    ) : Response<BaseResponse<List<MyData>>>

    @GET("/mydata/get/{id}")
    suspend fun getAllMyDataByGetInResponse(
        @Path("id") id: Int
    ) : Response<BaseResponse<MyData>>

    @POST("/mydata/post")
    suspend fun getAllMyDataByPostInResponse(
        @Body myDataRequestBody: MyDataRequestBody
    ) : Response<BaseResponse<MyData>>

}

data class MyData(
   @SerializedName("id") val id: Int,
   @SerializedName("title") val title: String,
   @SerializedName("content") val content: String
)

data class BaseResponse<T>(
    @SerializedName("success") val success: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: T
)

data class MyDataRequestBody(
    @SerializedName("id") val id: Int
)