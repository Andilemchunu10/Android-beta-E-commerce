package com.example.android_beta_e_commerce


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//

class Home : AppCompatActivity(){
    lateinit var rvHome : RecyclerView
    lateinit var myAdapter: MyAdapter
    var BASE_URL = "http://192.168.10.121:8081/api/products/"
    lateinit var searchView : SearchView
    private  var list = ArrayList<ProductsItem>()
    private var l= ArrayList<Image>()
    private lateinit var cartCount:TextView
    private var cartQuantity:Int = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvHome = findViewById(R.id.view1)
        searchView = findViewById(R.id.search)
        rvHome.layoutManager = GridLayoutManager(this, 2)
        getAllData()

        myAdapter = MyAdapter(this,list)

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
        getAllData()
        updateCartCount()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)

        val cartMenuItem = menu.findItem(R.id.cartIcon)
        val cartActionView = cartMenuItem.actionView

        if (cartActionView != null) {
            cartCount = cartActionView.findViewById(R.id.cartCount)
        }
       //cartCount.setText("2")
        updateCartCount() // Initialize cart count display
        return true
    }

    private fun updateCartCount() {
     //  cartCount.text = list.size.toString()
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<ProductsItem>()
            for (item in list) {
                if (item.name.contains(query, ignoreCase = true)) {
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

        val apiService = retrofit.create(ApiInterface::class.java)
        val retroData = apiService.getData()

        retroData.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null) {
                        val data = apiResponse.data
                        list.addAll(data)
                        Log.d("data", data.toString())
                        myAdapter.notifyDataSetChanged()
                        myAdapter.setOnItemClicklistener(object : MyAdapter.onItemClickListener {
                            override fun onItemClickListener(position: Int) {

                                //val intent = Intent(this@Home, ViewOneActivity2::class.java)
                                //intent.putExtra("information", data)

                                Toast.makeText(this@Home,"You Clicked on. $position",Toast.LENGTH_SHORT).show()

                            }

                            override fun onBindViewHolder(
                                holder: MyAdapter.ViewHolder,
                                position: Int
                            ) {
                                TODO("Not yet implemented")
                            }


                        }
                        )
                    }
                } else {
                    Log.e("Home", "Failed to fetch data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("Home", "Failed to fetch data: ${t.message}")
            }
        })
    }

    private fun addToCart(item:ProductsItem){
        list.add(item)
        Toast.makeText(this,"Added items",Toast.LENGTH_SHORT).show()
        updateCartCount()
    }


}