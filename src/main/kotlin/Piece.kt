
enum class Dir { FRONT, BACK, LEFT, RIGHT, FRONT_LEFT, FRONT_RIGHT, BACK_LEFT, BACK_RIGHT}
class SpecialMove()

data class Movement(val dir: Array<Dir>, val maxSteps: Int)

// Esta classe deve apenas ter as caracteristicas de cada peça como por exemplo a dforma como elas se podem deslocar mas não deve saber nada do tabuleiro.
// A classe Board é que deve mover as peças e verificar se a movimentção é valida
abstract class Piece()


fun Piece.toString(): String {
    return when(this) {
        is Pawn -> "P"
        is Knight -> "N"
        is Bishop -> "B"
        is Rook -> "R"
        is Queen -> "Q"
        else -> "K"
    }
}

fun Piece.getDirections(): Array<Dir> {
    return when(this) {
        is Pawn -> arrayOf(Dir.FRONT,Dir.FRONT_LEFT,Dir.FRONT_RIGHT)
        is Knight -> arrayOf(Dir.FRONT,Dir.BACK,Dir.LEFT,Dir.RIGHT)
        is Bishop -> arrayOf(Dir.FRONT_LEFT,Dir.FRONT_RIGHT,Dir.BACK_LEFT,Dir.BACK_RIGHT)
        is Rook -> arrayOf(Dir.FRONT,Dir.BACK,Dir.LEFT,Dir.RIGHT)
        is Queen -> Dir.values()
        else -> Dir.values()
    }
}

class Pawn(): Piece() {
    override fun toString() = "P"

    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (col != prev.col) return false
        if (line - prev.line > 2) return false
        return true
    }
}

class Knight(): Piece() {
    override fun toString() = "N"
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (kotlin.math.abs(line - prev.line) == 1 && kotlin.math.abs(col - prev.col) == 2) return true
        if (kotlin.math.abs(line - prev.line) == 2 && kotlin.math.abs(col - prev.col) == 1) return true
        return false
    }
}

class Bishop(): Piece() {
    override fun toString() = "B"
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (line - prev.line == kotlin.math.abs(col-prev.col)) return true
        return false
    }
}

class Rook(): Piece() {
    override fun toString() = "R"
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (line == prev.line || col == prev.col) return true
        return false
    }
}

class Queen(): Piece() {
    override fun toString() = "Q"
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (line == prev.line || col == prev.col) return true
        if (board[line][col] != null) return false
        if (line - prev.line == kotlin.math.abs(col-prev.col)) return true
        return false
    }
}

class King(): Piece() {
    override fun toString() = "K"
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (kotlin.math.abs(line - prev.line) <= 1 && kotlin.math.abs(col - prev.col) <= 1) return true
        return false
    }
}