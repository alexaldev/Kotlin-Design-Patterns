package behavioral

abstract class Unit(vararg units: Unit) {
    private val children: Array<Unit> = arrayOf(*units)
    fun accept(visitor: UnitVisitor) {
        children.forEach { it.accept(visitor) }
    }
}

interface UnitVisitor {
    fun visitSoldier(soldier: Soldier)
    fun visitSergeant(sergeant: Sergeant)
    fun visitCommander(commander: Commander)
}

class Commander(vararg units: Unit): Unit(*units) {}

class Sergeant: Unit() {}

class Soldier: Unit() {}
