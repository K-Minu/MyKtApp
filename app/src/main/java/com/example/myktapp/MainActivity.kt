package com.example.myktapp

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.preference.*
import com.example.myktapp.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        val name = TextView(this).apply{
            typeface = Typeface.DEFAULT_BOLD
            text = "Kang Minu"
        }

        val kkk = TextView(this).apply {
            text = "aaaaaa"
            textSize = 30F
        }

        val image = ImageView(this).also {
            it.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background))
        }

        val address = TextView(this).apply {
            text = "oh... jinun"
        }

        val layout = LinearLayout(this).apply{
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER

            addView(name, WRAP_CONTENT, WRAP_CONTENT)
            addView(kkk, WRAP_CONTENT, WRAP_CONTENT)
            addView(image, WRAP_CONTENT, WRAP_CONTENT)
            addView(address, WRAP_CONTENT, WRAP_CONTENT)
        }

        setContentView(R.xml.fff)
        binding.hello.visibility = View.INVISIBLE



        /*

        // readableDatabase or writableDatabase 로 데이터베이스 객체 생성
        val db: SQLiteDatabase = DBHelper(this).writableDatabase

        // 파일을 내장 메모리에 저장하려면 File 클래스 이용
        val file = File(filesDir, "test.txt")
        val writeStream: OutputStreamWriter = file.writer()
        writeStream.write("Hello www")
        writeStream.flush()

        // File 데이터 읽기
        val readStream: BufferedReader = file.reader().buffered()
        readStream.forEachLine {
            Log.d("kkmmuu", "$it")
        }

        // File 말고 Context 객체가 제공하는 openFileOutput과 Input 함수를 사용해 파일에 데이터 읽고 쓰기 가능
        openFileOutput("test.txt", Context.MODE_PRIVATE).use {
            it.write("hello world!!".toByteArray())
        }
        openFileInput("test.txt").bufferedReader().forEachLine {
            Log.d("kkmmuu","$it")
        }

        // 외장 메모리 사용가능여부 판단
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
            Log.d("kkmmuu", "ExternalStorageState Mount")
        } else {
            Log.d("kkmmuu", "ExternalStorageState UnMount")
        }
        // 파일을 이용하는 방식이 버전에 따라 다르기 때문에 API호환성을 생각해 외장메모리 사용시 Mainfest에 퍼미션 설정(ExternalStorage)
        // 외장메모리 공간은 앱별저장소와 공용저장소로 구분, 앱별 저장소는 개별 앱에 할당된 공간으로 기본적으로 해당 앱에서만 접근 가능
        // 만약 앱별 저장소의 파일을 외부 앱에서 접근하게 하려면 파일 프로바이더로 공개

        val files: File? = getExternalFilesDir(null)
        Log.d("kkmmuu", "${file?.absoluteFile}")
        // getExternalFilesDir(null) 함수가 반환하는 위치는 /storage/emulated/0/Android/data/패키지명/files 이다.
        // null 말고도 Environment.DIRECTORY_PICTURES를 전달 했다면 ../files/Pictures 이다.

        // 앱별 저장소에 파일 쓰기 읽기
        val filee: File = File(getExternalFilesDir(null), "test.txt")
        val writeStreamm: OutputStreamWriter = file.writer()
        writeStreamm.write("heeee")
        writeStreamm.flush()

        // 파일 읽기
        val readStreamm: BufferedReader = file.reader().buffered()
        readStreamm.forEachLine {
            Log.d("kkmmuu","$it")
        }

        // 외장 메모리의 앱별 저장소 파일을 다른 앱에서 접근
        // 파일 생성
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val fileee = File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
//        filePath = fileee.absolutePath
        // 파일 Uri 획득
        val photoURI: Uri = FileProvider.getUriForFile(
            this,
            "com.example.myKtapp.fileprovider", fileee
        )

        // 카메라 앱 실행
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//        requestCameraFileLauncher.launch(intent)

        // IllegalArgumentException 이 발생하면 파일을 공유하는데 필요한 정보가 없다는 의미
        // 공유할 파일 정보를 파일 프로바이더가 알게 해줘야 하기 때문에 res/xml 디렉터리에 xml파일을 만들고 이 파일을
        // 외부 앱에 공개할 경로로 지정해야함


        // 공용 저장소 이용
        // 접근
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )
        // MediaStore.Images는 안드로이드폰의 이미지 파일이 저장되는 DCIM, Pictures 디렉토리를 가리킴
        // MediaStore.Video는 DCIM, Movies, Pictures
        // MediaStore.Audio는 Alarms, Audibooks, Music, Notifications, Podcasts, Ringtones
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        cursor?.let {
            while (cursor.moveToNext()) {
                Log.d("kkmmuu", "_id : ${cursor.getLong(0)}, name : ${cursor.getString(1)}")
            }
        }

        // 이미지 파일의 Uri값 가져오기
        val contentUri: Uri = ContentUris.withAppendedId(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            cursor!!.getLong(0)
        )

        // 이미지 데이터 가져오기
        // resolver.openInputStream(uri)를 이용해 매개변수에 지정한 uri값이 가리키는 파일을 읽을 수 있는
        // Stream객체를 얻고 이 객체로 이미지 데이터를 가져옴
        val resolver = applicationContext.contentResolver
        resolver.openInputStream(contentUri).use { stream ->
            // stream 객체에서 작업 수행
            val option = BitmapFactory.Options()
            option.inSampleSize = 10
            val bitmap = BitmapFactory.decodeStream(stream, null, option)
//            binding.resultImageView.setImageBitmap(bitmap)
        }

        // 외장 메모리의 앱별 저장소나 공용저장소 말고 임의의 폴더를 만들고 파일 저장법
        val fileother = File(Environment.getExternalStorageDirectory().absolutePath)
        // 위 함수는 안드로이드 10에서 deprecated됌 Manifest파일의 <application> 태그에 requestLegacyExternalStorage 속성을
        // true로 지정하면 10버전 이후에도 접근할 수 있었으나 11버전부터는 안됌
        // 11 이후로는 MANAGE_EXTERNAL_STORAGE 퍼미션을 선언해야 하고 ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION을
        // 액션 문자열로 해서 모든  파일에 접근할 수 있는 권한을 사용자로부터 얻어야함.

        // 공유된 프리퍼런스에 보관하기
        // 액티비티의 데이터 저장, ex) MainActivity 클래스에서 SharedPreferences객체를 얻었다면 MainActivity.xml파일이 생기고 저장됌
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        // 앱 전체의 데이터를 키-값 형태로 저장하려고 SharedPreferences객체를 얻을 때는 Context.getSharedPreferences() 함수를 이용
        // 이 함수 첫번째 매개변수에 지정한 값이 파일 이름
        val sharedPref1 = getSharedPreferences("my_prefs",Context.MODE_PRIVATE)

        // 프리퍼런스에 데이터 저장
        sharedPref.edit().run {
            putString("data1", "hee")
            putInt("data2", 2)
            commit() // 호출시 저장
        }

        // 프리퍼런스에서 데이터 가져오기
        val data1 = sharedPref.getString("data1", "woo")
        val data2 = sharedPref.getInt("data2", 2)

         */
    }
}

