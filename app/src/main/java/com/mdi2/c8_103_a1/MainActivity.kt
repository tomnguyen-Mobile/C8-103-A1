package com.mdi2.c8_103_a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
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
            onClick = {
                fun validationForUser(inputFromUser: String): Boolean {
                    val number: Double? = inputFromUser.toDoubleOrNull()
                    return inputFromUser.isBlank() || number == null || number < 0  // check for empty field || number is null || not negative number
                }

                fun invalidInput(input: String): String {
                    if (input.isBlank()){ return "empty"}
                    if (input.toDoubleOrNull() == null){ return "not a numeric value"}
                    if (input.toDouble() < 0){ return "a negative number"}
                    return "null"
                }


                if ( // REVENUE
                    validationForUser(cookiesPrice) ||
                    validationForUser(cookiesSold) ||
                    validationForUser(muffinPrice) ||
                    validationForUser(muffinsSold) ||
                    validationForUser(cakePrice) ||
                    validationForUser(cakesSold)
                ){
                    val validationError: String = when {
                        validationForUser(cookiesPrice) -> "Cookie price field is ${invalidInput(cookiesPrice)}"
                        validationForUser(cookiesSold) -> "Cookies sold field is ${invalidInput(cookiesSold)}"
                        validationForUser(muffinPrice) -> " Muffin price field is ${invalidInput(muffinPrice)}"
                        validationForUser(muffinsSold) -> " Muffins sold field is ${invalidInput(muffinsSold)}"
                        validationForUser(cakePrice) -> " Cake price field is ${invalidInput(cakePrice)}"
                        validationForUser(cakesSold) -> " Cakes sold field is ${invalidInput(cakesSold)}"
                        else -> "null"
                    }
                    errorMessage = "Invalid input: $validationError"
                    return@Button // Don't need else if using return@Button
                } //else {
                    errorMessage = ""

                    val cookieP: Double = cookiesPrice.toDouble()
                    val cookiesS: Double = cookiesSold.toDouble()
                    val muffinP: Double = muffinPrice.toDouble()
                    val muffinsS: Double = muffinsSold.toDouble()
                    val cakeP: Double = cakePrice.toDouble()
                    val cakesS: Double = cakesSold.toDouble()

                    bakeryItems.clear()
                    bakeryItems.add(BakeryItem(name= "Cookies", sold= cookiesS, price= cookieP))
                    bakeryItems.add(BakeryItem("Muffins", muffinsS, muffinP))
                    bakeryItems.add(BakeryItem("Cakes", cakesS, cakeP))

                    totalRevenue = bakeryItems.sumOf{ it.revenue() }

                    val topItem = bakeryItems.maxByOrNull { it.revenue() }

                    bestSellingItem = topItem?.name ?: ""
                //}// end of if statement for - REVENUE
            }, // end of onclick
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate Revenue")
        } // end of button  - CALCULATE REVENUE

        Spacer(modifier = Modifier.height(16.dp))

        if(errorMessage.isNotEmpty()){ // ERROR MESSAGE
            Text(
                text = errorMessage,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))
        }// end of it statement for - ERROR MESSAGE

        Card( // CARD 1
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp), // give it a shadow on the back to make it look raised
        ){
            Column( // CARD COLUMN 1
                modifier = Modifier.padding(16.dp)
            ){

                Text( // class 02 - challenge 4
                    text = "Daily Revenue Report",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                if(bakeryItems.isEmpty()){ Text("No report available yet.") } // No report
                else{
                    LazyColumn(modifier = Modifier.fillMaxWidth()){ // DISPLAY ITEMS
                        items( bakeryItems){ item ->
                            Text(text="${item.name}: $currentSymbol${"%.2f".format(item.revenue())}")
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }// end of lazycolumn -DISPLAY ITEMS
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Best revenue item: $bestSellingItem",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text="Total revenue: $currentSymbol${"%.2f".format(totalRevenue)}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }// end of else
            }// end of column for - CARD COLUMN 1
        } // end of card - CARD 1


    }// end of - COLUMN 1


}

// Lazy Column - generate a scrollable list that is limited in how many items are shown
// Lazy Row
// Lazy grids
// helps the performance of the system by load by loading a little at a time instead of everything.

@Preview(showBackground = true)
@Composable
fun BakeryRevenueScreenPreview() {
    BakeryRevenueScreen()
}