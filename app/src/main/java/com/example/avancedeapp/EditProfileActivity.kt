package com.example.avancedeapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etDepartment: EditText
    private lateinit var etDistrict: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCardNumber: EditText
    private lateinit var btnSaveChanges: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Initialize views
        etName = findViewById(R.id.etName)
        etLastName = findViewById(R.id.etLastName)
        etEmail = findViewById(R.id.etEmail)
        etDepartment = findViewById(R.id.etDepartment)
        etDistrict = findViewById(R.id.etDistrict)
        etAddress = findViewById(R.id.etAddress)
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        etCardNumber = findViewById(R.id.etCardNumber)
        btnSaveChanges = findViewById(R.id.btnSaveChanges)

        // Set data from intent extras
        etName.setText(intent.getStringExtra("Nombre"))
        etLastName.setText(intent.getStringExtra("Apellido"))
        etEmail.setText(intent.getStringExtra("Correo"))
        etDepartment.setText(intent.getStringExtra("Departamento"))
        etDistrict.setText(intent.getStringExtra("Distrito"))
        etAddress.setText(intent.getStringExtra("Direccion"))
        etPhone.setText(intent.getStringExtra("Celular"))
        etPassword.setText(intent.getStringExtra("Contraseña"))
        etCardNumber.setText(intent.getStringExtra("Número de tarjeta"))

        btnSaveChanges.setOnClickListener {
            // Aquí puedes guardar los cambios en tu backend o base de datos local
            // Por ahora, simplemente finalizaremos la actividad para volver al perfil principal
            finish()
        }
    }
}
