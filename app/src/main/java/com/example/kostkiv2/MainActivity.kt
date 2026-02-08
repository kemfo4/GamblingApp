@file:Suppress("UNCHECKED_CAST")

package com.example.kostkiv2

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Integer.parseInt
import kotlin.random.Random

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

        val balanceText: TextView = findViewById(R.id.textBalance)
        val scoreText: TextView = findViewById(R.id.textScore)
        dicesDraw = arrayOf(R.drawable.k1, R.drawable.k2, R.drawable.k3, R.drawable.k4, R.drawable.k5, R.drawable.k6)
        balanceText.text = getString(R.string.balance, balance)
        scoreText.text = getString(R.string.score, score)
    }

    var balance: Int = 100
    var score: Int = 0
    var dicesDraw = arrayOfNulls<Int>(6) as Array<Int>

    fun rollDice(view: View) {
        val selDice: ImageButton = view as ImageButton

        if((balance-2)>=0 && selDice.contentDescription.toString()!="%d" && selDice.contentDescription.toString()!="1") {
            val value = Random.nextInt(1, 6 + 1)
            score -= parseInt(selDice.contentDescription.toString())

            selDice.setImageResource(dicesDraw[value - 1])
            selDice.contentDescription = getString(R.string.value, value)

            score+= parseInt(selDice.contentDescription.toString())/2
        }

    }

    fun rollDices(view: View) {
        val balanceText: TextView = findViewById(R.id.textBalance)
        val scoreText: TextView = findViewById(R.id.textScore)

        val dices: Array<ImageButton> = arrayOf(findViewById(R.id.dice1),
                                                findViewById(R.id.dice2),
                                                findViewById(R.id.dice3),
                                                findViewById(R.id.dice4),
                                                findViewById(R.id.dice5))

        score = 0
        balance-=5

        for(dice in dices) {
            val value = Random.nextInt(1, 6+1)

            dice.setImageResource(dicesDraw[value-1])
            dice.contentDescription = getString(R.string.value, value)
        }

        {
            var index = 1
            var multiplier = 1
            while (index < 5) {
                if (dices[index - 1].contentDescription == dices[index].contentDescription)
                    multiplier++
                else if (multiplier >= 2) {
                    score += parseInt(dices[index - 1].contentDescription.toString()) * multiplier
                    multiplier = 1
                }

                index++
            }

            if(multiplier>=2)
                score += parseInt(dices[index - 1].contentDescription.toString()) * multiplier
        }

        if(score>=10)
            balance+=score/2

        balanceText.text = getString(R.string.balance, balance)
        scoreText.text = getString(R.string.score, score)
    }
}