// PreferenceFragmentCompat을 상속받은 프래그먼트로 설정화면을 준비
class MySettingFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefer, rootKey)

        // 설정값 자동 적용
        val idPreference: EditTextPreference? = findPreference("id")
        val colorPreference: ListPreference? = findPreference("color")

        idPreference?.summaryProvider =
            EditTextPreference.SimpleSummaryProvider.getInstance()
        colorPreference?.summaryProvider =
            ListPreference.SimpleSummaryProvider.getInstance()

        // 코드에서 설정값 표시하기
        idPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference> { preference ->
                val text = preference.text
                if (TextUtils.isEmpty(text)){
                    "설정이 되지 않음"
                } else {
                    "설정된 ID 값은 : $text 입니다."
                }
            }

        // 설정 항목에 이벤트 추가도 가능
        idPreference?.setOnPreferenceClickListener { preference ->
            Log.d("kkmmuu", "preference key : ${preference.key}")
            true
        }

        // 설정값 가져오기
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val id = sharedPreferences.getString("id", "")

        // 프리퍼런스를 이용한 이벤트 처리
        idPreference?.setOnPreferenceChangeListener{ preference, newValue ->
            Log.d("kkmmuu", "preference key : ${preference.key}, newValue : $newValue")
            true
        }
    }
}

class AFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefer_one, rootKey)
    }
}

class BFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefer_two, rootKey)
    }
}

//class SettingActivity : AppCompatActivity(),
//        PreferenceFragmentCompat.OnPreferenceStartFragmentCallback{
//    override fun onPreferenceStartFragment(
//        caller: PreferenceFragmentCompat,
//        pref: Preference
//    ): Boolean {
//        // 새로운 프래그먼트 인스턴스화
//        val args = pref.extras
//        val fragment = supportFragmentManager.fragmentFactory.instantiate(
//            classLoader,
//            pref.fragment!!)
//        fragment.arguments = args
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.setting_content, fragment)
//            .addToBackStack(null)
//            .commit()
//        return true
//    }
//        }

class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

fun printShippingLabel(person: Person) {
    val address = person.company?.address ?: throw IllegalArgumentException("No address")

    with(address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

fun main(args: Array<String>) {
    val address = Address("Elsestr. 47", 88432, "Munich", "Germany")
    val jetbrains = Company("JetBrains", address)
    val person = Person("Dmitry", jetbrains)
    printShippingLabel(person)
    /*
    * output:
    * Elsestr. 47
    * 88432 Munich, Germany
    * */
}


class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1){
    // 아래 두 함수는 추상클래스이므로 반드시 재정의
    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}