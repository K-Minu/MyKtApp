package com.example.ch6_view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.ch6_view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var initTime = 0L
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 알림 빌더
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder : NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "MY first channel"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )

            // 채널에 정보 설정
            channel.description = "My first channel Description"
            channel.setShowBadge(true)
            val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            channel.setSound(uri, audioAttributes)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100,200,100,200)

            // 채널을 NotificationManger에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용해 빌더 생성
            builder = NotificationCompat.Builder(this, channelId)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        // 알림 빌더를 만들었으면 이 빌더를 사용해 Notification 객체를 만들어야함
        // 알림 객체 설정 (빌더의 세터 함수를 이용해 알림의 구성정보 설정)
        builder.setSmallIcon(R.drawable.appicon)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("암냠이")
        builder.setContentText("빵꾸 똥꾸!!")

// ===================================================================================================================

        /*

        // 알림 취소, 사용자가 알림을 터치하면 이벤트가 발생할 수 있으며 이때 알림은 화면에서 자동으로 사라짐(취소)
        manager.cancel(11)
        // 알림 취소 막기
        builder.setAutoCancel(true) // 이벤트는 발생하지만 알림이 사라지지는 않음 (default false )
        builder.setOngoing(true)    // 알림을 스와이프해도 사라지지 않음 ( default false )

         */

// ===================================================================================================================

        // 알림 구성, 알림 터치 이벤트
        // 알림은 앱이 관리 하는게 아니라 시스템에서 관리하는 상태 바 출력 정보 이기때문에 알림 터치시
        // 실행 정보를 Notification 객체에 담아, 실제 이벤트가 발생하면 객체에 등록된 이벤트를 시스템이 실행하는 구조
        // 사용자가 알림을 터치하면 앱의 액티비티 또는 브로드캐스트 리시버를 실행해야하는데 이를 실행하려면 인텐트를 사용
        val intent = Intent(this, TestJava::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 10, intent,
            PendingIntent.FLAG_IMMUTABLE)
        builder.setContentIntent(pendingIntent) // 터치 이벤트 등록

// ===================================================================================================================

        /*

        // 액션 등록하기
        val actionIntent = Intent(this, TestJava::class.java)
        val actionPendingIntent = PendingIntent.getBroadcast(this, 20, actionIntent,
            PendingIntent.FLAG_IMMUTABLE)
        builder.addAction(
            NotificationCompat.Action.Builder(
                android.R.drawable.stat_notify_more,
                "Action",
                actionPendingIntent
            ).build()
        )

         */

// ===================================================================================================================

        /*

        // 원격 입력
        val KEY_TEXT_REPLY = "key_text_reply"
        var replyLabel = "답장"
        var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
            setLabel(replyLabel)
            build()
        }

        // 인텐트 준비
        val replyIntent = Intent(this, ReplyReceiver::class.java)
        val replyPendingIntent = PendingIntent.getBroadcast(
            this, 30, replyIntent, PendingIntent.FLAG_MUTABLE)

        // 원격 입력 액션 등록
        builder.addAction(
            NotificationCompat.Action.Builder(
                android.R.drawable.ic_menu_send,
                "답장",
                replyPendingIntent
            ).addRemoteInput(remoteInput).build()
        )

        // 브로드캐스트 리시버로부터 사용자가 입력한 글 받아오기 getCharSequence() 함수의 매개변수 문자열이 RemoteInput 지정 식별값과 같아야 함
        val replyText = RemoteInput.getResultsFromIntent(intent)?.getCharSequence(KEY_TEXT_REPLY).toString()

        if(!replyText.isEmpty()){
            Log.d("kkkmmmuuu",replyText)
            manager.notify(11, builder.build())
        }
        // 알림 발생, NotificationManger 클래스의 notify() 함수를 이용해 알림의 띄움
        manager.notify(11, builder.build())

         */

// ===================================================================================================================

        /*

        // 프로그레스, 파일 다운로드등을 할 때 진행상황 표시를 위해..
        // 스레드를 이용해 10초 동안 프로그레스 바의 진행값을 증가시키는 코드 입니다.
        builder.setProgress(100, 0, false)
        manager.notify(11, builder.build())

        thread {
            for (i in 1..100){
                builder.setProgress(100, i, false)
                manager.notify(11, builder.build())
                SystemClock.sleep(100)
            }
        }

         */

