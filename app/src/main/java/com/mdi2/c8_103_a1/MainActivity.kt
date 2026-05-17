package com.mdi2.c8_103_a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdi2.c8_103_a1.ui.theme.C8103A1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BakeryRevenueScreen()
        }
    }
}

// CHALLENGE 3 - Composable function
@Composable
fun BakeryRevenueScreen(){
    val currentSymbol = "$"
    var cookiesSold by remember { mutableStateOf(value= "") }
    var cookiesPrice by remember { mutableStateOf(value= "") }
    // CHALLENGE
    var muffinsSold by remember { mutableStateOf(value= "") }
    var muffinPrice by remember { mutableStateOf(value= "") }
    var cakesSold by remember { mutableStateOf(value= "") }
    var cakePrice by remember { mutableStateOf(value= "") }

    // DATA STRUCTURE
    val bakeryItems = remember { mutableStateListOf<BakeryItem>() }
    // Q: how do we know when to use = remember instead of by remember?
    // A: when we are using data types use by remember
    //    otherwise collections, data structure, or data class use = remember
    var totalRevenue by remember { mutableDoubleStateOf(value= 0.0) }
    var bestSellingItem by remember { mutableStateOf(value= "") }
    var errorMessage by remember { mutableStateOf(value= "") }

    fun onCalculateRevenue(){
        val cookiesP = cookiesPrice.toDoubleOrNull()
        val cookiesS = cookiesSold.toDoubleOrNull()
        val muffinP = muffinPrice.toDoubleOrNull()
        val muffinsS = muffinsSold.toDoubleOrNull()
        val cakesP = cakePrice.toDoubleOrNull()
        val cakesS = cakesSold.toDoubleOrNull()

        if( cookiesS == null || cookiesP == null || // ON CALCULATE REVENUE
            muffinP == null || muffinsS == null ||
            cakesP == null || cakesS == null
        ){
            errorMessage = "Please enter valid numeric values"
        } else{
            errorMessage = ""
        } // end of fun - ON CALCULATE REVENUE
    }

    Column(modifier = Modifier //COLUMN 1
                        .fillMaxSize()
        .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text( // HEADER OF THE SCREEN
            text = "Bakery Revenue Calculator",
            style = MaterialTheme.typography.headlineMedium
        )// end of text for bakery revenue calculator - HEADER OF THE SCREEN

        Text( // GETTING THE SALES INFORMATION
            text = "Enter the sales information for each product",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom= 16.dp)
        )// end of text - GETTING THE SALES INFORMATION

        OutlinedTextField( // COOKIES PRICE
            value = cookiesPrice,
            onValueChange = { cookiesPrice = it },
            label = { Text("Price per cookies") },
            modifier = Modifier
                .fillMaxWidth()
        )// end of outlinedtextfield - COOKIES PRICE
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField( // COOKIES SOLD
            value = cookiesSold,
            onValueChange = { cookiesSold = it },
            label = { Text("Cookies sold") },
            modifier = Modifier
                .fillMaxWidth()
        )// end of outlinedtextfield - COOKIES SOLD
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField( // MUFFIN PRICE
            value = muffinPrice,
            onValueChange = { muffinPrice = it },
            label = { Text("Price per muffin") },
            modifier = Modifier
                .fillMaxWidth()
        )// end of outlinedtextfield - MUFFIN PRICE
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField( // COOKIES SOLD
            value = muffinsSold,
            onValueChange = { muffinsSold = it },
            label = { Text("Muffins sold") },
            modifier = Modifier
                .fillMaxWidth()
        )// end of outlinedtextfield - MUFFINS SOLD
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField( // CAKE PRICE
            value = cakePrice,
            onValueChange = { cakePrice = it },
            label = { Text("Price per cake") },
            modifier = Modifier
                .fillMaxWidth()
        )// end of outlinedtextfield - CAKE PRICE
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField( // CAKES SOLD
            value = cakesSold,
            onValueChange = { cakesSold = it },
            label = { Text("Cakes sold") },
            modifier = Modifier
                .fillMaxWidth()
        )// end of outlinedtextfield - CAKES SOLD
        Spacer(modifier = Modifier.height(8.dp))

        // challenge 1 Assignment 2
        Button( // CALCULATE REVENUE
            onClick = { onCalculateRevenue() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calculate Revenue")
        } // end of button  - CALCULATE REVENUE

    }// end of - COLUMN 1


}

@Preview(showBackground = true)
@Composable
fun BakeryRevenueScreenPreview() {
    BakeryRevenueScreen()
}