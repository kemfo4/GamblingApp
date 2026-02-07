package com.example.kostkiv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var balanceView: TextView = findViewById(R.id.Balance);
        balanceView.text = getString(R.string.balance, balance)
    }

    val balance: Int =100;

    fun loadGames() {
        val gamesHub: LinearLayout = findViewById(R.id.gamesHub);
    }

    fun rollDice(view: View) {

    }

    fun rollDices(view: View) {
        val dicesDraw: Array<Int> = arrayOf(R.drawable.k1, R.drawable.k2, R.drawable.k3, R.drawable.k4, R.drawable.k5, R.drawable.k6);

    }
}