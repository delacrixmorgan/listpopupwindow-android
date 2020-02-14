package com.delacrixmorgan.dialogbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = listOf(
        "Profile",
        "Settings",
        "Notification",
        "Logout"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloButton.setOnClickListener {
            showPopUp()
        }
    }

    private fun showPopUp() {
        val listPopupWindow = UIUtils.getPlainListPopUpWindow(
            context = this,
            items = list,
            anchor = helloButton,
            cellLayoutRes = R.layout.layout_filter_option,
            backgroundDrawableRes = R.drawable.bg_tooltip
        )

        listPopupWindow.setOnItemClickListener { _, _, index, _ ->
            listPopupWindow.dismiss()
        }

        listPopupWindow.show()
    }
}