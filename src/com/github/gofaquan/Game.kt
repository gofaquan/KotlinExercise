package com.github.gofaquan

import kotlin.system.exitProcess

fun main() {
    Game.play()

}

object Game {
    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()
    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
    )


    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction is out of bounds.")
            }
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        } catch (e: Exception) {
            "Invalid direction: $directionInput."
        }


    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        fun processCommand() = when (command.toLowerCase()) {
            "move" -> move(argument)
            "map" -> map()
            "exit", "quit" -> exitGame()
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    init {
        println("Welcome, adventurer.")
        player.castFireball()
    }

    fun play() {
        while (true) {
            // Play NyetHack
            println(currentRoom.description())
            println(currentRoom.load())
            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
    }


    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0) {
            Thread.sleep(1000)
        }
        "Combat complete."
    } ?: "There's nothing here to fight."


    //玩家和怪物的一轮打斗靠一个叫 slay 的私有函数来模拟。
    private fun slay(monster: Monster) {
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")
        if (player.healthPoints <= 0) {
            println(">>>> You have been defeated! Thanks for playing. <<<<")
            exitProcess(0)
        }
        if (monster.healthPoints <= 0) {
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }
    }

    //角色的基本信息 => js_jbxx(云家园有感
    private fun printPlayerStatus(player: Player) {
        println(
            "(Aura: ${player.auraColor()}) " +
                    "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
        )

        println("${player.name} ${player.formatHealthStatus()}")
    }

    private fun map(): String {
        val notInRoom = "O "
        val inRoom = "X "
        var index = 0
        var map = ""

        while (index < worldMap.count()) {
            worldMap[index].forEach {
                map += if (it == currentRoom) {
                    inRoom
                } else {
                    notInRoom
                }
            }
            map += "\n"
            index += 1
        }
        return map
    }

    private fun exitGame() {
        println("Thank you for playing NyetHack!")
        exitProcess(0)
    }

    private fun ring() {
        TODO("还不会写，等我想想")
    }
}

