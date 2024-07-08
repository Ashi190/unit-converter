package com.example.unitconverter

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter() {
    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Meters") }
    var outputunit by remember { mutableStateOf("Meters") }
    var iexpanded by remember { mutableStateOf(false) }
    var oexpanded by remember { mutableStateOf(false) }
    val conversionfactor = remember { mutableStateOf(1.00) }
    val oconversionfactor = remember { mutableStateOf(1.00) }



    fun convertunits(){
        val inputvaluedouble = inputvalue.toDoubleOrNull() ?: 0.0//return 0.0 by default
        val result = (inputvaluedouble * conversionfactor.value * 100.0/oconversionfactor.value).roundToInt()/100.0
        outputvalue = result.toString()
    }

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
          ){
        //yeha upar neeche print hoga
       Text(text = "unit converter",style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue, onValueChange = {
            inputvalue = it
            convertunits()
        },
            label = {Text("Enter the value")})

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //here all the UI element will stack over each other
           //yeha side side mai hoga
            //val context = LocalContext.current
            // Button(onClick = { Toast.makeText(context,
              //  "thanks for clicking",Toast.LENGTH_LONG).show()})
          //  {
            //    Text(text = "my button")
           // }
            //input box
            Box{
                //input button
                 Button({iexpanded = true}) {
                   Text(text = inputunit)
                     Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                 }
              DropdownMenu(expanded =iexpanded, onDismissRequest = {iexpanded = false}) {
                  DropdownMenuItem(text = { Text("Centimeters")},
                      onClick = {
                      iexpanded = false
                      inputunit =  "Centimeters"
                      conversionfactor.value = 0.01
                          convertunits()
                                       })
                  DropdownMenuItem(text = { Text("Meters")},
                      onClick ={
                          iexpanded = false
                          inputunit =  "Meters"
                          conversionfactor.value = 1.0
                          convertunits()
                      })
                  DropdownMenuItem(text = { Text("Feet")},
                      onClick = {
                          iexpanded = false
                          inputunit =  "Feet"
                          conversionfactor.value = 0.3048
                          convertunits()
                      })
                  DropdownMenuItem(text = { Text("Milimeters")},
                      onClick = {
                          iexpanded = false
                          inputunit =  "Milimeters"
                          conversionfactor.value = 0.001
                          convertunits()
                      })
              }
            }

            Spacer(modifier = Modifier.width(16.dp))

            //output box
            Box{
                //output button
                Button(onClick = {oexpanded = true}) {
                    Text(text = outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded =oexpanded, onDismissRequest = {oexpanded = false}) {
                    DropdownMenuItem(text = { Text("Centimeters")},
                        onClick = {
                            oexpanded = false
                            outputunit =  "Centimeters"
                            oconversionfactor.value = 0.01
                            convertunits()
                        })
                    DropdownMenuItem(text = { Text("Meters")},
                        onClick = {
                            oexpanded = false
                            outputunit =  "Meters"
                            oconversionfactor.value = 1.00
                            convertunits()
                        })
                    DropdownMenuItem(text = { Text("Feet")},
                        onClick = {
                            oexpanded = false
                            outputunit =  "Feet"
                            oconversionfactor.value = 0.3048
                            convertunits()
                        })
                    DropdownMenuItem(text = { Text("Milimeters")},
                        onClick = {
                            oexpanded = false
                            outputunit =  "Milimeters"
                            oconversionfactor.value = 0.01
                            convertunits()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "result: $outputvalue $outputunit",
            style = MaterialTheme.typography.headlineMedium
            )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}