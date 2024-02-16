package com.example.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasedemo.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Student")

        binding.button.setOnClickListener {
            val studentId = binding.studentId.text.toString()
            val studentName = binding.studentName.text.toString()
            val studentAge = binding.studentAge.text.toString()
            val studentAddress = binding.studentAddress.text.toString()
            val studentEmail = binding.studentEmail.text.toString()

            val student = Student(studentId, studentName, studentAge, studentAddress, studentEmail)

            // Use studentId as the key in the database
            database.child(studentId).setValue(student).addOnSuccessListener {
                // Clear input fields after successful save
                binding.studentId.text.clear()
                binding.studentName.text.clear()
                binding.studentAge.text.clear()
                binding.studentAddress.text.clear()
                binding.studentEmail.text.clear()

                Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Ops... Something went wrong.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
