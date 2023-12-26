package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMydata.adapter = MyAdapter(listOf())
        binding.rvMydata.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val retrofitInterface = MyRetrofit.retrofit.create(RetrofitInterface::class.java)
        retrofitInterface.getAllMyDataInCall()
            .enqueue(object : Callback<BaseResponse<List<MyData>>> {
                override fun onResponse(
                    call: Call<BaseResponse<List<MyData>>>,
                    response: Response<BaseResponse<List<MyData>>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("getAllMyDataInCall-success", response.body()!!.toString())
                        (binding.rvMydata.adapter as MyAdapter).setData(response.body()!!.result)
                    } else {
                        Log.d("getAllMyDataInCall-nonsuccess", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<BaseResponse<List<MyData>>>, t: Throwable) {
                    Log.d("getAllMyDataInCall-fail", t.message.toString())
                }
            })

        retrofitInterface.getMyDataByGetInCall(1)
            .enqueue(object : Callback<BaseResponse<MyData>> {
                override fun onResponse(
                    call: Call<BaseResponse<MyData>>,
                    response: Response<BaseResponse<MyData>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("getAllMyDataByGetInCall-success", response.body()!!.toString())
                    } else {
                        Log.d("getAllMyDataByGetInCall-nonsuccess", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<BaseResponse<MyData>>, t: Throwable) {
                    Log.d("getAllMyDataByGetInCall-fail", t.message.toString())
                }
            })

        retrofitInterface.getMyDataByPostInCall(MyDataRequestBody(1))
            .enqueue(object : Callback<BaseResponse<MyData>> {
                override fun onResponse(
                    call: Call<BaseResponse<MyData>>,
                    response: Response<BaseResponse<MyData>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("getAllMyDataByPostInCall-success", response.body()!!.toString())
                    } else {
                        Log.d(
                            "getAllMyDataByPostInCall-nonsuccess",
                            response.errorBody().toString()
                        )
                    }
                }

                override fun onFailure(call: Call<BaseResponse<MyData>>, t: Throwable) {
                    Log.d("getAllMyDataByPostInCall-fail", t.message.toString())
                }
            })

        CoroutineScope(Dispatchers.IO).launch {
            val allMyDataResponse = retrofitInterface.getAllMyDataInResponse()
            if (allMyDataResponse.isSuccessful) {
                Log.d("getAllMyDataInResponse-success", allMyDataResponse.body()!!.toString())
            } else {
                Log.d("getAllMyDataInResponse-fail", allMyDataResponse.errorBody()!!.toString())
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val myDataResponseByGet = retrofitInterface.getMyDataByGetInResponse(1)
            if (myDataResponseByGet.isSuccessful) {
                Log.d("getAllMyDataByGetInResponse-success", myDataResponseByGet.body()!!.toString())
            } else {
                Log.d("getAllMyDataByGetInResponse-fail", myDataResponseByGet.errorBody()!!.toString())
            }
        }


        CoroutineScope(Dispatchers.IO).launch {
            val myDataResponseByResponse =
                retrofitInterface.getMyDataByPostInResponse(MyDataRequestBody(1))
            if (myDataResponseByResponse.isSuccessful) {
                Log.d("getAllMyDataByPostInResponse-success", myDataResponseByResponse.body()!!.toString())
            } else {
                Log.d("getAllMyDataByPostInResponse-fail", myDataResponseByResponse.errorBody()!!.toString())
            }
        }

    }
}