fun main() {
    val player = Player()
    player.healthPoints = 1
    // Aura 角色光环
    val auraColor = auraColor(player.isBlessed, player.healthPoints, player.isImmortal) //光环颜色
    val healthStatus = formatHealthStatus(player.healthPoints, player.isBlessed) //目前的健康状态

    // Player status 角色的基本信息
    printPlayerStatus(auraColor, player.isBlessed, player.name, healthStatus)

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



