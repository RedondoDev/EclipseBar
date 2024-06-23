package structure

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.time.LocalDate
import java.time.LocalTime

data class Product(
    var name: String,
    var price: Double,
    var pvp: Double,
    var stock: Int,
    var photo: Photo,
    var type: String
)

data class Photo(
    var ruta: String,
    var desctiption: String
)

val productList: MutableList<Product> = mutableStateListOf(
    Product("Coca-Cola", 1.5, 2.2, 5, Photo("prodImgs/Fanta.png", "sfsdf"), "Refrescos"),
    Product("Fanta", 2.5, 3.0, 1, Photo("prodImgs/Fanta.png", "sfsdf"), "Cocteles"),
    Product("Aquarius", 1.9, 2.5, 3, Photo("prodImgs/Fanta.png", "sfsdf"), "Comida"),
    Product("Cerveza", 2.2, 2.5, 4, Photo("prodImgs/Fanta.png", "sfsdf"), "Refrescos"),
    Product("Vino", 3.5, 4.0, 6, Photo("prodImgs/Fanta.png", "sfsdf"), "Refrescos"),
    Product("Café", 3.1, 3.2, 7, Photo("prodImgs/Fanta.png", "sfsdf"), "Cocteles"),
    Product("Té", 2.0, 3.2, 8, Photo("prodImgs/Fanta.png", "sfsdf"), "Cocteles"),
    Product("Zumo", 1.3, 3.2, 9, Photo("prodImgs/Fanta.png", "sfsdf"), "Comida"),
    Product("Leche", 1.7, 3.2, 1, Photo("prodImgs/Fanta.png", "sfsdf"), "Comida"),
    Product("Agua", 2.4, 3.2, 3, Photo("prodImgs/Fanta.png", "sfsdf"), "Comida"),
    Product("Sprite", 1.1, 3.2, 4, Photo("prodImgs/Fanta.png", "sfsdf"), "Comida"),
    Product("7up", 2.1, 3.2, 1, Photo("prodImgs/Fanta.png", "sfsdf"), "Comida"),
    Product("Pepsi", 2.7, 3.2, 1, Photo("prodImgs/Fanta.png", "sfsdf"), "Comida"),
)

data class ProdInOrder(
    var product: Product,
    var quantity: Int
)

data class Order(
    val number: Int,
    val date: LocalDate,
    val time: LocalTime,
    val amount: Double,
    val waiter: String
)

val time = LocalTime.now()
val orders: SnapshotStateList<Order> =
    mutableStateListOf(
        Order(1, date(), time, amount(), "Toño"),
        Order(2, date(), time, amount(), "Camarero 1"),
        Order(3, date(), time, amount(), "Toño"),
        Order(4, date(), time, amount(), "Camarero 2"),
        Order(5, date(), time, amount(), "Toño"),
        Order(6, date(), time, amount(), "Toño"),
        Order(7, date(), time, amount(), "Camarero 3"),
        Order(8, date(), time, amount(), "Camarero 2"),
        Order(9, date(), time, amount(), "Camarero 2"),
        Order(10, date(), time, amount(), "Camarero 3"),
        Order(11, date(), time, amount(), "Camarero 1"),
    )

data class Filter(
    val orderNumber: IntRange = (0..orders.maxBy { it.number }.number),
    val dateRange: ClosedRange<LocalDate> = LocalDate.of(2022, 8, 10)..LocalDate.now(),
    val waiter: String = "Todos",
    val amountRange: ClosedFloatingPointRange<Float> = (0.0f..1000.00f)
)

//For Debug
fun amount(): Double {
    return (1..200).random().toDouble()
}

//For Debug
fun date(): LocalDate {
    val year = 2024
    val month = (1..6).random()
    val day = (1..28).random()
    return LocalDate.of(year, month, day)
}