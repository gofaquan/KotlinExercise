package com.github.gofaquan

fun main() {
    //耍剑🗡模式
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient) {
        swordsJuggling = 2
    }
    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }
    println("You juggle $swordsJuggling swords!")
}

fun proficiencyCheck(swordsJuggling: Int?) {
    checkNotNull(swordsJuggling) { "com.github.gofaquan.Player cannot juggle swords" }
}

class UnskilledSwordJugglerException() :
    IllegalStateException("com.github.gofaquan.Player cannot juggle swords")