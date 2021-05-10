package com.customcolapsingtoolbar

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class ImageActivity : AppCompatActivity() {

    var img1: ImageView?=null
    var img2: ImageView?=null
    var btn: Button?=null
    var parent: RelativeLayout?=null
    var isKeyBoardShow = false
    private var progressStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val et1: EditText = findViewById(R.id.et1)
        val et2: EditText = findViewById(R.id.et2)
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        btn = findViewById(R.id.btn)
        parent = findViewById(R.id.parent)

        et1.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            showImg2()
        }

        et2.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            showImg2()
        }

        btn?.setOnClickListener {
            showImg1()
        }

        parent?.setOnClickListener {
            showImg1()
        }

    }

    override fun onBackPressed() {
        if (isKeyBoardShow) {
            showImg1()
        } else {
            super.onBackPressed()
        }
    }

    fun showImg1() {
        hideSoftKeyboard(this, btn!!)
        img1?.animate()?.alpha(1f)?.duration = 1000
        img2?.animate()?.alpha(0f)?.duration = 1000
    }

    fun showImg2() {
        hideSoftKeyboard(this, btn!!)
        img2?.animate()?.alpha(1f)?.duration = 1000
        img1?.animate()?.alpha(0f)?.duration = 1000
    }

    fun hideSoftKeyboard(activity: Activity, view: View) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
        isKeyBoardShow = false
    }

}