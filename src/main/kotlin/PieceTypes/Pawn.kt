
/**
 * Checks if the given Move tried to move a Pawn in a valid direction.
 */
fun tryToMovePawn(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    // if the player chooses the same place
    if (move.newSquare.row === move.curSquare.row && move.newSquare.column === move.curSquare.column) return false
    if (checksDiagonalForward(move,board)) return true
    return checksForward(move,board)
}

/**
 * Checks if the given Move tried to move a Pawn in a valid diagonal direction.
 * Returns false if there inst already a piece there
 */
private fun checksDiagonalForward(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    //Pode ser escrito numa função é código repetido
    val player = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player
    var rowDif = move.newSquare.row.ordinal - move.curSquare.row.ordinal
    var colDif = move.newSquare.column.ordinal - move.curSquare.column.ordinal
    if (player===Board.Player.BLACK) {
        rowDif = -rowDif
        colDif = -colDif
    }

    // if the player tries to go forward-left
    if (colDif == -1 && rowDif == -1) {
        // if there's a Piece on the forward-left square
        if (player===Board.Player.WHITE && board[move.curSquare.row.ordinal+1][move.curSquare.column.ordinal-1] != null
            && board[move.curSquare.row.ordinal+1][move.curSquare.column.ordinal-1]!!.player===Board.Player.BLACK)
            return true
        if (player===Board.Player.BLACK && board[move.curSquare.row.ordinal-1][move.curSquare.column.ordinal+1] != null
            && board[move.curSquare.row.ordinal+1][move.curSquare.column.ordinal-1]!!.player===Board.Player.WHITE)
            return true
    }
    // if the player tries to go forward-right
    if (colDif == 1 && rowDif == -1) {
        // if there's a Piece on the forward-right
        if (player===Board.Player.WHITE && board[move.curSquare.row.ordinal + 1][move.curSquare.column.ordinal + 1] != null) return true
        if (player===Board.Player.BLACK && board[move.curSquare.row.ordinal - 1][move.curSquare.column.ordinal - 1] != null) return true
    }
    return false
}

/**
 * Checks if the given Move tried to move a Pawn in a valid forward direction.
 */
private fun checksForward(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player
    var rowDif = move.newSquare.row.ordinal - move.curSquare.row.ordinal
    var colDif = move.newSquare.column.ordinal - move.curSquare.column.ordinal
    if (player===Board.Player.BLACK) {
        rowDif = -rowDif
        colDif = -colDif
    }

    // if the player tries to go forward
    if (rowDif < 0 && colDif == 0) {
        // if the player tries to go forward 2 steps
        if (rowDif == -2) {
            val pawn = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.type as Pawn
            if (!pawn.hasPlayed) {
                if (board[move.newSquare.row.ordinal][move.newSquare.column.ordinal] != null) return false
                if (player === Board.Player.WHITE && board[move.curSquare.row.ordinal - 2][move.curSquare.column.ordinal] == null) return true
                if (player === Board.Player.BLACK && board[move.curSquare.row.ordinal + 2][move.curSquare.column.ordinal] == null) return true
            }
        }
        // if the player tries to go forward 1 step
        if (rowDif == -1) {
            // if there isn't already a piece there
            if (player === Board.Player.WHITE && board[move.curSquare.row.ordinal - 1][move.curSquare.column.ordinal] == null) return true
            if (player === Board.Player.BLACK && board[move.curSquare.row.ordinal + 1][move.curSquare.column.ordinal] == null) return true
        }
    }
    return false
}