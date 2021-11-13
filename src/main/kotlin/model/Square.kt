package chess.model

class Square(val column: Column, val row: Row) {
    companion object {
        val values = values()

        fun values(): List<Square> {
            val list = emptyList<Square>().toMutableList()
<<<<<<< Updated upstream
            for (col in Column.values()) {
                for (row in Row.values()) {
=======
            for (row in Row.values()) {
                for (col in Column.values()) {
>>>>>>> Stashed changes
                    list += Square(col,row)
                }
            }
            return list
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