fun main() {
    val name = "Madrigal"  //角色名
    var healthPoints = 89  //血量剩余
    var isBlessed = true //是否被治愈了
    val isImmortal = false //是否不死
    // Aura 角色光环
    val auraColor = auraColor(isBlessed, healthPoints, isImmortal) //光环颜色
    val healthStatus = formatHealthStatus(healthPoints, isBlessed) //目前的健康状态

    // Player status 角色的基本信息
    printPlayerStatus(auraColor, isBlessed, name, healthStatus)
}

//康康你的身体健不健康
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

//角色的基本信息 => js_jbxx(云家园导表有感
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
    val auraColor = if (auraVisible) "GREEN" else "NONE"
    return auraColor
}

//秘技: 火球术！
private fun castFireball(numFireballs: Int = 2) =
    println("A glass of Fireball springs into existence. (x$numFireballs)")