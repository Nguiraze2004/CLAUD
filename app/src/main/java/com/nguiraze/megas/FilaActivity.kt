package com.nguiraze.megas

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nguiraze.megas.data.local.BancoFila

class FilaActivity : AppCompatActivity() {
    private lateinit var banco: BancoFila
    private lateinit var textoEmEspera: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fila)

        banco = BancoFila(this)
        textoEmEspera = findViewById(R.id.textoEmEspera)

        val campoNumero = findViewById<EditText>(R.id.campoNumero)
        val campoMegas = findViewById<EditText>(R.id.campoMegas)
        val estado = findViewById<TextView>(R.id.textoEstado)

        findViewById<View>(R.id.botaoVoltar).setOnClickListener { finish() }

        findViewById<View>(R.id.pacote100).setOnClickListener {
            campoMegas.setText("100")
        }

        findViewById<View>(R.id.botaoDespachar).setOnClickListener {
            val numero = campoNumero.text.toString().trim()
            val megas = campoMegas.text.toString().toIntOrNull()

            when {
                numero.length != 9 -> {
                    estado.setTextColor(Color.parseColor("#EF4444"))
                    estado.text = "Número inválido: precisa de 9 dígitos."
                }
                !megasValidos(megas) -> {
                    estado.setTextColor(Color.parseColor("#EF4444"))
                    estado.text = "Megas inválidos. Use 100 a 10240 MB, ou 20480, 30720, 40960, 51200 MB."
                }
                else -> {
                    banco.adicionar(numero, megas!!)
                    estado.setTextColor(Color.parseColor("#22C55E"))
                    estado.text = "Adicionado à fila: $megas MB para $numero"
                    campoNumero.text.clear()
                    campoMegas.text.clear()
                    atualizarContadores()
                }
            }
        }

        atualizarContadores()
    }

    override fun onResume() {
        super.onResume()
        atualizarContadores()
    }

    private fun megasValidos(megas: Int?): Boolean {
        if (megas == null) return false
        if (megas in 100..10240) return true
        return megas in listOf(20480, 30720, 40960, 51200)
    }

    private fun atualizarContadores() {
        textoEmEspera.text = banco.contarNaFila().toString()
    }
}
