package com.nguiraze.megas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val campoNumero = findViewById<EditText>(R.id.campoNumero)
        val campoMegas = findViewById<EditText>(R.id.campoMegas)
        val botao = findViewById<Button>(R.id.botaoEnviar)
        val estado = findViewById<TextView>(R.id.textoEstado)

        botao.setOnClickListener {
            val numero = campoNumero.text.toString().trim()
            val megas = campoMegas.text.toString().toIntOrNull()

            when {
                numero.length != 9 ->
                    estado.text = "Número inválido: precisa de 9 dígitos."
                megas == null || megas < 100 || megas > 10240 ->
                    estado.text = "Megas inválidos: use entre 100 e 10240."
                else ->
                    estado.text = "Pedido pronto: $megas MB para $numero"
            }
        }
    }
}
