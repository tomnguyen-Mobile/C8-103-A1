package com.mdi2.c8_103_a1

// CHALLENGE 1
data class BakeryItem(
    val name: String,
    val sold: Double,
    val price: Double
){
    // CHALLENGE 2 - Adding a behavior to the data class.
    fun revenue(): Double{
        return sold * price
    }

}
