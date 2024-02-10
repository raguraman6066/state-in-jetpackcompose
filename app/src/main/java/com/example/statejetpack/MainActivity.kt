package com.example.statejetpack

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.statejetpack.ui.theme.StateJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // Greeting("Android")
                  //  demoState()
                    demoTextField()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun demoState(){
    //var count=0ß
    var count by remember {
        mutableStateOf(0)
    }

    Column {
        Button(onClick = {
            count++
        }) {
            Text(text = "Click Me $count")
        }
    }
}


private fun validateAge(inputText:String):Boolean{
    return inputText.toInt()<18
}
@Composable
fun demoTextField(){
    var enteredValue by remember {
        mutableStateOf("")
    }

    var isUserBelow18 by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        //custom text field
        TextField(value = enteredValue, onValueChange = {newtext->enteredValue=newtext}, label = { Text(
            text = "Name"
        )}, placeholder = { Text(text = "Enter your name")}, leadingIcon = { Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Email"
        )}, isError = isUserBelow18, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done), keyboardActions = KeyboardActions( onDone = {
            isUserBelow18= validateAge(enteredValue)
        }))
        if(isUserBelow18){
            Text(text = "You should be 18+", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StateJetpackTheme {
        Greeting("Android")
    }
}

//stateful and stateless composable
