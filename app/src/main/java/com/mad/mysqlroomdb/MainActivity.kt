package com.mad.mysqlroomdb


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.mad.mysqlroomdb.data.Product
import com.mad.mysqlroomdb.data.ProductDB
import com.mad.mysqlroomdb.data.ProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var dao : ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.btnInsert)

        dao = ProductDB.getInstance(application).productDao

        btn.setOnClickListener(){


            val name : String = findViewById<TextView>(R.id.tfName).text.toString()
            val price : Double = findViewById<TextView>(R.id.tfPrice).text.toString().toDouble()
            val p = Product(0, name, price)



            CoroutineScope(IO).launch {

                //calling the ProductDao interface's function
                dao.insertProduct(p)
            }


        }


        val btnGet : Button = findViewById(R.id.btnGet)
        btnGet.setOnClickListener(){

            CoroutineScope(IO).launch {


                var productName = ""

                //get all products
                // val productList: List<Product> = dao.getAll()


                // get price less than 5500
                val productList: List<Product> = dao.getPriceLessThan(5500.00)

                for(p : Product in productList){
                    productName += p.name + "\n"

                }

                CoroutineScope(Main).launch {
                    val tvResult: TextView = findViewById(R.id.tvResult)
                    tvResult.text = productName
                }
            }
        }

    }
}