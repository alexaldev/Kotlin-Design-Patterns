package behavioral

abstract class Modem {
    abstract fun accept(modemVisitor: ModemVisitor)
}

class Zoom: Modem() {
    override fun accept(modemVisitor: ModemVisitor) {
        if (modemVisitor is ZoomVisitor) {
            modemVisitor.visit(this@Zoom)
        } else {
            println("Only a zoom visitor can visit Zoom modem")
        }
    }
}
class Hayes: Modem() {
    override fun accept(modemVisitor: ModemVisitor) {
        if (modemVisitor is HayesVisitor) {
            modemVisitor.visit(this@Hayes)
        } else {
            println("Only a hayes visitor can visit Hayes modem")
        }
    }
}
interface ModemVisitor
interface HayesVisitor: ModemVisitor {
    fun visit(hayes: Hayes)
}
interface ZoomVisitor: ModemVisitor {
    fun visit(zoom: Zoom)
}
interface AllModemVisitor: HayesVisitor, ZoomVisitor
class ConfigureForDosVisitor: AllModemVisitor {
    override fun visit(hayes: Hayes) {
        println("$hayes used with Dos configurator")
    }
    override fun visit(zoom: Zoom) {
        println("$zoom used with Dos configurator")
    }
}

class ConfigureForUnixVisitor: ZoomVisitor {
    override fun visit(zoom: Zoom) {
        println("$zoom used with unix configurator")
    }
}

fun main() {
    // Configurators
    val conUnix = ConfigureForUnixVisitor()
    val conDos = ConfigureForDosVisitor()
    // Modems
    val hayes = Hayes()
    val zoom = Zoom()

    hayes.accept(conDos)
    hayes.accept(conUnix)
    zoom.accept(conDos)
    zoom.accept(conUnix)
}