package behavioral

interface State {
    fun onEnterState()
    fun observe()
}

class PeacefulState(private val mammoth: Mammoth): State {
    override fun onEnterState() {
        println("$mammoth calms down. Peace !")
    }

    override fun observe() {
        println("$mammoth is calm and peaceful. Let's smome some!")
    }
}

class AngryState(private val mammoth: Mammoth): State {
    override fun onEnterState() {
        println("$mammoth gets angry !")
    }

    override fun observe() {
        println("$mammoth is furious ! Run for your lives fuckers!")
    }
}

class Mammoth {
    private var state: State = PeacefulState(this)

    fun timePasses() {
        if (state is PeacefulState) changeStateTo(AngryState(this))
        else changeStateTo(PeacefulState(this))
    }

    fun observe() = state.observe()

    private fun changeStateTo(newState: State) {
        state = newState
        state.onEnterState()
    }
}

fun main() {

    val alex = Mammoth()
    alex.observe()
    alex.timePasses()
    alex.observe()
    alex.timePasses()
    alex.observe()
    alex.timePasses()
    alex.timePasses()
    alex.observe()
}
