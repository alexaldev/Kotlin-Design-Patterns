package behavioral

data class Employee(
    val no: Int,
    val name: String,
    val gender: Gender,
    val salary: Int,
    val projectName: String,
    val floorWorking: Floor,
    val dptt: Dpt
) {
    enum class Gender { Male, Female }
    enum class Floor { First, Second, Third }
    enum class Project { Nirvana, Flatbush, Backend }
    enum class Dpt { ENG, QA, HR, SUPPORT, IT, ADMIN }
}

interface Filter {
    fun apply(employees: List<Employee>): List<Employee>
}

// Available filters
class MaleFilter : Filter {
    override fun apply(employees: List<Employee>): List<Employee> {
        return employees.filter { it.gender == Employee.Gender.Male }
    }
}

class EngFilter : Filter {
    override fun apply(employees: List<Employee>): List<Employee> {
        return employees.filter { it.dptt == Employee.Dpt.ENG }
    }
}

class AndFilter(
    private val f1: Filter,
    private val f2: Filter
) : Filter {
    override fun apply(employees: List<Employee>): List<Employee> {
        return f2.apply(f1.apply(employees))
    }
}
class OrFilter(
    private val f1: Filter,
    private val f2: Filter
): Filter {
    override fun apply(employees: List<Employee>): List<Employee> {
        val f1Results = f1.apply(employees).toMutableList()
        val f2Results = f2.apply(employees)
        f1Results.addAll(f2Results)
        return f1Results.distinct()
    }
}

// Some Kotlin fun

val and: (List<Employee>, Filter, Filter) -> List<Employee> = { list, f1, f2 -> f1.apply(f2.apply(list))}

infix fun List<Employee>.filteredBy(f1: Filter): List<Employee> = f1.apply(this)

infix fun Filter.and(other: Filter): Filter = AndFilter(this, other)

fun main() {
    val skroutzEmployees = listOf(
        Employee(1, "Alex Allafi", Employee.Gender.Male, 1277, "Mobile-Core", Employee.Floor.Second, Employee.Dpt.IT),
        Employee(2, "Alex Allafi", Employee.Gender.Male, 1277, "Mobile", Employee.Floor.First, Employee.Dpt.ENG),
        Employee(3, "Alex Allafi", Employee.Gender.Female, 1277, "Katana", Employee.Floor.Second, Employee.Dpt.SUPPORT),
        Employee(4, "Alex Allafi", Employee.Gender.Female, 1277, "Mobile-Core", Employee.Floor.First, Employee.Dpt.ADMIN),
        Employee(5, "Alex Allafi", Employee.Gender.Male, 1277, "Mobile-Core", Employee.Floor.Second, Employee.Dpt.ADMIN),
        Employee(6, "Alex Allafi", Employee.Gender.Female, 1277, "Mobile-Core", Employee.Floor.Third, Employee.Dpt.IT),
        Employee(7, "Alex Allafi", Employee.Gender.Male, 1277, "Mobile-Core", Employee.Floor.Third, Employee.Dpt.HR),
        Employee(8, "Alex Allafi", Employee.Gender.Female, 1277, "Mobile-Core", Employee.Floor.Third, Employee.Dpt.IT),
    )

    val males = MaleFilter()
    val atEngDepartment = EngFilter()

    println(skroutzEmployees filteredBy (males and atEngDepartment))
}