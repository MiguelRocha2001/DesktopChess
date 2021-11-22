package chess.model

enum class Row(val digit: Char) {
    EIGHT('8'), SEVEN('7'), SIX('6'), FIVE('5'), FOUR('4'), THREE('3'), TWO('2'), ONE('1');
    companion object {
        val values = Array<Row>(Column.values().size) { it ->
            Row.values()[it]
        }
    }
}

fun Char.toRowOrNull(): Row? {
    val rows: Array<Row> = Row.values()
    val row = rows.filter { row -> row.digit == this}
    if (row.isEmpty()) return null
    return row[0]
}

fun Int.toRow() = Row.values()[this]

fun main() {

}