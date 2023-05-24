package com.example.iiot_sdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.iiot_sdk.ui.theme.IIOT_SDKTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.SDK.Test.ANDConnect
import ru.SDK.Test.BluetoothStatusCode
import ru.SDK.Test.DeviceCallBack
import ru.SDK.Test.DeviceService
import ru.SDK.Test.PlatformStatusCode
import kotlin.random.Random

const val TAG = "check___"

@SuppressLint("MissingPermission")
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DeviceService.init(application, "RtDoctis-dev", "FPrUadrNNxrs", object : DeviceCallBack {
            override fun onExploreDevice(p0: String?, p1: String?, p2: Any?) {
                Log.d(TAG, "onExploreDevice: p0 - $p0 p1 - $p1 p2 - $p2 ")
            }

            override fun onStatusDevice(p0: String?, p1: BluetoothStatusCode?) {
                Log.d(TAG, "onStatusDevice: p0 - $p0 p1 - $p1")
            }

            override fun onSendData(p0: String?, p1: PlatformStatusCode?) {
                Log.d(TAG, "onSendData: p0 - $p0  p1 - $p1")
            }

            override fun onDisconnect(p0: String?, p1: HashMap<String, Any>?) {
                Log.d(TAG, "onDisconnect: p0 - $p0 p1 - $p1")
            }

            override fun onException(p0: String?, p1: Exception?) {
                Log.d(TAG, "onException: p0 - $p0 p1 - $p1")
            }

        })

        lifecycleScope.launch {
            repeat(10) {
                delay(1111)
                ANDConnect.sendData(
                    "serial",
                    "display",
                    Random.nextInt(300).toString(),
                    Random.nextInt(300).toString(),
                    Random.nextInt(300).toString()
                )
            }
        }

        //DeviceService.connect(ANDConnect::class.java, "B8:80:4F:58:5D:CD")


        setContent {
            IIOT_SDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }


    }
}

