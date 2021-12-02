package com.github.gofaquan

import java.io.File

//伴生对象本质上依然是个对象声明，所以不需要使用类实例来访问它内部定义的函数或属
//性。以下是一个伴生对象示例，它定义在一个叫 PremadeWorldMap 的类里。
class PreMadeWorldMap {
    companion object {
        private const val MAPS_FILEPATH = "nyethack.maps"
        fun load() = File(MAPS_FILEPATH).readBytes()
    }
}
//这个伴生对象里定义了一个叫作 load 的函数。如果想调用 load 函数，无须 PremadeWorldMap 类实例，像下面这样直接调用就可以了：
//PreMadeWorldMap.load()
//按照上面说的两种情况，只有初始化 PremadeWorldMap 类或调用 load 函数时，伴生对象
//的内容才会载入。而且，无论实例化 PremadeWorldMap 类多少次，这个伴生对象始终只有一个
//实例存在。