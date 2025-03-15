package com.example.sharedpref

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBindings
import com.example.sharedpref.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        binding.saveBtn.setOnClickListener{
           val name =  binding.getName.text.toString()
            val age = binding.getAge.text.toString().toInt()

         editor.apply {
                editor.putString("name",name,)
                editor.putInt("age",age)
                apply()
            }
        }

        binding.loadBtn.setOnClickListener {
            binding.getName.setText(sharedPref.getString("name",""))
            binding.getAge.setText(sharedPref.getInt("age",0).toString())
        }

    }
}