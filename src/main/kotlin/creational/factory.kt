package creational

import lombok.RequiredArgsConstructor

interface Coin {
    fun getDescription(): String
}

class GoldCoin : Coin {
    override fun getDescription(): String {
        return "a gold coin"
    }
}

class CopperCoin : Coin {
    override fun getDescription(): String {
        return "a copper coin"
    }
}

@RequiredArgsConstructor
enum class CoinType {
    Copper,
    Gold
}

class CoinFactory {
    companion object {
        fun getCoin(type: CoinType): Coin {
            return when (type) {
                CoinType.Copper -> CopperCoin()
                CoinType.Gold -> GoldCoin()
            }
        }
    }
}

fun main() {
    val coin1 = CoinFactory.getCoin(CoinType.Gold)
    val coin2 = CoinFactory.getCoin(CoinType.Copper)
}
