fun main() {
//欢迎新镇长
runSimulation()

}
fun runSimulation() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("Guyal","hospitals"))
    println(greetingFunction("Guyal","hospitals"))
}
fun configureGreetingFunction(): (String,String) -> String {
    var numBuildings = 5
    return { playerName: String, structureType->
        val currentYear = 2018
        numBuildings += 1
        println("Adding $numBuildings $structureType")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
}



//使用 runSimulation 函数里的值输出房屋的建设成本
fun printConstructionCost(numBuildings: Int) {
    val cost = 500
    println("construction cost: ${cost * numBuildings}")
}



