package com.daftmobile.a4bhomework2

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRedButton.setOnClickListener(this::dialogShow)
        myGreenButton.setOnClickListener(this::dialogShow)
        myBlueButton.setOnClickListener(this::dialogShow)

    }

    private fun dialogShow(view: View){
        val builder = AlertDialog.Builder(this)
        val string = getString(R.string.dialog_color, (view as Button).text)

        builder.setMessage(string)

        builder.setPositiveButton("OK"){dialog, which ->  dialog.dismiss()}

        val dialog: AlertDialog = builder.create();
        dialog.show()
    }
}
