package com.example.android_beta_e_commerce



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager

import android.content.Intent


import android.widget.ImageView

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.android_beta_e_commerce.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//

class Home : AppCompatActivity(){
    lateinit var rvHome : RecyclerView
    lateinit var myAdapter: MyAdapter
    private lateinit var binding : ActivityMainBinding
    private lateinit var item: ImageView
    var BASE_URL = "https://fakestoreapi.com"
    lateinit var searchView : SearchView
    private  var list = ArrayList<ProductsItem>()
    private lateinit var cartCount:TextView
    private var cartQuantity:Int = 0;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvHome = findViewById(R.id.view1)
        searchView = findViewById(R.id.search)


        rvHome.layoutManager = GridLayoutManager(this, 2)
        getAllData()


        myAdapter = MyAdapter(this, list)
        rvHome.adapter = myAdapter

        fun onChanged(productItem: List<ProductsItem>) {
            for (item in productItem) {

            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               filterList(newText)
                return true

            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)

        val cartMenuItem = menu.findItem(R.id.cartIcon)
        val cartActionView = cartMenuItem.actionView

        if (cartActionView != null) {
            cartCount = cartActionView.findViewById(R.id.cartCount)
        }
        cartCount.setText("2")








    }


    private fun getAllData() {





        updateCartCount() // Initialize cart count display

        return true
    }

    private fun updateCartCount() {
        TODO("Not yet implemented")
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<ProductsItem>()
            for (item in list) {
                if (item.title.contains(query, ignoreCase = true)) {
                    filteredList.add(item)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                myAdapter.setFilteredList(filteredList)
            }
        }

    }


    private fun getAllData() {
        val retrofit = Retrofit.Builder()
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


        val apiService = retrofit.create(ApiInterface::class.java)
        val retroData = apiService.getData()

        retroData.enqueue(object : Callback<List<ProductsItem>> {
            override fun onResponse(call: Call<List<ProductsItem>>, response: Response<List<ProductsItem>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        list.addAll(data)
                        myAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@Home, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ProductsItem>>, t: Throwable) {
                Toast.makeText(this@Home, "Failed to fetch data: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })





    }



}

