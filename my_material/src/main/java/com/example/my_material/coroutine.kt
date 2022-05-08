package com.example.my_material

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlin.system.measureTimeMillis

class coroutine : AppCompatActivity() {

    /* 채널 클래스는 코루틴의 값을 전달받을 수 있는 방법을 제공
     채널은 큐 알고리즘과 비슷하면 채널의 send()함수로 데이터를 전달 하면 그 데이터를 받는
     코루틴에서는 receive()나 consumeEach()등의 함수로 데이터를 받음 */
    val channel = Channel<Int>()

    /* Dispatchers.Default, 백그라운드에서 동작 ( 시간이 오래 걸리는 작업, CPU 사용 많은 )
       Dispatchers.IO, 파일에 읽거나 쓰기 또는 네트워크 작업 등에 최적화됨 */
    val backgroundScope = CoroutineScope(Dispatchers.Default + Job())

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        backgroundScope.launch {
            var sum = 0L
            var time = measureTimeMillis {
                for (i in 1..2_000_000_000){
                    sum += i
                }
            }
            Log.d("kkmmuu", "tiem : $time")
            channel.send(sum.toInt())
        }

        // Dispatchers.Main, 메인 스레드에서 동작 ( 화면에 결과 값 표시(UI 변경), 액티비티의 메인 스레드 )
        val mainScope = GlobalScope.launch(Dispatchers.Main) {
            channel.consumeEach {
//                binding.resultView.text = "sum: $it"
            }
        }

    }
}