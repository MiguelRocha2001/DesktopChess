val PieceTypes = arrayOf('K','Q','R','B','N','P')

enum class Dir { FRONT, BACK, LEFT, RIGHT, FRONT_LEFT, FRONT_RIGHT, BACK_LEFT, BACK_RIGHT}
class SpecialMove()

data class Movement(val dir: Array<Dir>, val maxSteps: Int)

// Esta classe deve apenas ter as caracteristicas de cada peça como por exemplo a dforma como elas se podem deslocar mas não deve saber nada do tabuleiro.
// A classe Board é que deve mover as peças e verificar se a movimentção é valida
sealed class Piece()
class Pawn(): Piece()
class Knight(): Piece()
class Bishop(): Piece()
class Rook(): Piece()
class Queen(): Piece()
class King(): Piece()

/*
fun getAllPieces(): Array<Piece> {
    return arrayOf(Pawn(),Knight(),Bishop(),Rook(),Queen(),King())
}
 */

fun Piece.toStr(): String {
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