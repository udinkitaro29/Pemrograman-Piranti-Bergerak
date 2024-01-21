package com.example.tugas8

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas8.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private lateinit var profilBinding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profilBinding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(profilBinding.root)

        ambilData()

        profilBinding.btnEditName.setOnClickListener { navigasiKeEditProfil() }
        profilBinding.btnEditAll.setOnClickListener { navigasiKeEditAll() }
        profilBinding.btnCall.setOnClickListener { dialPhoneNumber(profilBinding.txtTelp.text.toString()) }

    }

    private fun ambilData() {
        val bundle = intent.extras
        val nama = bundle?.getString("nama")
        val gender = bundle?.getString("gender")
        val email = bundle?.getString("email")
        val telp = bundle?.getString("telp")
        val alamat = bundle?.getString("alamat")

        profilBinding.txtName.text = nama
        profilBinding.txtGender.text = gender
        profilBinding.txtEmail.text = email
        profilBinding.txtTelp.text = telp
        profilBinding.txtAddress.text = alamat
    }

    private fun navigasiKeEditProfil() {
        val intent = Intent(this, EditProfilActivity::class.java)
        val namaUser = profilBinding.txtName.text.toString()
        intent.putExtra("nama", namaUser)
        resultLauncher.launch(intent)
    }
    private fun navigasiKeEditAll() {
        val intent = Intent(this, EditAllActivity::class.java)
        val namaUser = profilBinding.txtName.text.toString()
        val genderUser = profilBinding.txtGender.text.toString()
        val emailUser = profilBinding.txtEmail.text.toString()
        val telpUser = profilBinding.txtTelp.text.toString()
        val alamatUser = profilBinding.txtAddress.text.toString()

        intent.putExtra("nama", namaUser)
        intent.putExtra("gender", genderUser)
        intent.putExtra("email", emailUser)
        intent.putExtra("telp", telpUser)
        intent.putExtra("alamat", alamatUser)

        resultLauncher.launch(intent)
    }


    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val resultData = result.data
                val nama = resultData?.getStringExtra("nama")
                val gender = resultData?.getStringExtra("gender")
                val email = resultData?.getStringExtra("email")
                val telp = resultData?.getStringExtra("telp")
                val alamat = resultData?.getStringExtra("alamat")

                profilBinding.txtName.text = nama
                profilBinding.txtGender.text = gender
                profilBinding.txtEmail.text = email
                profilBinding.txtTelp.text = telp
                profilBinding.txtAddress.text = alamat
            } else {
                Toast.makeText(this, "Edit failed", Toast.LENGTH_SHORT).show()
            }
        }


    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
