package com.github.gofaquan

import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"  //老板店铺's name
const val MenuLen = 34
const val CharLen = 4  //~[????]~的长度
var playerGold = 10 //金币
var playerSilver = 10 //银币
var patronGold = mutableMapOf<String, Double>("ava" to 6.0)

//TODO
//身无分文的人不应来小客栈消费。事实上，这样的人也不应在此逗留——小客栈门卫应负责
//这些事。如果顾客钱包余额不足，就从 uniquePatrons 和 com.github.gofaquan.getPatronGold 中删除他们的信息，把
//他们请出小客栈。


fun main() {
    println("*** Welcome to Taernyl's Folly ***")
    val menuList = File("data/tavern-menu-items.txt")
        .readText()
        .split("\n")
    menuList.forEach { data ->
        val (type, name, price) = data.split(",")
        //菜单美化带师
        (1..(MenuLen - CharLen - type.length) / 2).forEach { _ ->
            print(" ")
        }
        print("~[$type]~")
        (1..(MenuLen - CharLen - type.length) / 2).forEach { _ ->
            print(" ")
        }
        println()
        print("$name")
        (1..MenuLen - price.length - name.length).forEach { _ ->
            print(".")

        }
        println("$price")

    }


    placeOrder("shandy,Dragon's Breath,5.91")
//    com.github.gofaquan.placeOrder("elixir,Shirley's Temple,4.12")
    displayPatronBalances()

    //小客栈，给顾客点单
    var beverage = readLine()
    // beverage = null
    if (beverage != null) {
        beverage = beverage.capitalize()
    } else {
        println("I can't do that without crashing - beverage was null!")
    }
    println(beverage)

//    var beverage = readLine()
//    beverage?.let {
//        beverage = it.capitalize()
//    } ?: println("I can't do that without crashing - beverage was null!")


    val beverageServed: String = beverage ?: "Buttered Ale"
    println(beverageServed)
}

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'') //利用转义字符检查 ' 的下标
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe) //这不能理解那就可以埋了
    println("Madrigal speaks with $tavernMaster about their order.")

    //字符串分片
    val (type, name, price) = menuData.split(',')

    val message = "Madrigal buys a $name ($type) for $price."
    println(message)


//    com.github.gofaquan.performPurchase(price.toDouble())
//    com.github.gofaquan.performPurchase(price.toDoubleOrNull() ?: 0.0)
    performPurchase(price.toDouble(), "ava")

    val phrase = if (name == "Dragon's Breath") {
        //啊，好好喝的龙之息啊~~~~~~~~~~~
//        "Madrigal exclaims ${com.github.gofaquan.toDragonSpeak("Ah, delicious $name!")}"
        "Madrigal exclaims ${toDragonSpeak("DRAGON'S BREATH: IT'S GOT WHAT ADVENTURERS CRAVE! \n")}"
    } else {
        "Madrigal says: Thanks for the $name."
    }
    println(phrase)
}

//Dragon’s Breath（龙之息）饮料不仅能带给你愉悦的感官体验，
//还能让你就此获得高级编程能力，会说类似 1337Sp34k 黑客加密语言这样的古老龙之语。
//例如，下面这条忠告：
//A word of advice: Don't drink the Dragon's Breath
//翻译成龙之语就是：
//A w0rd 0f 4dv1c3: D0n't dr1nk th3 Dr4g0n's Br34th
private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[/\\w+/i aeiou]")) {  //俺也理解不了的正寄吧则
        when (it.value) {
            "a", "A" -> "4"
            "e", "E" -> "3"
            "i", "I" -> "1"
            "o", "O" -> "0"
            "u", "U" -> "|_|"
            else -> it.value
        }
    }

//学了map的船新版本
fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}
//fun com.github.gofaquan.performPurchase(price: Double) {
//    displayBalance()
//    // 这里的100.0让int转成double，否则 10/100 = 0.1的int等于0
//    val totalPurse = com.github.gofaquan.getPlayerGold + (com.github.gofaquan.getPlayerSilver / 100.0) //购买力 = 金币 + 银币/100.0,
//    println("Total purse: $totalPurse")
//    println("Purchasing item for $price")
//
//    val remainingBalance = totalPurse - price
//    if (remainingBalance >= 0) {
//        println("Remaining balance: ${"%.2f".format(remainingBalance)}") //小客栈里的交易计算对精度的要求就没那么高
//    } else {
//        println("Total purse too less to purchase the wine!")
//        return
//    }
//    //把购买力换算成原来的金币银币
//    val remainingGold = remainingBalance.toInt()
////    这里，我们使用了取模运算符%（又叫取余运算符），也就是求两个数相除的余数。% 1 的效
////    果就是拿掉整数部分值（这部分能被 1 整除），剩下小数部分值。最后，用余数乘以 100，然后
////    针对 18.99999999999995 调用 roundToInt 函数。该函数四舍五入，得到最近似的整数，所以
////    剩余的银币数就是 19。
////    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
//    val remainingSilver = (remainingBalance % 1 * 100).toInt() //上面四舍五入，我的想法是不能凑成1的银币那肯定不能算的啦
//    com.github.gofaquan.getPlayerGold = remainingGold
//    com.github.gofaquan.getPlayerSilver = remainingSilver
//    displayBalance()
//}

private fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}