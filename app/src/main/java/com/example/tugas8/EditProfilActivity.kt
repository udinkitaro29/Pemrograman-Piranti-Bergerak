package com.example.tugas8

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas8.databinding.ActivityEditProfilBinding

class EditProfilActivity : AppCompatActivity() {
    private lateinit var editBinding: ActivityEditProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(editBinding.root)

        // Menerima data yang dikirimkan dari ProfilActivity.kt
        val intentData = intent.extras
        val namaUser = intentData?.getString("nama")

        // Set EditText dengan data yang dikirimkan tadi
        editBinding.edtProfilName.setText(namaUser)

        // Berikan action click ke tombol Simpan
        editBinding.btnEditSave.setOnClickListener { saveData() }
    }

    private fun saveData() {
        val namaEdit = editBinding.edtProfilName.text.toString()

        if (!namaEdit.isEmpty()) {
            // Jika EditText namaEdit tidak kosong, kirimkan valuenya
            // ke ProfilActivity dan beri tanda RESULT_OK
            val result = Intent()
            result.putExtra("nama", namaEdit)
            setResult(Activity.RESULT_OK, result)
        } else {
            // Jika EditText namaEdit kosong, kirimkan tanda RESULT_CANCELED
            setResult(Activity.RESULT_CANCELED)
        }

        finish()
    }
}
