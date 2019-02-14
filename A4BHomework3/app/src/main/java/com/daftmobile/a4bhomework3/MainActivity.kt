package com.daftmobile.a4bhomework3

import android.app.Activity
import android.content.Intent
import android.content.UriMatcher
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EMAIL_RETRIEVER = EmailRetriever.Impl(applicationContext)

        sendMailButton.setOnClickListener(this::findContact)
    }

    companion object {
        lateinit var EMAIL_RETRIEVER: EmailRetriever
        val PICK_CONTACT: Int = 101
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_CONTACT && resultCode == Activity.RESULT_OK) {

            //val contactUri = data!!.toUri(0) //jest roznica ktorego rozwiazania z tych dwoch uzyje?
            val contactUri = data!!.data
            println("uri: $contactUri")

            //val email = EMAIL_RETRIEVER.retrieve(Uri.parse(contactUri))
            val email = EMAIL_RETRIEVER.retrieve(contactUri)
            if (email != null) {
                println("pobrano mail: $email")
                sendMail(email)
            }

        }

    }

    private fun findContact(view: View) {
        val intent = Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"))
        intent.type = ContactsContract.CommonDataKinds.Email.CONTENT_TYPE

        val validActivities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        if (validActivities.isNotEmpty())
            startActivityForResult(intent, PICK_CONTACT)
    }

    private fun sendMail(email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
                .putExtra(Intent.EXTRA_SUBJECT, "Wiadomość z pracy domowej")
                .putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        emailIntent.data = Uri.parse("mailto:$email")


        val validActivities = packageManager.queryIntentActivities(emailIntent, PackageManager.MATCH_DEFAULT_ONLY)
        if (validActivities.isNotEmpty()) {
            startActivity(emailIntent)
        }
    }
}