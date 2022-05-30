package com.example.mybright

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mybright.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var Bright: Int? = null
    var screenMode: Int? = null
    val REQUEST_CODE_WRITE_SETTINGS = 101
    private val TAG: String = "ScreenLuminance"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == RESULT_OK){
                val mString = result.data?.getStringExtra("ㅁㄴㅇ")
            }

        }

//        binding.seekBar.setOnSeekBarChangeListener()
    }

    fun permissionCheck() : Boolean {
        var permission: Boolean?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission = Settings.System.canWrite(applicationContext)
        } else {
            permission = ContextCompat.checkSelfPermission(applicationContext,
                Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED
        }

        if (!permission) {
            Toast.makeText(applicationContext, "시스템 설정을 변경하기 위해 권한이 필요합니다.", Toast.LENGTH_SHORT)
                .show()
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.setData((Uri.parse("package:"+packageName)))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                val ss = arrayOf(Manifest.permission.WRITE_SETTINGS)
                ActivityCompat.requestPermissions(this, ss,
                    REQUEST_CODE_WRITE_SETTINGS)
            }
        }
        return permission
    }
}



