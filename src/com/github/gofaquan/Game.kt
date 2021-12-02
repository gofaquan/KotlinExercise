package com.github.gofaquan

fun main() {
    var sword = Sword("lijinhao")
    println(sword.name)
    val player = Player("faq", 89, true, false)

    var player2 = Player("李健豪")
    player.healthPoints = 1
    // Aura 角色光环
    val auraColor = player.auraColor() //光环颜色
    val healthStatus = player.formatHealthStatus() //目前的健康状态

    // Player status 角色的基本信息
    printPlayerStatus(player)

}

//角色的基本信息 => js_jbxx(云家园有感
private fun printPlayerStatus(player: Player) {
    println(
        "(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
    )
    print(player.name)
//    println(player.formatHealthStatus())
}