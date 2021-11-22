package chess.model

class Square(val column: Column, val row: Row) {
    companion object {
        private var lineCount = 1

        /**
         * Returns all the possible Squares in a list of Squares.
         */
        val values = Array<Square>(Column.values().size * Row.values().size) { it ->
            val square = Square(Column.values[if (it < Column.values.size-1) it else it % Column.values.size],Row.values[lineCount-1])
            // when arrives to the final Column, increments the counter
            if (it >= Column.values().size-1 && it % Column.values.size == Column.values.size-1) ++lineCount
            square
        }
    }
    override fun toString() = "" + this.column.letter + this.row.digit
}

fun String.toSquareOrNull(): Square? {
    if (this.length > 2) return null
    val col = this[0].toColumnOrNull()
    val row = this[1].toRowOrNull()
    return if (col != null && row != null) Square(col, row) else null
}