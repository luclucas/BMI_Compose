package com.lulu.bmi_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.pow

class MainActivity : ComponentActivity() {

    private var height = 0F
    private var weight = 0F

    private val imc = mutableStateOf(0F)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Structure()
        }
    }


    @Composable
    private fun Structure() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.
            fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                space = 20.dp
            )

        ) {
            HeightInput()
            WeightInput()
            CalculateButton()
            TextResult()
        }
    }

    @Composable
    private fun HeightInput() {
        val textHeight = remember { mutableStateOf("") }
        TextField(
            value = textHeight.value,
            onValueChange = { newValue ->
                if(newValue.isEmpty()||newValue.toFloat().isNaN()){
                    textHeight.value = ""
                } else{
                textHeight.value = newValue
                height = textHeight.value.toFloat()}
            },
            label = { Text("Altura (1.52)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(top = 40.dp)
            )
    }

    @Composable
    private fun WeightInput() {
        val textWeight = remember { mutableStateOf("") }
        TextField(value = textWeight.value, onValueChange = { newValue ->
            if(newValue.isEmpty()||newValue.toFloat().isNaN()){
                textWeight.value = ""
            } else {
                textWeight.value = newValue
                weight = newValue.toFloat()
            }
        }, label = { Text("Peso (Kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

    @Composable
    private fun CalculateButton(){
        Button(onClick = {
            imc.value = (weight/height.pow(2))
        }){ Text(text = "Calculate")}


    }

    @Composable
    private fun TextResult(){
        val x by imc
        Text(text = String.format("Seu IMC é %.2f", x))
    }
}
