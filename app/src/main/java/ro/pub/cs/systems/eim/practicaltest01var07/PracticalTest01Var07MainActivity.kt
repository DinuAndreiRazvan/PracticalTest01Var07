package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.pub.cs.systems.eim.practicaltest01var07.ui.theme.PracticalTest01Var07Theme
import kotlin.random.Random

class PracticalTest01Var07MainActivity : ComponentActivity() {
    private var sum = 0
    private val secondaryActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val message = when (result.resultCode) {
            Activity.RESULT_OK -> {
                val data: Intent? = result.data
                val sum = data?.getIntExtra("sum", 0)
                "Sum = $sum"
            }
            Activity.RESULT_CANCELED -> {
                val data: Intent? = result.data
                val prod = data?.getIntExtra("prod", 0)
                "Prod = $prod"
            }
            else -> "No result"
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.d("Secondary", "Received - $message")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticalTest01Var07Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PracticalTestScreen(
                        onNavigate = { intent -> secondaryActivityLauncher.launch(intent) }
                    )
                }
            }
        }
    }
}

@Composable
fun PracticalTestScreen (
    onNavigate: (Intent) -> Unit,
    modifier: Modifier = Modifier) {

    /* Extra UI elem */
    var text_11 by rememberSaveable { mutableStateOf("") }
    var text_12 by rememberSaveable { mutableStateOf("") }
    var text_21 by remember { mutableStateOf("") }
    var text_22 by remember { mutableStateOf("") }

    /* Activity Result Launcher to get the result from SecondaryActivity */
    val context = LocalContext.current

    /* UI Elements */
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer (modifier = Modifier.height(24.dp))
        Button (
            onClick = {
                val intent = Intent(context, PracticalTest01Var07SecondaryActivity::class.java)
                intent.putExtra(Constants.NUM_11, text_11)
                intent.putExtra(Constants.NUM_12, text_12)
                intent.putExtra(Constants.NUM_21, text_21)
                intent.putExtra(Constants.NUM_22, text_22)
                onNavigate(intent)
            } ) {
            Text("Set")
        }
        Spacer (modifier = Modifier.height(16.dp))

        Row (
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = text_11,
                onValueChange = { text_11 = it },
                singleLine = true,
                modifier = Modifier
                    .size(80.dp)
                    .fillMaxWidth()
            )
            TextField(
                value = text_12,
                onValueChange = { text_12 = it },
                singleLine = true,
                modifier = Modifier
                    .size(80.dp)
                    .fillMaxWidth()
            )
        }
        Spacer (modifier = Modifier.height(16.dp))

        Row (
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = text_21,
                onValueChange = { text_21 = it },
                singleLine = true,
                modifier = Modifier
                    .size(80.dp)
                    .fillMaxWidth()
            )
            TextField(
                value = text_22,
                onValueChange = { text_22 = it },
                singleLine = true,
                modifier = Modifier
                    .size(80.dp)
                    .fillMaxWidth()
            )
        }
        Spacer (modifier = Modifier.height(20.dp))

        Button (
            onClick = {
                try {
                    text_11.toInt()
                } catch (e: NumberFormatException) {
                    text_11 = Random.nextInt(0, 10).toString()
                }

                try {
                    text_12.toInt()
                } catch (e: NumberFormatException) {
                    text_12 = Random.nextInt(0, 10).toString()
                }

                try {
                    text_21.toInt()
                } catch (e: NumberFormatException) {
                    text_21 = Random.nextInt(0, 10).toString()
                }

                try {
                    text_22.toInt()
                } catch (e: NumberFormatException) {
                    text_22 = Random.nextInt(0, 10).toString()
                }
            } ) {
            Text("Random")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrimaryScreenPreview() {
    PracticalTest01Var07Theme {
        PracticalTestScreen(
            onNavigate = { }
        )
    }
}