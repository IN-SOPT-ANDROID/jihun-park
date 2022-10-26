package org.sopt.sample.util.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

//확장할 클래스 - View 클래스
//View 클래스에 속한 인스턴스 객체 - this
//함수 내부에서는 binding.root 키워드로 수신 객체 멤버 사용

fun View.makeSnackBar(message:String, isShort: Boolean=true): Snackbar {
    val duration = if (isShort) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG
    return Snackbar.make(this,message,duration)
}