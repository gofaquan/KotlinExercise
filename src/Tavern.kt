fun main() {
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