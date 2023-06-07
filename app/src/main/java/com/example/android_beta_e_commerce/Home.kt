package com.example.android_beta_e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_beta_e_commerce.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


//

class Home : AppCompatActivity(){
    lateinit var rvHome : RecyclerView
    lateinit var myAdapter: MyAdapter
    private lateinit var binding : ActivityMainBinding
    private lateinit var item: ImageView
    var BASE_URL = "https://fakestoreapi.com"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvHome = findViewById(R.id.view1)
        rvHome.layoutManager = LinearLayoutManager(this)
        getAllData()





    }


    private fun getAllData() {

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        var retroData = retrofit.getData()

        retroData.enqueue(object : Callback<List<ProductsItem>>{
            override fun onResponse(
                call: Call<List<ProductsItem>>,
                response: Response<List<ProductsItem>>
            ) {
               var data = response.body()!!
                myAdapter = MyAdapter(baseContext,data)
                rvHome.adapter = myAdapter
                Log.d("data",data.toString())

                myAdapter.setOnItemClicklistener(object :MyAdapter.onItemClickListener{
                    override fun onItemClickListener(position: Int) {

                        val intent = Intent(this@Home, ViewOneActivity2::class.java)
                        intent.putExtra(myAdapter, data)
                        startActivity(intent)
                    //Toast.makeText(this@Home,"You Clicked on. $position",Toast.LENGTH_SHORT).show()
                    }

                })
            }

            override fun onFailure(call: Call<List<ProductsItem>>, t: Throwable) {

            }

        })





    }

}

private fun Intent.putExtra(myAdapter: MyAdapter, data: List<ProductsItem>) {

}
