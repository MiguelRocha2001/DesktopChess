
/**
 * Cada peça vai receber uma tentativa de jogada, ou seja, com as coordenadas da jogada e o tabuleiro com as outras peças e DIZER se essa jogada foi valida
 */
sealed class PieceType()
class Pawn(): PieceType()
class Knight(): PieceType()
class Bishop(): PieceType()
class Rook(): PieceType()
class Queen(): PieceType()
class King(): PieceType()

fun PieceType.canItMove(move: Board.Move, board: Array<Array<Pair<PieceType,Player>?>): Boolean {
    return when(this) {
        is Pawn -> tryToMovePawn(move)
        is Knight -> tryToMoveKnight(move)
        is Bishop -> tryToMoveBishop(move)
        is Rook -> tryToMoveRook(move)
        is Queen -> tryToMoveQueen(move)
        else ->
    }
}

fun tryToMovePawn(move: Board.Move, board: Array<Array<Pair<PieceType,Player>?>): Boolean {

}

/*
fun getAllPieces(): Array<Piece> {
    return arrayOf(Pawn(),Knight(),Bishop(),Rook(),Queen(),King())
}
 */

fun PieceType.toStr() =
    when(this) {
        is Pawn -> "P"
        is Knight -> "N"
        is Bishop -> "B"
        is Rook -> "R"
        is Queen -> "Q"
        else -> "K"
    }

fun getPieceType(type: Char) =
    when(type) {
        'P' -> Pawn()
        'N' -> Knight()
        'B' -> Bishop()
        'R' -> Rook()
        'Q' -> Queen()
        'K' -> King()
        else -> null
    }

fun PieceType.getDirections(): Array<Dir> {
    return when(this) {
        is Pawn -> arrayOf(Dir.FRONT,Dir.FRONT_LEFT,Dir.FRONT_RIGHT)
        is Knight -> arrayOf(Dir.FRONT,Dir.BACK,Dir.LEFT,Dir.RIGHT)
        is Bishop -> arrayOf(Dir.FRONT_LEFT,Dir.FRONT_RIGHT,Dir.BACK_LEFT,Dir.BACK_RIGHT)
        is Rook -> arrayOf(Dir.FRONT,Dir.BACK,Dir.LEFT,Dir.RIGHT)
        is Queen -> Dir.values()
        else -> Dir.values()
    }
}