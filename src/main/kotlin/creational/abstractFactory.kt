package creational

import java.util.logging.Level
import java.util.logging.Logger

interface Castle { fun getDescription(): String }
interface King { fun getDescription(): String }
interface Army { fun getDescription(): String }

// Elvens
class ElvenCastle: Castle {
    override fun getDescription() = "Elven castle ! "
}

class ElvenKing: King {
    override fun getDescription() = "Elven king ! "
}

class ElvenArmy: Army {
    override fun getDescription() = "Elven army ! "
}

// Orcs
class OrcCastle: Castle {
    override fun getDescription() = "Orc castle ! "
}

class OrcKing: King {
    override fun getDescription() = "Orc king ! "
}

class OrcArmy: Army {
    override fun getDescription() = "Orc army ! "
}

// ABSTRACT FACTORY
interface KingdomFactory {
    fun createCastle(): Castle
    fun createKing(): King
    fun createArmy(): Army
}

class ElvenFactory: KingdomFactory {
    override fun createCastle(): Castle {
        return ElvenCastle()
    }

    override fun createKing(): King {
        return ElvenKing()
    }

    override fun createArmy(): Army {
        return ElvenArmy()
    }
}

class OrcFactory: KingdomFactory {
    override fun createCastle(): Castle {
        return OrcCastle()
    }

    override fun createKing(): King {
        return OrcKing()
    }

    override fun createArmy(): Army {
        return OrcArmy()
    }
}

class FactoryMaker {
    enum class KingdomType {
        ElF, ORC
    }
    companion object {
        fun makeFactory(type: KingdomType) =
            when(type) {
                KingdomType.ElF -> ElvenFactory()
                KingdomType.ORC -> OrcFactory()
            }
    }
}

fun main() {
    println("I will create an elven kingdom")
    with(FactoryMaker.makeFactory(FactoryMaker.KingdomType.ElF)) {
        println(createKing().getDescription() + createCastle().getDescription() + createArmy().getDescription())
    }
}


