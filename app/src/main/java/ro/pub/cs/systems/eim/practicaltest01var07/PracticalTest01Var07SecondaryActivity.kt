package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.pub.cs.systems.eim.practicaltest01var07.ui.theme.PracticalTest01Var07Theme


class PracticalTest01Var07SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val num_11 = intent.getStringExtra(Constants.NUM_11) ?: "0"
        val num_12 = intent.getStringExtra(Constants.NUM_12) ?: "0"
        val num_21 = intent.getStringExtra(Constants.NUM_21) ?: "0"
        val num_22 = intent.getStringExtra(Constants.NUM_22) ?: "0"

        /* Jetpack Compose entry point in activity */
        setContent {
            PracticalTest01Var07Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SecondaryScreen(
                        num_11 = num_11,
                        num_12 = num_12,
                        num_21 = num_21,
                        num_22 = num_22,
                        onSumClick = {
                            val sum = (num_11.toIntOrNull() ?: 0) +
                                    (num_12.toIntOrNull() ?: 0) +
                                    (num_21.toIntOrNull() ?: 0) +
                                    (num_22.toIntOrNull() ?: 0)
                            val resultIntent = Intent()
                            resultIntent.putExtra("sum", sum)
                            setResult(Activity.RESULT_OK, resultIntent)
                            finish()
                        },
                        onProdClick = {
                            val prod = (num_11.toIntOrNull() ?: 0) *
                                    (num_12.toIntOrNull() ?: 0) *
                                    (num_21.toIntOrNull() ?: 0) *
                                    (num_22.toIntOrNull() ?: 0)
                            val resultIntent = Intent()
                            resultIntent.putExtra("prod", prod)
                            setResult(Activity.RESULT_CANCELED, resultIntent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun SecondaryScreen(num_11: String, num_12: String, num_21: String, num_22: String, onSumClick: () -> Unit, onProdClick: () -> Unit, modifier: Modifier = Modifier) {
    /* UI Elements */
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text (text = "Instructions: $num_11")
            Text (text = "Instructions: $num_12")
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text (text = "Instructions: $num_21")
            Text (text = "Instructions: $num_22")
        }


        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onSumClick) {
                Text("Sum +")
            }
            Button(onClick = onProdClick) {
                Text("Prod *")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SecondaryScreenPreview() {
    PracticalTest01Var07Theme {
        SecondaryScreen(
            num_11 = "",
            num_12 = "",
            num_21 = "",
            num_22 = "",
            onSumClick = {},
            onProdClick = {}
        )
    }
}