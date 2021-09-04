package creational

import java.util.concurrent.atomic.AtomicInteger

class Oliphunt {

    private val id: Int = counter.incrementAndGet()

    override fun toString() = "ID: $id"
    init {
        // Emulate heavy initialization process
        try {
            Thread.sleep(1000L)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    companion object {
        val counter = AtomicInteger(0)
    }
}

abstract class ObjectPool<T> {
    private val available = hashSetOf<T>()
    private val used = hashSetOf<T>()

    protected abstract fun create(): T

    public fun take(): T {
        if (available.isEmpty()) available.add(create())
        with(available.iterator().next()) {
            available.remove(this)
            used.add(this)
            println("Checking out object withj ")
            return this
        }
    }

    public fun returnBack(t: T) {
        used.remove(t)
        available.add(t)
    }

    public override fun toString(): String {
        return "Pool: available=${available.size}, used=${used.size}"
    }
}

class OliphuntPool: ObjectPool<Oliphunt>() {
    override fun create() = Oliphunt()
}

fun main() {
    val pool = OliphuntPool()
    val o1 = pool.take()
    val o2 = pool.take()
    val o3 = pool.take()
    val o4 = pool.take()

}