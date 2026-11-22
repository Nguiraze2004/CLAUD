package com.nguiraze.megas

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var pausado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_painel)

        val textoSim1 = findViewById<TextView>(R.id.textoSim1)
        val textoSim2 = findViewById<TextView>(R.id.textoSim2)
        val botaoPausar = findViewById<Button>(R.id.botaoPausar)

        findViewById<Button>(R.id.botaoReset1).setOnClickListener {
            textoSim1.text = "0/10"
        }
        findViewById<Button>(R.id.botaoReset2).setOnClickListener {
            textoSim2.text = "0/10"
        }

        botaoPausar.setOnClickListener {
            pausado = !pausado
            botaoPausar.text = if (pausado) "Retomar" else "Pausar"
        }

        listOf(R.id.botaoEnviar, R.id.botaoFila, R.id.botaoHistorico, R.id.botaoMeta)
            .forEach { id ->
                findViewById<Button>(id).setOnClickListener {
                    Toast.makeText(this, "Em breve", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
