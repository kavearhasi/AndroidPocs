package com.example.workmanageronetime
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.workmanageronetime.data.di.Myapp.Companion.CHANNEL_ID
import com.example.workmanageronetime.databinding.ActivityMainBinding
import com.example.workmanageronetime.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.getRate.setOnClickListener {
           binding.progress.visibility = View.VISIBLE
            mainViewModel.setWork()
        }
        observeRate()
    }

    private fun observeRate() {

        mainViewModel.currentRate.observe(this) {

            if (it != null) {

                binding.progress.visibility = View.INVISIBLE
                val (high, low, priceChange, priceChangePercent, volume, price, timeStamp) = it
                val formatedTime = getDateFormat(timeStamp)
                binding.price.text = price
                binding.time.text = formatedTime
                binding.priceChange.text = priceChange
                binding.priceChangePercent.text = priceChangePercent
                binding.todayVolume.text = volume
                binding.todayHigh.text = high
                binding.todayLow.text = low
                binding.progress.visibility = View.VISIBLE
            }
            mainViewModel.prediction.observe(this) { suggest ->
                binding.suggestion.post {
                    binding.suggestion.text = suggest
                    binding.progress.visibility = View.INVISIBLE
                    showNotification("Rate", "Your today's rate is ready", 1)
                }
            }

        }

    }
 private fun showNotification(title:String,content:String,id:Int){
     val notification = NotificationCompat.Builder(this,CHANNEL_ID)
         .setSmallIcon(R.drawable.ic_launcher_foreground)
         .setContentTitle(title)
         .setContentText(content)
         .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    with(NotificationManagerCompat.from(this)){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if ( ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS,
                ) == PackageManager.PERMISSION_GRANTED){
                notify(id,notification.build())
            }
            else{
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)
            }
        }
    }

 }
    private fun getDateFormat(timeStamp: Long): String {
        val dateFormat = SimpleDateFormat("dd:MM:yyyy,HH:mm", Locale.getDefault())
        return dateFormat.format(Date(timeStamp * 1000L))
    }
}