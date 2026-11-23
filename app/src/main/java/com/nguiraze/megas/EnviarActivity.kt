package com.nguiraze.megas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nguiraze.megas.data.local.BancoFila

class EnviarActivity : AppCompatActivity() {
    private lateinit var banco: BancoFila

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviar)

        banco = BancoFila(this)

        val campoNumero = findViewById<EditText>(R.id.campoNumero)
        val campoMegas = findViewById<EditText>(R.id.campoMegas)
        val estado = findViewById<TextView>(R.id.textoEstado)

        findViewById<Button>(R.id.botaoAdicionar).setOnClickListener {
            val numero = campoNumero.text.toString().trim()
            val megas = campoMegas.text.toString().toIntOrNull()

            when {
                numero.length != 9 ->
                    estado.text = "Número inválido: precisa de 9 dígitos."
                megas == null || megas < 100 || megas > 10240 ->
                    estado.text = "Megas inválidos: use entre 100 e 10240."
                else -> {
                    banco.adicionar(numero, megas)
                    estado.text = "Adicionado à fila: $megas MB para $numero"
                    campoNumero.text.clear()
                    campoMegas.text.clear()
                }
            }
        }
    }
}
