package com.mad.mysqlroomdb.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {


    @Insert
    fun insertProduct(p : Product)

    //function to get all the products
    @Query("SELECT * FROM product_table ")
    fun getAll() : List<Product>


    //////function to get product with certain price
    @Query("SELECT * FROM product_table where price < :price  ")
    fun getPriceLessThan(price:Double) : List<Product>


    ///Delete all records
    @Query("DELETE FROM product_table ")
    fun clear()


}