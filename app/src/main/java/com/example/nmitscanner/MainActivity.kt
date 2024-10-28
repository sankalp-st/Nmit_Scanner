package com.example.nmitscanner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.documentfile.provider.DocumentFile
import com.example.nmitscanner.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.ref.Reference

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var pdfFileUri: Uri? = null
    private lateinit var storageReference: StorageReference
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        init()
        iniClickListeners()


    }

    private fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storageReference = FirebaseStorage.getInstance().reference.child("pdfs")
        databaseReference = FirebaseDatabase.getInstance().reference.child("pdfs")
    }

    private fun iniClickListeners() {
        binding.selectPdfButton.setOnClickListener {
            launcher.launch("application/pdf")

        }
        binding.uploadBtn.setOnClickListener {

            if (pdfFileUri != null) {
                uploadPdfFileToFirebase()


            } else {
                Toast.makeText(this, "Please select pdf first", Toast.LENGTH_SHORT).show()
            }
        }
        binding.showAllBtn.setOnClickListener{
            val intent = Intent(this,AllPdfsActivity::class.java)
            startActivity(intent)
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        pdfFileUri = uri
        val fileName = uri?.let { DocumentFile.fromSingleUri(this, it)?.name }
        binding.fileName.text = fileName.toString()
    }

    private fun uploadPdfFileToFirebase() {

        val fileName = binding.fileName.text.toString()
        val mStorageRef = storageReference.child("${System.currentTimeMillis()}/$fileName")

        pdfFileUri?.let { uri ->
            mStorageRef.putFile(uri).addOnSuccessListener {
                mStorageRef.downloadUrl.addOnSuccessListener { downloadUri ->

                    val pdfFile = PdfFile(fileName, downloadUri.toString())
                    databaseReference.push().key?.let { pushKey ->
                        databaseReference.child(pushKey).setValue(pdfFile)
                            .addOnSuccessListener {

                                pdfFileUri = null
                                binding.fileName.text = resources.getString(R.string.no_pdf_selected)

                                Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()

                                    binding.progressBar.visibility = View.GONE

                            }.addOnFailureListener { err ->
                                Toast.makeText(this, err.message.toString(), Toast.LENGTH_SHORT)
                                    .show()


                                    binding.progressBar.visibility = View.GONE

                            }

                    }
                }
            }.addOnProgressListener { uploadTask ->
                val uploadingPercent = uploadTask.bytesTransferred * 100 / uploadTask.totalByteCount
                binding.progressBar.progress = uploadingPercent.toInt()
                if (!binding.progressBar.isShown)
                    binding.progressBar.visibility = View.VISIBLE

            }.addOnFailureListener { err ->

                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()


            }
        }
    }
}
