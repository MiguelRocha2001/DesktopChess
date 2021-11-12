package chess.model

enum class Column(val letter: Char) {
    A('a'), B('b'), C('c'), D('d'), E('e'), F('f'), G('g'), H('h');
}

fun Char.toColumnOrNull(): Column? {
    val cols: Array<Column> = Column.values()
    val col = cols.filter { column -> column.letter == this}
    if (col.isEmpty()) return null
    return col[0]
}

fun Int.toColumn() = Column.values()[this]

fun main() {
    print(Column.valueOf('j'.toString()))
}