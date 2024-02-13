package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ClickableAndInstruction()
                }
            }
       }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClickableAndInstruction(modifier: Modifier = Modifier) {
    var step by remember {
        mutableIntStateOf(1)
    }
    var timesLemonWasPressed by remember {
        mutableIntStateOf(0)
    }
    val timesToPress = (1..10).random();

    val stepId = when(step) {
        1 -> arrayOf(R.string.instruction_1, R.drawable.lemon_tree, R.string.lemon_tree)
        2 -> arrayOf(R.string.instruction_2, R.drawable.lemon_squeeze, R.string.lemon)
        3 -> arrayOf(R.string.instruction_3, R.drawable.lemon_drink, R.string.glass_of_lemonade)
        else -> arrayOf(R.string.instruction_4, R.drawable.lemon_restart, R.string.empty_glass)
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(onClick = {
            when(step) {
                1, 3 -> step++
                2 -> {
                    if(timesLemonWasPressed < timesToPress) {
                        timesLemonWasPressed++;
                    } else {
                        timesLemonWasPressed = 0;
                        step++
                    }
                }
                4 -> step = 1
            }
        }) {
            Image(
                painter = painterResource(stepId[1]),
                contentDescription = stringResource(stepId[2]),
                modifier = Modifier.padding(24.dp)
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = stringResource(stepId[0]),
            fontSize = 20.sp,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImageAndTextPreview() {
    LemonadeTheme {
        ClickableAndInstruction()
    }
}
