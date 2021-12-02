package com.github.gofaquan

import java.util.*


interface Creature {
}

//记住，接口只会定义要干什么，不管具体怎么实现。
//所以，Fightable 接口中的四个属性没有构造函数等初始化工具，attack 函数没有函数体。
interface Fightable {
    var healthPoints: Int
    val diceCount: Int
    val diceSides: Int

    //这个 getter 方法应该汇总掷骰子的总次数，以统计每一轮打怪施加伤害的点数。
    val damageRoll: Int
        get() = (0 until diceCount).map {
            Random().nextInt(diceSides) + 1
        }.sum()

    fun attack(opponent: Fightable): Int
}

//之所以定义 Monster 为抽象类，是因为它是更具体的各种怪物类的一个基础模板类。
// 你不会去创建一个 Monster，即使想也办不到。
// 事实上，我们会实例化 Monster 的子类：更具体的怪物，如小妖怪、幽灵、怪龙等。它们都是 Monster 抽象类的具体实现版本
abstract class Monster(
    val name: String,
    val description: String,
    override var healthPoints: Int
) : Fightable {
    override fun attack(opponent: Fightable): Int {
        val damageDealt = damageRoll
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}


class Goblin(
    name: String = "Goblin",
    description: String = "A nasty-looking goblin",
    healthPoints: Int = 30
) : Monster(name, description, healthPoints) {
    //Monster 抽象类没必要实现 Fightable 接口中所有的属性和函数。
    // 即使这样做了，因为是个抽象类，Monster 也没法被实例化。
    // 然而，它的子类必须实现 Fightable 接口中所有的属性和函数，要么靠继承，要么自己实现
    //下面就是填充Fightable需要的属性以便继承接口
    override val diceCount = 2
    override val diceSides = 8
}

//不管超类是哪种类型的类（一般类、抽象类或接口），子类默认都会共享其超类的所有属性和函数。
//如果一个类实现了一个接口，那么它的子类也必须满足这个接口的所有实现需求。
//你可能已经注意到了抽象类和接口相似的地方：它们都能定义无须自己实现的函数和属性。
//那么，它们有什么区别没有？
//当然有区别。首先，接口里不能定义构造函数。其次，一个类只能继承一个抽象类，但可以
//实现多个接口。日常开发时，对于如何使用它们，有个不错的经验法则：
//在你需要一组公共对象行为或属性时，如果继承行不通，就用接口。
//另一方面，如果继承可行，但你又不需要父类太具体，那就用抽象类。（如果还想实例化父类，那就只能用一般类。）