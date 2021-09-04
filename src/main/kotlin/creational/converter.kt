package creational

open class Converter<T, U>(
    val fromDto: (T) -> U,
    val fromEntity: (U) -> T
) {
    fun convertFromDto(dto: T): U = fromDto(dto)
    fun convertFromEntity(entity: U): T = fromEntity(entity)
    fun convertFromDtos(dtos: Collection<T>): List<U> = dtos.map(fromDto)
    fun convertFromEntities(entities: Collection<U>): List<T> = entities.map(fromEntity)
}

class Person(val id: Long, val firstName: String, val lastName: String)
class PersonDto(val id: Long, val fullName: String)

class PersonConverter : Converter<Person, PersonDto>(fromPerson, fromPersonDto)

val fromPerson: (Person) -> PersonDto = { p: Person -> PersonDto(p.id, p.firstName + " " + p.lastName) }
val fromPersonDto: (PersonDto) -> Person = { personDto ->
    Person(
        personDto.id,
        personDto.fullName.substringBefore(" "),
        personDto.fullName.substringAfter(" ")
    )
}

fun main() {
}