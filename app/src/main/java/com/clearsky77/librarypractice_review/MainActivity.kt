package com.clearsky77.librarypractice_review

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// 이미지 바로 가져오기(Library)
        val imgeURL = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTAyMDlfMjQz%2FMDAxNjEyODY0NDY0OTEy.CyEmVaDiUjBLPASsOWz-1P-dpWh2GbXrtZggxsMRpBsg.9bNocgGaBJU1mFYm7D6PrNaN_TtU5y8dpU8DlsprWB8g.JPEG.dochi20%2F%25B9%25DF%25B7%25BB%25C6%25BC%25BF%25C2.jpg"
        Glide.with(this).load(imgeURL).into(latestNewsImg)
        val imgeURL2 = "https://search.pstatic.net/common/?src=http%3A%2F%2Fpost.phinf.naver.net%2FMjAyMTA3MTVfMjU4%2FMDAxNjI2MzI2MDYzNDkx.7UFt3Yyix-OK2XKL0ARLgwoXFn9snhC-ZAEzUgbRNzQg.hn0pu8hWaxGuDPF5UygE4lRFcACiPbU16SheITAN2UQg.PNG%2FIuL2i1ZtrntAnQgbE2Ko-bQ4bhts.jpg&type=sc960_832"
        Glide.with(this).load(imgeURL2).into(latestNewsImg2)
        val imgeURL3 = "https://search.pstatic.net/common/?src=http%3A%2F%2Fpost.phinf.naver.net%2FMjAxOTA0MDJfMTg1%2FMDAxNTU0MTg3NTY0ODIy.FYknc4fFRLPjehEACpb89a3aNbcpITfKx4uyQFC77d4g.rl2vAxnHfB_gnx3ezAOK8qvuzg08F6Vtch-9p-edCYQg.JPEG%2FITG636IM3VPsOdKSmYQmVFLS3aRs.jpg"
        Glide.with(this).load(imgeURL3).into(latestNewsImg3)

// 사진 클릭시 사진 상세 화면으로 Intent
        profilePhotoImg.setOnClickListener {
            val myIntent = Intent(this, PhotoViewActivity::class.java)
            startActivity(myIntent)
        }
// call 버튼 클릭시 전화
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