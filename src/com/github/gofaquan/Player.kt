package com.github.gofaquan

class Player {
    val name = "李剑豪"  //角色名
        get() = field.capitalize()  //getter

    var healthPoints = 89  //血量剩余
        set(value) {//setter
            field = value - 1  //就要扣你一滴血，嘿嘿
            println(field)
        }
    var isBlessed = true //是否被治愈了
    private val isImmortal = false //是否不死


    //康康你健不健康
     fun formatHealthStatus() = when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 -> if (isBlessed) {
            "has some minor wounds, but is healing quite quickly!"
        } else {
            "has some minor wounds."
        }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }




    //装杯光环的获取
     fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        return if (auraVisible) "GREEN" else "NONE"
    }


    //秘技: 醉酒术！  (给角色喝酒
    private fun castFireball(numFireballs: Int = 2) =
        println("A glass of Fireball springs into existence. (x$numFireballs)")

    //秘技: 数酒术！  (数一下角色喝酒数，计算ta的醉酒状态
    private fun countFireballNum(numFireballs: Int) =
        when (numFireballs) {
//          醉酒指标值     醉酒状态
            in 1..10 -> "tipsy（微醺）"
            in 11..20 -> "sloshed（微醉）"
            in 21..30 -> "soused（醉了）"
            in 31..40 -> "stewed（大醉）"
            in 41..50 -> "t0aSt3d（烂醉如泥）"
            else -> "你是真寄吧能喝啊，我不数了"
        }


}

//修 饰 符 描 述
//public（默认） 函数或属性类对于外部可见。默认情况下，不加可见性修饰符的函数或属性都是公共可见的
//private 函数或属性仅在类自己的内部可见
//protected 函数或属性仅在类自己的内部或该类的子类内可见
//internal 函数或属性仅在同一模块内可见