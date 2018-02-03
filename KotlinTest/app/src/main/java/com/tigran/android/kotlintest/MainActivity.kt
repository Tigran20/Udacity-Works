package com.tigran.android.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private var mTextTV: TextView? = null
    private var mChangeTextKotBtn: Button? = null
    private var mChangeTextAlexBtn: Button? = null
    private var mChangeTextColor: Button? = null
    private var mDefaultTextColor: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        mTextTV = findViewById(R.id.hello_from_alex)
        mChangeTextKotBtn = findViewById(R.id.change_text_kotlin_button)
        mChangeTextAlexBtn = findViewById(R.id.change_text_alex_button)
        mChangeTextColor = findViewById(R.id.change_text_color)
        mDefaultTextColor = findViewById(R.id.default_text_color)

        mChangeTextKotBtn?.setOnClickListener {
            mTextTV?.text = getString(R.string.hello_from_kotlin)
        }

        mChangeTextAlexBtn?.setOnClickListener {
            mTextTV?.text = getString(R.string.hello_from_alex)
        }

        mChangeTextColor?.setOnClickListener {
            mTextTV?.setTextColor(resources.getColor(R.color.color_text_veiw))
        }

        mDefaultTextColor?.setOnClickListener {
            mTextTV?.setTextColor(resources.getColor(R.color.default_color_text))
        }
    }

}
