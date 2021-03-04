package com.customcolapsingtoolbar

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView

class ScrollActivity : AppCompatActivity(), View.OnTouchListener,
    ViewTreeObserver.OnScrollChangedListener {

    var scrollView: NestedScrollView? = null
    var img1: ImageView? = null
    var img2: ImageView? = null

    var bottom = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        scrollView = findViewById(R.id.scroll)
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        scrollView!!.setOnTouchListener(this)
        scrollView!!.viewTreeObserver.addOnScrollChangedListener(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return false
    }

    override fun onScrollChanged() {
        val view = scrollView!!.getChildAt(scrollView!!.childCount - 1)
        val topDetector = scrollView!!.scrollY
        val bottomDetector = view.bottom - (scrollView!!.height + scrollView!!.scrollY)
        if (bottomDetector == 0) {
            if (!bottom) {
                Log.e("Bottom", ">>> $bottom")
                bottom = true
                val transition: Transition = Fade()
                transition.duration = 600
                transition.addTarget(img1!!)
                transition.addTarget(img2!!)
                TransitionManager.beginDelayedTransition(scrollView!!, transition)
                img1!!.visibility = View.GONE
                img2!!.visibility = View.VISIBLE
                Toast.makeText(baseContext, "Scroll View bottom reached", Toast.LENGTH_SHORT).show()
            }
        }
        if (topDetector <= 0) {
            if (bottom) {
                Log.e("Bottom", ">>> 11 $bottom")
                bottom = false
                val transition: Transition = Fade()
                transition.duration = 600
                transition.addTarget(img1!!)
                transition.addTarget(img2!!)
                TransitionManager.beginDelayedTransition(scrollView!!, transition)
                img1!!.visibility = View.VISIBLE
                img2!!.visibility = View.GONE
                Toast.makeText(baseContext, "Scroll View top reached", Toast.LENGTH_SHORT).show()
            }
        }
    }

}