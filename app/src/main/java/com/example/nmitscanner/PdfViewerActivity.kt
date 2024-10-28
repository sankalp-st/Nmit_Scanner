package com.example.nmitscanner

import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.nmitscanner.databinding.ActivityPdfViewerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class PdfViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fileName = intent.extras?.getString("fileName")
        val downloadUrl = intent.extras?.getString("downloadUrl")


        lifecycleScope.launch(Dispatchers.IO) {
            val inputStream = URL(downloadUrl).openStream()
            withContext(Dispatchers.Main){
                binding.pdfView.fromStream(inputStream).onRender{}.load()
                binding.progressBar.visibility = View.GONE
            }
        }


        }
    }