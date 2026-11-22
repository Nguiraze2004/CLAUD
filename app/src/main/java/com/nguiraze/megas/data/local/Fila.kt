package com.nguiraze.megas.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class PedidoFila(
    val id: Long = 0,
    val numeroCliente: String,
    val megas: Int,
    val estado: String = "NA_FILA",
    val criadoEm: Long = System.currentTimeMillis()
)

class BancoFila(context: Context) :
    SQLiteOpenHelper(context, "fila.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE pedidos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "numero TEXT NOT NULL," +
                "megas INTEGER NOT NULL," +
                "estado TEXT NOT NULL," +
                "criado_em INTEGER NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS pedidos")
        onCreate(db)
    }

    fun adicionar(numero: String, megas: Int): Long {
        val valores = ContentValues().apply {
            put("numero", numero)
            put("megas", megas)
            put("estado", "NA_FILA")
            put("criado_em", System.currentTimeMillis())
        }
        return writableDatabase.insert("pedidos", null, valores)
    }

    fun contarNaFila(): Int {
        readableDatabase.rawQuery(
            "SELECT COUNT(*) FROM pedidos WHERE estado = 'NA_FILA'", null
        ).use { c ->
            return if (c.moveToFirst()) c.getInt(0) else 0
        }
    }

    fun ultimos(limite: Int = 5): List<PedidoFila> {
        val lista = mutableListOf<PedidoFila>()
        readableDatabase.rawQuery(
            "SELECT id, numero, megas, estado, criado_em FROM pedidos " +
                "ORDER BY id DESC LIMIT ?",
            arrayOf(limite.toString())
        ).use { c ->
            while (c.moveToNext()) {
                lista.add(
                    PedidoFila(
                        c.getLong(0),
                        c.getString(1),
                        c.getInt(2),
                        c.getString(3),
                        c.getLong(4)
                    )
                )
            }
        }
        return lista
    }
    }
