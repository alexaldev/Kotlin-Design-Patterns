package creational

import java.net.http.HttpClient
import java.util.ArrayList

data class Panino(
    val name: String,
    val breadType: String,
    val fish: String?,
    val cheese: String?,
    val meat: String?,
    val vegetables: List<String>
)

// The classic Builder pattern.
// Many things can go wrong here.
class PaninoBuilder {
    private var name: String = ""
    private var breadType: String = ""
    private var fish: String = ""
    private var cheese: String = ""
    private var meat: String = ""
    private var vegetables: List<String> = arrayListOf()

    fun paninoCalled(name: String): PaninoBuilder {
        this.name = name
        return this
    }

    fun withBread(type: String): PaninoBuilder {
        this.breadType = type
        return this
    }

    fun withFish(fish: String): PaninoBuilder {
        this.fish = fish
        return this
    }

    fun withCheese(cheese: String): PaninoBuilder {
        this.cheese = cheese
        return this
    }

    fun withMeat(meat: String): PaninoBuilder {
        this.meat = meat
        return this
    }

    fun withVegetables(vegetables: List<String>): PaninoBuilder {
        this.vegetables = ArrayList(vegetables)
        return this
    }

    fun build(): Panino {
        return Panino(name, breadType, fish, cheese, meat, vegetables)
    }
}

class PaninoStepBuilder private constructor() {

    companion object {

        fun newBuilder(): FirstNameStep {
            return Steps()
        }
    }

    interface FirstNameStep {
        fun paninoCalled(name: String): BreadTypeStep
    }

    interface BreadTypeStep {
        fun breadType(type: String): MainFillingStep
    }

    interface MainFillingStep {
        fun meat(meat: String): CheeseStep
        fun fish(fish: String): VegetableStep
    }

    interface CheeseStep {
        fun noCheesePlease(): VegetableStep
        fun withCheese(cheese: String): VegetableStep
    }

    interface VegetableStep {

        fun noMoreVegetablesPlease(): BuildStep
        fun noVegetables(): BuildStep
        fun addVegetable(v: String): BuildStep
    }

    interface BuildStep {
        fun build(): Panino
    }
    
    class Steps : FirstNameStep, BreadTypeStep, MainFillingStep, CheeseStep, VegetableStep, BuildStep {

        private var name: String = ""
        private var breadType: String = ""
        private var fish: String = ""
        private var cheese: String = ""
        private var meat: String = ""
        private var vegetables: MutableList<String> = mutableListOf()

        override fun paninoCalled(name: String): BreadTypeStep {
            this.name = name
            return this
        }

        override fun breadType(type: String): MainFillingStep {
            this.breadType = type;
            return this
        }

        override fun meat(meat: String): CheeseStep {
            this.meat = meat
            return this
        }

        override fun fish(fish: String): VegetableStep {
            this.fish = fish
            return this
        }

        override fun noCheesePlease(): VegetableStep {
            return this
        }

        override fun withCheese(cheese: String): VegetableStep {
            this.cheese = cheese
            return this
        }

        override fun noMoreVegetablesPlease(): BuildStep {
            return this
        }

        override fun noVegetables(): BuildStep {
            return this
        }

        override fun addVegetable(v: String): BuildStep {
            this.vegetables.add(v)
            return this
        }

        override fun build(): Panino {
            return Panino(
                name = name,
                breadType = breadType,
                meat = meat,
                fish = fish,
                cheese = cheese,
                vegetables = vegetables
            )
        }
    }
}

fun main() {
    val panino = PaninoStepBuilder.newBuilder()
        .paninoCalled("sole panino")
        .breadType("baguette")
        .fish("Salmon")
        .noVegetables()
        .build()
}