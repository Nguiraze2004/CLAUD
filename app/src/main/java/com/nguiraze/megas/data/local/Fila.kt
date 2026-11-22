package com.nguiraze.megas.data.local

data class PedidoFila(
    val id: Long = 0,
    val numeroCliente: String,
    val megas: Int,
    val estado: String = "NA_FILA",
    val criadoEm: Long = System.currentTimeMillis()
)
