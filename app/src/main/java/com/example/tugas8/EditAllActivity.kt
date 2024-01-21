package com.example.tugas8

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas8.databinding.ActivityEditAllBinding


// ...

// ...

class EditAllActivity : AppCompatActivity() {
    private lateinit var editBinding: ActivityEditAllBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = ActivityEditAllBinding.inflate(layoutInflater)
        setContentView(editBinding.root)

        // Menerima data yang dikirimkan dari ProfilActivity.kt
        val intentData = intent.extras
        val namaUser = intentData?.getString("nama")
        val genderUser = intentData?.getString("gender")
        val emailUser = intentData?.getString("email")
        val telpUser = intentData?.getString("telp")
        val alamatUser = intentData?.getString("alamat")

        // Set EditText dengan data yang dikirimkan tadi
        editBinding.edtProfilName.setText(namaUser)
        editBinding.edtProfilEmail.setText(emailUser)
        editBinding.edtProfilTelp.setText(telpUser)
        editBinding.edtProfilAddress.setText(alamatUser)

        // Set Spinner dengan data yang dikirimkan tadi
        val genderArray = resources.getStringArray(R.array.jenis_kelamin)
        val genderPosition = genderArray.indexOf(genderUser)
        if (genderPosition != -1) {
            editBinding.spinnerProfilGender.setSelection(genderPosition)
        }



        // Berikan action click ke tombol Simpan
        editBinding.btnEditSave.setOnClickListener { saveData() }
    }

    private fun saveData() {
        val namaEdit = editBinding.edtProfilName.text.toString()
        val genderEdit = editBinding.spinnerProfilGender.selectedItem.toString()
        val emailEdit = editBinding.edtProfilEmail.text.toString()
        val telpEdit = editBinding.edtProfilTelp.text.toString()
        val alamatEdit = editBinding.edtProfilAddress.text.toString()

        if (!namaEdit.isEmpty()) {
            // Jika EditText namaEdit tidak kosong, kirimkan valuenya
            // ke ProfilActivity dan beri tanda RESULT_OK
            val result = Intent()
            result.putExtra("nama", namaEdit)
            result.putExtra("gender", genderEdit)
            result.putExtra("email", emailEdit)
            result.putExtra("telp", telpEdit)
            result.putExtra("alamat", alamatEdit)
            setResult(Activity.RESULT_OK, result)
        } else {
            // Jika EditText namaEdit kosong, kirimkan tanda RESULT_CANCELED
            setResult(Activity.RESULT_CANCELED)
        }

        finish()
    }
}



