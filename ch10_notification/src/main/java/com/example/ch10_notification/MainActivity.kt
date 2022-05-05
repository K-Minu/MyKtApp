package com.example.ch10_notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch10_notification.databinding.ActivityMainBinding
import com.example.ch10_notification.databinding.RecyclerViewBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // 액티비티 코드에서 업 버튼 생성, 뒤로가기 버튼
        // 매니페스트 파일에서 업버튼 생성도 가능
        // <activity
        //     android:name=".SubActivity"
        //     android:parentActivityName=".MainActivity"></activity>
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentView(binding.root)



        // FragmentTrasaction 객체의 add() 함수를 이용해 프래그먼트 객체를 화면에 출력하는 코드
        // add()함수의 첫번째 매개변수가 프래그먼트가 출력될 뷰의 id값
        // 프래그먼트의 생명주는 5단계로 구분, 초기화, 생성, 시작, 재개, 소멸
        val fragmentManger : FragmentManager = supportFragmentManager
        val transaction : FragmentTransaction = fragmentManger.beginTransaction()
        val fragment = FirstFrag()
        transaction.add(R.id.fragment_content, fragment)
        // 백스택을 사용하면 다른 프래그먼트로 넘어갔을때 프래그먼트가 제거 되지 않고 onDestoryView함수까지만 호출됌.
        // 즉 onPause - onStop - onDestoryView함수가 자동으로 호출됌
        // 다시 돌아오면 onCreateView - onViewCreated - onStart - onResume
        transaction.addToBackStack(null)
        transaction.commit()



        // 툴바, 액션바 보다 다양한 기능을 제공할 수 있습니다. 개발자가 제어 하기 때문에..
//        setSupportActionBar(binding.toolbar)

// ===================================  카톡 알림 만들기 ~ ==============================================
        /*
        binding.notificationButton.setOnClickListener{
            val manager = getSystemService(NOTIFICATION_SERVICE)
                    as NotificationManager
            val builder: NotificationCompat.Builder
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                // 26 이상
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    // 채널에 다양한 정보 설정
                    description = "My Channel One Description"
                    setShowBadge(false)
                    val uri: Uri = RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val audioAttributes = AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build()
                    setSound(uri, audioAttributes)
                    enableVibration(true)
                }
                // 채널을 NotificationManager에 등록
                manager.createNotificationChannel(channel)
                // 채널을 이용해 builder 생성
                builder = NotificationCompat.Builder(this, channelId)
            } else {
                // 26 버전 이하
                builder = NotificationCompat.Builder(this)
            }
            builder.run {
                // 알림의 기본정보
                setSmallIcon(R.drawable.small)
                setWhen(System.currentTimeMillis())
                setContentTitle("강민우")
                setContentText("암냠이 머하누")
                setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.big))
            }
            val KEY_TEXT_REPLY = "key_text_reply"
            val replyLabel: String = "답장"
            val remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }
            val replyIntent = Intent(this, ReplyReceiver::class.java)
            val replayPendingIntent = PendingIntent.getBroadcast(
                this, 30, replyIntent, PendingIntent.FLAG_MUTABLE)
            builder.addAction(
                NotificationCompat.Action.Builder(
                    R.drawable.send,
                    "답장",
                    replayPendingIntent
                ).addRemoteInput(remoteInput).build()
            )
            manager.notify(11, builder.build())
        }

         */
// ===================================  카톡 알림 만들기 ~ ==============================================

//        println("kkmmuu"+doble(3))

    }



    class RecyclerViewActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = RecyclerViewBinding.inflate(layoutInflater)
            setContentView(binding.root)
            val datas = mutableListOf<String>()
            for(i in 1..10){
                datas.add("Item $i")
            }
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = MyAdapter(datas)
            binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        }
    }

    // 전달받은 값의 두배를 반환하는 함수를 단일 표현식으로 작성
    fun doble(x: Int): Int = x *2

//===================== 액션바 시작 ===================================================================

    /*
    // 액션바 오버플로 버튼 메뉴 구성 함수
    // 두번째 매개변수는 메뉴의 식별자, 네번째는 문자열
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Activity 코드에서 작성
//        val menuItem1: MenuItem? = menu?.add(0, 0, 0, "menu1")
//        val menuItem2: MenuItem? = menu?.add(0, 1, 0, "menu2")
//        val menuItem3: MenuItem? = menu?.add(0, 2, 0, "menu3")

        // xml 파일로 작성 보통 xml 에서 함 동적으로 구성하기 때문에..
        /*
           MenuItem 객체는 findItem() 함수의 매개변수에 MenuItem의 식별값을 주어 얻습니다.
           MenuItem에 등록된 액션 뷰는 actionView 속성으로 얻습니다. 그리고 검색과 관련된 이벤트를 처리할때는
           searchView의 setOnQueryTextListener()함수로 이벤트 핸들러를 지정합니다.
         */
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색어 변경 이벤트
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드의 검색 버튼을 클릭한 순간의 이벤트
                Log.d("kkmmuuu","$query")
                return true
            }
        })
        return true
    }

    // 업 버튼 클릭 시 자동으로 호출되는 함수 재정의
    override fun onSupportNavigateUp(): Boolean {
        Log.d("kkmmuu", "onSupportNavigateUp")
        // 매니페스트 파일의 parentActivityName 속성이 설정되어 있지 않으면 자동으로 이전화면으로 돌아가지 않음
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        0-> {
            Log.d("kkmmuu", "menu1 click")
            true
        }
        1-> {
            Log.d("kkmmuu", "menu2 click")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

     */

//===================== 액션바 끝 =====================================================================

//===================== 액션 뷰 시작 ==================================================================

}