const val TAVERN_NAME = "Taernyl's Folly"  //老板店铺's name
fun main() {

    placeOrder("shandy,Dragon's Breath,5.91")
//    placeOrder("elixir,Shirley's Temple,4.12")

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
    val data = menuData.split(',')
    val type = data[0]
    val name = data[1]
    val price = data[2]

    val message = "Madrigal buys a $name ($type) for $price."
    println(message)

    val phrase = if (name == "Dragon's Breath") {
        //啊，好好喝的龙之息啊~~~~~~~~~~~
//        "Madrigal exclaims ${toDragonSpeak("Ah, delicious $name!")}"
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
            "a","A" -> "4"
            "e","E" -> "3"
            "i","I" -> "1"
            "o","O" -> "0"
            "u","U" -> "|_|"
            else -> it.value
        }
    }