// ===================================================================================================================

        // 알림스타일
        // 큰 이미지 스타일, 폰에서 스샷찍으면 뜨는거...
//        val bigPicture = BitmapFactory.decodeResource(resources, R.drawable.appicon)
//        val bigStyle = NotificationCompat.BigPictureStyle()
//        bigStyle.bigPicture(bigPicture)
//        builder.setStyle(bigStyle)


        // 긴 텍스트 스타일, 이메일 앱..
//        val bigTextStyle = NotificationCompat.BigTextStyle()
//        bigTextStyle.bigText(resources.getString(R.string.main_desc))
//        builder.setStyle(bigTextStyle)

        // 상자 스타일, 문자열을 목록으로 출력하는 InboxStyle
//        val boxStyle = NotificationCompat.InboxStyle()
//        boxStyle.addLine("1. 기")
//        boxStyle.addLine("2. 모")
//        boxStyle.addLine("3. 치")
//        builder.setStyle(boxStyle)

        // 메시지 스타일, 3가지 정보로 표현됌
        // 첫번째 매개변수 : 메시지 내용, 두번째 : 시각, 세번째 : 보낸사람
        val sender1: Person = Person.Builder()
            .setName("kang")
            .setIcon(IconCompat.createWithResource(this, R.drawable.appicon))
            .build()

        val sender2: Person = Person.Builder()
            .setName("oh..")
            .setIcon(IconCompat.createWithResource(this, R.drawable.appicon))
            .build()

        val message1 = NotificationCompat.MessagingStyle.Message(
            "heloooo",
            System.currentTimeMillis(),
            sender1
        )

        val message2 = NotificationCompat.MessagingStyle.Message(
            "good bye",
            System.currentTimeMillis(),
            sender2
        )

        // Message 객체를 MessageStyle 에 대입
        val messageStyle = NotificationCompat.MessagingStyle(sender1)
            .addMessage(message1)
            .addMessage(message2)
        builder.setStyle(messageStyle)


        manager.notify(11, builder.build())


// ===================================================================================================================


