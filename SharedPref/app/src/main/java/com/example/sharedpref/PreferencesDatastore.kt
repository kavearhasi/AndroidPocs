package com.example.sharedpref

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.sharedpref.databinding.ActivityPreferencesDatastoreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PreferencesDatastore : AppCompatActivity() {
    private lateinit var binding: ActivityPreferencesDatastoreBinding

    private val Context.preferencesDataStoree: DataStore<Preferences> by preferencesDataStore(name = "Settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPreferencesDatastoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

      // Save the key value pair on datastore using button click
        binding.saveBtn.setOnClickListener {
            val value: String = binding.getMessage.text.toString()
            if (!checkInput(value)) {
                Toast.makeText(this, "Enter the key values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                setData(value)
            }
        }

        // getting the given value
        binding.loadBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                val data = getData() ?: "No value"
                binding.receivedMessage.text = data
            }
        }

    }

    private suspend fun setData(value: String) {
        preferencesDataStoree.edit {
            it[PreferenceKeys.KEY] = value

        }
    }

    private suspend fun getData(): String? {
        val preferences = preferencesDataStoree.data
            .catch { exception ->
                exception.message?.let { Log.e("From get data", it) }
            }
            .first()
        return preferences[PreferenceKeys.KEY]
    }

    private fun checkInput(value: String): Boolean {
        return value.isNotBlank()
    }

    private object PreferenceKeys {
        val KEY = stringPreferencesKey("key")
    }
}