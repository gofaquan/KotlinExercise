package com.github.gofaquan

//武器带师们的武器
open class Weapon(val name:String, val type: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Weapon
        if (name != other.name) return false
        if (type != other.type) return false
        return true
    }
    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}
