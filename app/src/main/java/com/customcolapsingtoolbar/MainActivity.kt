package com.customcolapsingtoolbar

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAppBarLayout: AppBarLayout = findViewById(R.id.app_bar)
        val img: ImageView = findViewById(R.id.ivImg)
        mAppBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true
                    img.visibility = View.VISIBLE
//                    showOption(R.id.action_info)
                } else if (isShow) {
                    isShow = false
                    img.visibility = View.GONE
//                    hideOption(R.id.action_info)
                }
            }
        })

    }
}