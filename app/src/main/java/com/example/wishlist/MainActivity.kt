package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var list: MutableList<WishList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val itemRV = findViewById<RecyclerView>(R.id.itemRV)
        list = mutableListOf()
        val adapter = WishListAdapter(list)
        itemRV.adapter = adapter
        itemRV.layoutManager = LinearLayoutManager(this)

        val editName = findViewById<EditText>(R.id.itemName)
        val editPrice = findViewById<EditText>(R.id.itemPrice)
        val editURL = findViewById<EditText>(R.id.itemURL)
        val submitButton = findViewById<Button>(R.id.submitBTN)

        submitButton.setOnClickListener {
            val name = editName.text.toString().trim()
            val price = editPrice.text.toString().trim()
            var url = editURL.text.toString().trim()

            if (name.isNotEmpty() && price.isNotEmpty() && url.isNotEmpty()) {
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://$url"
                }

                val newItem = WishList(name, price, url)
                list.add(newItem)
                adapter.notifyDataSetChanged()

                itemRV.scrollToPosition(list.size - 1)

                editName.text.clear()
                editPrice.text.clear()
                editURL.text.clear()


            }
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}