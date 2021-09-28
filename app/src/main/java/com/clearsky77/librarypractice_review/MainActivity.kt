package com.clearsky77.librarypractice_review

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// 사진 클릭시 사진 상세 화면으로 Intent
        profilePhotoImg.setOnClickListener {
            val myIntent = Intent(this, PhotoViewActivity::class.java)
            startActivity(myIntent)
        }
//        call 버튼 클릭시 전화
        callBtn.setOnClickListener {
            val myUri = Uri.parse("tel:${telNumTxt.toString()}")
            val myIntent = Intent(Intent.ACTION_CALL, myUri)
            startActivity(myIntent)
        }

    }
}