import chess.model.*
import model.Board
import model.Move
import model.Player


/**
 * Cada peça vai receber uma tentativa de jogada, ou seja, com as coordenadas da jogada e o tabuleiro com as outras peças e DIZER se essa jogada foi valida
 */
sealed class PieceType
class Pawn: PieceType() {
    var hasPlayed: Boolean = false
}

var allAvailablePositions = mutableListOf<Square>()

class Knight: PieceType()
class Bishop: PieceType()
class Rook: PieceType()
class Queen: PieceType()
class King: PieceType()

//Get all squares possible to move to, from one piece
private fun getAllMoves(curSquare:Square, board: Array<Array<Board.Piece?>>, pieceType: PieceType): List<Square> {
    val player = board[curSquare.row.ordinal][curSquare.column.ordinal]!!.player //Current player
    allAvailablePositions = mutableListOf()
    var newRow = curSquare.row.ordinal - 1
    var newCol = curSquare.column.ordinal
    if(pieceType !is Bishop) {
        //No black Pawns
        if(!(player=== Player.BLACK && pieceType is Pawn) ){
            if(!(board[newRow][newCol] != null && pieceType is Pawn)) {
                //Move Up
                while (newRow >= Row.EIGHT.ordinal && tryToMove(curSquare, board, Square(curSquare.column.ordinal.toColumn(), newRow.toRow()), pieceType)) {
                    if (pieceType is King || (pieceType is Pawn && newRow != Row.THREE.ordinal && board[newRow][newCol] == null)) break
                    newRow--
                }
            }
        }
        //No white pawns
        if(!(player===Player.WHITE && pieceType is Pawn)) {
            //Move Down
            newRow = curSquare.row.ordinal + 1
            while (newRow <= Row.ONE.ordinal && tryToMove(curSquare, board, Square(curSquare.column.ordinal.toColumn(), newRow.toRow()), pieceType)) {
                if(pieceType is King || (pieceType is Pawn && newRow != Row.SIX.ordinal && board[newRow][newCol] != null)) break
                newRow++
            }
        }
    }
    newCol = curSquare.column.ordinal + 1
    if(!(pieceType is Bishop || pieceType is Pawn)) {
        //Move Right
        while(newCol <= Column.H.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), curSquare.row.ordinal.toRow()), pieceType)) {
            if(pieceType is King) break
            newCol++
        }
        //Move Left
        newCol = curSquare.column.ordinal - 1
        while(newCol >= Column.A.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), curSquare.row.ordinal.toRow()), pieceType)) {
            if(pieceType is King) break
            newCol--
        }
    }
    if(pieceType !is Rook && !(player===Player.BLACK && pieceType is Pawn)) {
        //Move Up_Right
        newRow = curSquare.row.ordinal - 1
        newCol = curSquare.column.ordinal + 1
        if(newCol in 0..7) { //Check if Col is not out of bounds from the board
            if (!(pieceType is Pawn && (board[newRow][newCol] == null || player === board[newRow][newCol]!!.player))) {
                while (newRow >= Row.EIGHT.ordinal && newCol <= Column.H.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), newRow.toRow()), pieceType)) {
                    if (pieceType is King) break
                    newRow--
                    newCol++
                }
            }
        }
        //Move Up_Left
        newRow = curSquare.row.ordinal - 1
        newCol = curSquare.column.ordinal - 1
        if(newCol in 0..7) { //Check if Col is not out of bounds from the board
            if (!(pieceType is Pawn && (board[newRow][newCol] == null || player === board[newRow][newCol]!!.player))) {
                while (newRow >= Row.EIGHT.ordinal && newCol >= Column.A.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), newRow.toRow()), pieceType)) {
                    if (pieceType is King) break
                    newRow--
                    newCol--
                }
            }
        }
    }
    if(pieceType !is Rook && !(player===Player.WHITE && pieceType is Pawn)) {
        //Move Down_Right
        newRow = curSquare.row.ordinal + 1
        newCol = curSquare.column.ordinal + 1
        if(newCol in 0..7) { //Check if Col is not out of bounds from the board
            if (!(pieceType is Pawn && (board[newRow][newCol] == null || player === board[newRow][newCol]!!.player))) {
                while (newRow <= Row.ONE.ordinal && newCol <= Column.H.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), newRow.toRow()), pieceType)) {
                    if (pieceType is King) break
                    newRow++
                    newCol++
                }
            }
        }
        //Move Down_Left
        newRow = curSquare.row.ordinal + 1
        newCol = curSquare.column.ordinal - 1
        if(newCol in 0..7) { //Check if Col is not out of bounds from the board
            if (!(pieceType is Pawn && (board[newRow][newCol] == null || player === board[newRow][newCol]!!.player))) {
                while (newRow <= Row.ONE.ordinal && newCol >= Column.A.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), newRow.toRow()), pieceType)) {
                    if (pieceType is King) break
                    newRow++
                    newCol--
                }
            }
        }
    }
    return allAvailablePositions
}

//See if the play is possible
private fun tryToMove(curSquare:Square, board: Array<Array<Board.Piece?>>, newSquare:Square, piece:PieceType):Boolean {
    val move = Move(piece,curSquare,newSquare)
    val player = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player

    //There's a piece in the way
    if(board[move.newSquare.row.ordinal][move.newSquare.column.ordinal] == null) {
        allAvailablePositions.add(newSquare)
        return true
    }
    //Move to Enemy square
    if(board[move.newSquare.row.ordinal][move.newSquare.column.ordinal]!!.player != player) {
        allAvailablePositions.add(newSquare)
        //Stops at enemy piece
        return false
    }
    return false
}

fun PieceType.getAllMoves(move: Move, board: Array<Array<Board.Piece?>>): List<Square> {
    //tries to move piece
    return when(this) {
        //Knights moves are way too different from the other pieces
        is Knight -> getAllMovesKnight(move, board)
        else -> getAllMoves(move.curSquare, board,move.piece)
    }
}

fun getAllMovesKnight(move: Move, board: Array<Array<Board.Piece?>>): List<Square> {
    val player = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player
    val offsetRow = intArrayOf(2, 1, -1, -2, -2, -1, 1, 2)
    val offsetColumn = intArrayOf(1, 2, 2, 1, -1, -2, -2, -1)
    allAvailablePositions = mutableListOf()

    // if the player chooses the same place
    if (move.newSquare.row === move.curSquare.row && move.newSquare.column === move.curSquare.column) return allAvailablePositions

    for (i in 0..7) {
        if(move.curSquare.row.ordinal + offsetRow[i] in 0..7 && move.curSquare.column.ordinal + offsetColumn[i] in 0..7 ) {
            val row = move.curSquare.row.ordinal + offsetRow[i]
            val col = move.curSquare.column.ordinal + offsetColumn[i]
            val square = Square(col.toColumn(),row.toRow())
            if (board[move.curSquare.row.ordinal + offsetRow[i]][move.curSquare.column.ordinal + offsetColumn[i]] !== null) {
                if (player === Player.WHITE && board[move.curSquare.row.ordinal + offsetRow[i]][move.curSquare.column.ordinal + offsetColumn[i]]!!.player === Player.BLACK)
                    allAvailablePositions.add(square)

                if (player === Player.BLACK && board[move.curSquare.row.ordinal+offsetRow[i]][move.curSquare.column.ordinal+ offsetColumn[i]]!!.player === Player.WHITE)
                    allAvailablePositions.add(square)
            }
            else
                allAvailablePositions.add(square)

        }
    }
    return allAvailablePositions
}

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