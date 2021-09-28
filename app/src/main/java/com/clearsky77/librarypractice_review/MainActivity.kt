package com.clearsky77.librarypractice_review

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
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
//            권한 획득 여부에 따른 수칙 정리
            val pl = object : PermissionListener {
                //권한 허용 시 -> 전화걸기
                override fun onPermissionGranted() {
                    val myUri = Uri.parse("tel:${telNumTxt.text.toString()}")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                //권한 거절 시 -> Toast 띄우기
                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(
                        this@MainActivity,
                        "전화 연결 불가. 전화 연결 권한이 없습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

//  실제 Permission 확인
            TedPermission.create()
                .setPermissionListener(pl) //pl을 판단해줘!
                .setPermissions(Manifest.permission.CALL_PHONE) //어떤 권한을 확인할 것인가? 필히 안드로이드로 임포트!
                .check()
        }
    }
}