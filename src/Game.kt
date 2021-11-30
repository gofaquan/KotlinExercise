fun main() {
    val name = "李剑豪"  //角色名
    var healthPoints = 89  //血量剩余
    var isBlessed = true //是否被治愈了
    val isImmortal = false //是否不死
    // Aura 角色光环
    val auraColor = auraColor(isBlessed, healthPoints, isImmortal) //光环颜色
    val healthStatus = formatHealthStatus(healthPoints, isBlessed) //目前的健康状态

    // Player status 角色的基本信息
    printPlayerStatus(auraColor, isBlessed, name, healthStatus)
}

//康康你健不健康
private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) = when (healthPoints) {
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

//角色的基本信息 => js_jbxx
private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healthStatus: String
) {
    println(
        "(Aura: $auraColor) (Blessed: ${if (isBlessed) "YES" else "NO"})"
    )
    println("$name $healthStatus")
}

//装杯光环的获取
private fun auraColor(
    isBlessed: Boolean,
    healthPoints: Int,
    isImmortal: Boolean
): String {
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    return if (auraVisible) "GREEN" else "NONE"
}

//秘技: 醉酒术！  (给角色喝酒
private fun castFireball(numFireballs: Int = 2) =
    println("A glass of Fireball springs into existence. (x$numFireballs)")


//秘技: 数酒术！  (数一下角色喝酒数，计算ta的醉酒状态
private fun countFireballNum(numFireballs: Int) =
    when (numFireballs) {
//      醉酒指标值        醉酒状态
        in 1..10 -> "tipsy（微醺）"
        in 11..20 -> "sloshed（微醉）"
        in 21..30 -> "soused（醉了）"
        in 31..40 -> "stewed（大醉）"
        in 41..50 -> "t0aSt3d（烂醉如泥）"
        else -> "你是真寄吧能喝啊，我不数了"
    }