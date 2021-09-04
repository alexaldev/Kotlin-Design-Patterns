package creational

class Customer(
    val id: Long,
    val name: String
)

class CustomerRegistry() {

    private val customers = hashMapOf<Long, Customer>()

    public fun addCustomer(c: Customer) {
        customers.put(c.id, c)
    }

    public fun getCustomer(id: Long) = customers[id]
}

fun main() {

    val customerRegistry = CustomerRegistry()
    customerRegistry.addCustomer(Customer(1L, "Alex"))
    customerRegistry.addCustomer(Customer(2L, "Chris"))
    customerRegistry.addCustomer(Customer(3L, "John"))

    val alex = customerRegistry.getCustomer(1L)
    val chris = customerRegistry.getCustomer(2L)
}