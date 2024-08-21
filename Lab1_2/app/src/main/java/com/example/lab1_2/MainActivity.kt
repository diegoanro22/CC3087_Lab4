package com.example.lab1_2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab1_2.ui.theme.Lab1_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1_2Theme {
                val team = Team.Mercedes
                println(printDetails((team)))

                println(exercise2(listOf(3, 5, 4, 4, 3, 1, 3, 2), "EAST"))
                println(exercise2(listOf(3, 5, 4, 4, 3, 1, 3, 2), "WEST"))

                println(exercise3("abcdcaf"))
                println(exercise3("loveleetcode"))
            }
        }




    }


    @Composable
    fun printDetails(team: Team){
        println("Team: $team")
        when(team){
            Team.Mercedes -> println("Mercedes")
            Team.RedBullRacing -> println("RedBullRacing")
            Team.Ferrari -> println("Ferrari")
            Team.McLaren -> println("McLaren")
            Team.AstonMartin -> println("AstonMartin")
            Team.Alpine -> println("Alpine")
            Team.AlphaTauri -> println("AlphaTauri")
            Team.AlfaRomeo -> println("AlfaRomeo")
            Team.Haas -> println("Haas")
            Team.Williams -> println("Williams")

    }

}

    private fun exercise2 (buildings: List<Int>, direction: String) : List<Int> {

        val listIndex = mutableListOf<Int>()
        var max = 0

        when (direction) {
            "EAST" -> {
                for (i in buildings.size - 1 downTo 0) {
                    if (buildings[i] > max) {
                        listIndex.add(i)
                        max = buildings[i]
                    }
            } ;return listIndex.reversed()
            }
            "WEST" -> {
                for (i in 0 until buildings.size - 1) {
                    if (buildings[i] > max){
                        listIndex.add(i)
                        max = buildings[i]
                    }
                }
                return listIndex

            }
        }
        return listIndex


    }

    private fun exercise3(character : String) : Int {
        val hashMap = HashMap<Char, Int>()
        for (i in character){
            hashMap[i] = hashMap.getOrDefault(i, 0) + 1
        }

        for ((i, key) in character.withIndex()) {
            if (hashMap[key] == 1) {
                return i
            }
        }

        return -1


    }




}
