
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

fun tryToMovePawn(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    // if the player chooses the same place
    if (move.target.row === move.cur.row && move.target.column === move.cur.column) return false
    // for the WHITE player
    if (board[move.cur.row.n][move.cur.column.n]!!.player === Board.Player.WHITE) {
        // if there inst a Piece on the forward-left
        if (move.target.column < move.cur.column) {
            // if the player tries to go foward-left
            if (board[move.cur.row.n + 1][move.cur.column.n - 1] == null) return false
        }
        // if the player tries to go foward-right
        if (move.target.column > move.cur.column) {
             // if there inst a Piece on the foward-right
             if (board[move.cur.row.n + 1][move.cur.column.n + 1] == null) return false
        }
        // if the player tries to go foward
        if (move.target.row > move.cur.row && move.target.column === move.cur.column) {
            // if the player tries to go foward more than 2 times
            if (move.target.row.n - move.cur.row.n > 2) return false
            // if the player tries to go foward 2 times
            if (move.target.row.n - move.cur.row.n == 2) {
                val pawn = board[move.cur.row.n][move.cur.column.n] as Pawn
                if (pawn.hasPlayed) return false
            }
            // if the player tries to go foward 1 time
            if (move.target.row.n - move.cur.row.n == 1) {
                if (board[move.cur.row.n + 1][move.cur.column.n] != null) return false
            }
        }
    }
    return true
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
