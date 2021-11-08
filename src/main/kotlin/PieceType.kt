
/**
 * Cada peça vai receber uma tentativa de jogada, ou seja, com as coordenadas da jogada e o tabuleiro com as outras peças e DIZER se essa jogada foi valida
 */
sealed class PieceType()
class Pawn(val hasPlayed: Boolean): PieceType()
class Knight(): PieceType()
class Bishop(): PieceType()
class Rook(): PieceType()
class Queen(): PieceType()
class King(): PieceType()

fun PieceType.canItMove(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    return when(this) {
        is Pawn -> tryToMovePawn(move,board)
        //is Knight -> tryToMoveKnight(move)
        //is Bishop -> tryToMoveBishop(move)
        //is Rook -> tryToMoveRook(move)
        // is Queen -> tryToMoveQueen(move)
        else -> tryToMovePawn(move,board)
    }
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
        'P' -> Pawn(false)
        'N' -> Knight()
        'B' -> Bishop()
        'R' -> Rook()
        'Q' -> Queen()
        'K' -> King()
        else -> null
    }
