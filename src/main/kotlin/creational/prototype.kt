package creational

interface Prototype {
    fun copy(): Any
}

abstract class Beast: Prototype {

    constructor(beast: Beast) {}

    abstract override fun copy(): Beast
}

class OrcBeast: Beast {

    private val weapon: String

    constructor(orcBeast: OrcBeast) : super(orcBeast) {
        weapon = orcBeast.weapon
    }

    override fun copy(): OrcBeast {
        return OrcBeast(this)
    }

}