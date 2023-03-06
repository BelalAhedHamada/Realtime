package com.example.realtimefb

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = Firebase.database
        val myRef = database.getReference()
        var id :Int= 0


        btn_add.setOnClickListener {


            val name = fb_name.text.toString()
            val  nummber =  fb_number.text.toString()
            val address = fb_address.text.toString()

            val person = hashMapOf(
                "name" to name,
                "nummber" to nummber,
                "address" to address
            )
            myRef.child("Person").child(id.toString()).setValue(person)
            id++
        }

        btn_data.setOnClickListener {

            // Read from the database
            myRef.addValueEventListener(
                object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = snapshot.getValue()
                    txt_data.text = value.toString()
                    Toast.makeText(this@MainActivity, "${value.toString()}", Toast.LENGTH_SHORT).show()

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity, "Failed to read value", Toast.LENGTH_SHORT).show()
                }

            })
        }



    }
}