//        val vibrator = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
//            val vibratorManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE)
//                                    as VibratorManager
//            vibratorManager.defaultVibrator
//        } else {
//            getSystemService(VIBRATOR_MANAGER_SERVICE) as Vibrator
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            vibrator.vibrate(
//                VibrationEffect.createWaveform(longArrayOf(500,1000,500,1500),
//                intArrayOf(0, 50, 0, 100), -1))
//        } else {
//            vibrator.vibrate(longArrayOf(500,1000,500,1500), -1)
//        }

//        val player : MediaPlayer = MediaPlayer.create(this, R.raw.impact)
//        player.start()

//        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        val ringtone = RingtoneManager.getRingtone(applicationContext, notification)
//        ringtone.play()

//        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val customDialog = inflater.inflate(R.layout.custom_dialog, null)

//        val customDialogBinding = CustomDialogBinding.inflate(layoutInflater)
//
//        AlertDialog.Builder(this).run {
//            setView(customDialogBinding.root)
//            setPositiveButton("닫기", null)
//            show()
//        }

//        val items = arrayOf<String>("헬스", "코딩", "볼링", "배드민턴")

//        AlertDialog.Builder(this).run {
//            setTitle("당신의 취미는?")
//            setIcon(android.R.drawable.ic_dialog_info)
//            setMultiChoiceItems(items, booleanArrayOf(false, false, false, false)
//            ) { dialog, which, isChecked ->
//                Log.d("minu", "${items[which]} ${if (isChecked) "선택 하셨습니다." else "선택 해제"}")
//            }
//            setCancelable(false)
//            setPositiveButton("닫기", null)
//            show()
//        }.setCanceledOnTouchOutside(false)


//        val btnEventHandler = object : DialogInterface.OnClickListener{
//            override fun onClick(dialog: DialogInterface?, which: Int) {
//                when(which){
//                    DialogInterface.BUTTON_POSITIVE ->
//                        Log.d("minu", "positive btn click")
//                    DialogInterface.BUTTON_NEGATIVE ->
//                        Log.d("minu", "negative btn click")
//                    DialogInterface.BUTTON_NEUTRAL ->
//                        Log.d("minu", "neutral btn click")
//                }
//            }
//        }
//
//        AlertDialog.Builder(this).run {
//            setTitle("제목")
//            setIcon(android.R.drawable.ic_dialog_alert)
//            setMessage("내용")
//            setPositiveButton("버튼1", btnEventHandler)
//            setNegativeButton("버튼2", btnEventHandler)
//            setNeutralButton("버튼3", btnEventHandler)
//            show()
//        }

//        DatePickerDialog(this,
//            { view, year, month, dayOfMonth ->
//                Log.d("minu", "year : $year, month : ${month+1}, dayOfMonth : $dayOfMonth")
//            }, 2022, 4, 2).show()

//        TimePickerDialog(this, object: TimePickerDialog.OnTimeSetListener{
//            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//                Log.d("minu", "time : $hourOfDay, minute : $minute")
//            }
//        }, 3, 7, true).show()

//        TimePickerDialog(this,
//            { view, hourOfDay, minute ->
//                Log.d("minu", "time : $hourOfDay, minute : $minute")
//            }, 3, 7, true).show()


//        binding.textview.textSize = resources.getDimension(R.dimen.txt_size)
//        binding.textview.setTextColor(ResourcesCompat.getColor(resources, R.color.teal_200, null))
//
//        // API Level 에 따른 화면정보 가져오기
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){ // <- API 30
//            val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
//            binding.textview.text = "Width : ${windowMetrics.bounds.width()}," +
//                                    "height : ${windowMetrics.bounds.height()}"
//        } else{
//            val display = windowManager.defaultDisplay
//            val displayMetrics = DisplayMetrics()
//            display?.getRealMetrics(displayMetrics)
//            binding.textview.text = "Width : ${displayMetrics.widthPixels}," +
//                                    "height : ${displayMetrics.heightPixels}"
//        }

        val status = ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_Find_Location")

//        val toast = Toast.makeText(this, "한번더 클릭 시 앱 종료", Toast.LENGTH_SHORT)
//        toast.show()

        // API 레벨 호환성 애너테이션
        fun showToast() {
            val toast = Toast.makeText(this, "한번더 클릭 시 앱 종료", Toast.LENGTH_SHORT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                toast.addCallback(
                    object : Toast.Callback() {
                        override fun onToastHidden() {
                            super.onToastHidden()
                            Log.d("Minu", "onToastHidden")
                        }

                        override fun onToastShown() {
                            super.onToastShown()
                            Log.d("Minu", "onToastShown")
                        }
                    })
            }
            toast.show()
        }

//        showToast()

//        if (status == PackageManager.PERMISSION_GRANTED){
//            Log.d("kang", "permission Granted")
//        }else {
//            Log.d("kang", "permission Denied")
//        }

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()){
            isGranted ->
            if (isGranted){
                Log.d("kang", "callback, granted..")
            } else{
                Log.d("kang", "callback, denied..")
            }
        }

        // 퍼미션 허용 요청 실행
//        requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")



//        binding.checkbox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
//            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//                Log.d("kang", "on Checked..")
//            }
//        })
//
//        binding.checkbox.setOnCheckedChangeListener(MyEventHandler())
//
//        binding.button.setOnLongClickListener{
//            Log.d("kang", "Long Clicked")
//            true
//        }
//
//        binding.checkbox.setOnCheckedChangeListener{
//            compoundButton, b ->
//            Log.d("kang", "SAM checked..")
//        }


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action){
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return super.onTouchEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
//            KeyEvent.KEYCODE_0 -> Log.d("kang", "0 key pressed")
//            KeyEvent.KEYCODE_1 -> Log.d("kang", "1 key pressed")
//            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("kang", "VOLUME_UP key pressed")
            KeyEvent.KEYCODE_BACK -> if(System.currentTimeMillis() - initTime > 2000){
                Toast.makeText(this, "종료하려면 한번 더 누르세요!", Toast.LENGTH_LONG).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}

//class MyEventHandler : CompoundButton.OnCheckedChangeListener {
//    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//        Log.d("kang", "outer checked...")
//    }
//}