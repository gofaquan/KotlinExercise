package com.github.gofaquan

open class Room(val name: String) {
    protected open val dangerLevel = 5
    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel\n" +
            "Creature: ${monster?.description ?: "none."}"

    open fun load() = "Nothing much to see here..."

    //如果某个 Room 的 monster 属性为空，那就说明里面的怪已被降服。否则，就是有怪等你去打。
    var monster: Monster? = Goblin()
}

open class TownSquare : Room("Town Square") {
    override val dangerLevel = super.dangerLevel - 3
    private var bellSound = "GWONG"
    final override fun load() = "The villagers rally and cheer as you enter!\n${ringBell()}"
    private fun ringBell() = "The bell tower announces your arrival. $bellSound"
}

class Piazza : TownSquare() {
//    override fun load() = "faq"  final后就不可在重写了！
}