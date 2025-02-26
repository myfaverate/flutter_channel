package edu.tyut.flutterintegration.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.IntentCompat
import edu.tyut.flutterintegration.ui.flutter.FlutterContainerActivity
import edu.tyut.flutterintegration.ui.theme.FlutterIntegrationTheme
import edu.tyut.flutterintegration.ui.theme.Purple80
import io.flutter.embedding.android.FlutterActivity

private const val TAG: String = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlutterIntegrationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context: Context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "启动Flutter页面", modifier = Modifier.background(color = Purple80, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .clickable{
                    // context.startActivity(FlutterActivity.createDefaultIntent(context))
                    context.startActivity(Intent(context, FlutterContainerActivity::class.java))
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    FlutterIntegrationTheme {
        Greeting("Android")
    }
}