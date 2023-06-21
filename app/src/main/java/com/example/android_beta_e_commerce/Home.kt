package com.example.android_beta_e_commerce
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageView
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

class Home : AppCompatActivity() {
    lateinit var rvHome: RecyclerView
    lateinit var myAdapter: MyAdapter
    var BASE_URL = "http://10.100.0.97:8081/api/products/"
    lateinit var searchView : SearchView
    private  var list = ArrayList<ProductsItem>()

    private lateinit var cartCount: TextView


    private lateinit var cartIcon:ImageView
    private lateinit var profileIcon: ImageView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvHome = findViewById(R.id.view1)
        searchView = findViewById(R.id.search)

        cartIcon = findViewById(R.id.cartIcon)

        cartCount = findViewById(R.id.cartCount)
        updateCartCount(CartManager.getCartCount())

        profileIcon = findViewById(R.id.profileIcon)


        findViewById<ImageView>(R.id.productImg).setOnClickListener {
            onCategoryImageClick("Fruits")
        }
        findViewById<ImageView>(R.id.meatImg).setOnClickListener {
            onCategoryImageClick("Meat")
        }
        findViewById<ImageView>(R.id.cerealsImg).setOnClickListener {
            onCategoryImageClick("Breakfast")
        }
        findViewById<ImageView>(R.id.frozenImg).setOnClickListener {
            onCategoryImageClick("Frozens")
        }
        findViewById<ImageView>(R.id.imageView6).setOnClickListener {
            onCategoryImageClick("Bakery")
        }

        rvHome.layoutManager = GridLayoutManager(this, 2)

        getAllData()
        myAdapter = MyAdapter(this,list)
        rvHome.adapter = myAdapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        // Get the cart items from the CartManager
        val cartItems = CartManager.getCartItems()

        // Update the cart count TextView
        updateCartCount(cartItems.size)

        // Set cartCount visibility based on the cartItems size
        cartCount.visibility = if (cartItems.isNotEmpty()) {
            View.VISIBLE // Display the cartCount TextView
        } else {
            View.INVISIBLE // Hide the cartCount TextView
        }

        cartIcon.setOnClickListener{
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

        profileIcon.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

    }

    private fun updateCartCount(count: Int) {
        cartCount.text = count.toString()
    }
    private fun onCategoryImageClick(category: String) {
        myAdapter.filterByCategory(category)
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
}
