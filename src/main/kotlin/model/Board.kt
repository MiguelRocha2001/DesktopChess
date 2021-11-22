package model

import Bishop
import King
import Knight
import Pawn
import PieceType
import Queen
import Rook
import chess.model.*
import getAllMoves
import getPieceType
import toStr
import java.util.*

class Board {
    /**
     * Used to bound a piece to a player in the [boardArr]
     */
    class Piece(val type: PieceType, val player: Player)

    private val LINES = 8
    private val COLS = 8
    private val boardArr: Array<Array<Piece?>>
    private val finished: Boolean

    /**
     * Saves the position of the king throughout the game (It is going to be used for check and checkmate)
     */
    private val whiteKingPosition: Square
    private val blackKingPosition: Square

    /**
     * To iniciate the Game
     */
    constructor() {
        finished = false
        boardArr = Array(LINES) { Array(COLS) { null } }
        // updates the white and black King positions
        init()
        whiteKingPosition = Square(Column.E,Row.ONE)
        blackKingPosition = Square(Column.E,Row.EIGHT)
    }

    /**
     * To change the [boardArr] state/make movements.
     */
    constructor(board: Board, boardArr: Array<Array<Piece?>>) {
        this.boardArr = boardArr
        finished = board.finished
        var whiteKingPosition: Square? = null
        var blackKingPosition: Square? = null
        Square.values.forEach {square ->
            val piece = boardArr[square.row.ordinal][square.column.ordinal]
            if (piece != null) {
                if (piece.type is King) {
                    if (piece.player === Player.WHITE)
                        whiteKingPosition = square
                    else
                        blackKingPosition = square
                }
            }
        }
        // should never be null
        this.whiteKingPosition = whiteKingPosition!!
        this.blackKingPosition = blackKingPosition!!
    }

    /**
     * To change the [endOfGame] state
     */
    private constructor(board: Board, boardArr: Array<Array<Piece?>>, endOfGame: Boolean) {
        whiteKingPosition = board.whiteKingPosition
        blackKingPosition = board.blackKingPosition
        this.boardArr = boardArr
        finished = endOfGame
    }

    /**
     * Initiates the board
     */
    private fun init() {
        initPlayer(Player.WHITE)
        initPlayer(Player.BLACK)
    }

    /**
     * Initiate player pieces
     */
    private fun initPlayer(player: Player) {
        var firstRow = Row.ONE.ordinal
        var currCol = Column.A.ordinal
        var secondRow = Row.TWO.ordinal
        if (player == Player.BLACK) {
            firstRow = Row.EIGHT.ordinal
            secondRow = Row.SEVEN.ordinal
        }
        boardArr[firstRow][currCol++] = Piece(Rook(), player)
        boardArr[firstRow][currCol++] = Piece(Knight(), player)
        boardArr[firstRow][currCol++] = Piece(Bishop(), player)
        boardArr[firstRow][currCol++] = Piece(Queen(), player)
        boardArr[firstRow][currCol++] = Piece(King(), player)
        boardArr[firstRow][currCol++] = Piece(Bishop(), player)
        boardArr[firstRow][currCol++] = Piece(Knight(), player)
        boardArr[firstRow][currCol] = Piece(Rook(), player)
        for (i in 0..7)
            boardArr[secondRow][i] = Piece(Pawn(), player)
    }

    /**
     * Converts the current state of the game in a String
     * Square.values.joinToString
     */
    fun toStringTest(): String {
        var str = ""
        Square.values.forEach { square ->
            val piece = boardArr[square.row.ordinal][square.column.ordinal]
            if (piece != null) {
                var aux = piece.type.toStr()
                if (piece.player == Player.BLACK)
                    aux = aux.lowercase(Locale.getDefault())
                str += aux
            } else str += ' '
        }
        return str
    }

    /**
     * The chess board as a String
     */
    override fun toString(): String {
        var str = "    a b c d e f g h\n   -----------------\n"
        str += "" + Square.values[0].row.digit + " |"
        var oldSquare: Square? = null
        Square.values.forEach { square ->
            val piece = boardArr[square.row.ordinal][square.column.ordinal]
            if (oldSquare != null && square.row.ordinal == oldSquare!!.row.ordinal + 1) {
                str += " |\n"
                str += "" + square.row.digit + " |"
            }
            if (piece != null) {
                var aux = piece.type.toStr()
                if (piece.player == Player.BLACK)
                    aux = aux.lowercase(Locale.getDefault())
                str += " " + aux
            } else str += "  "
            oldSquare = square
        }
        str += " |\n   -----------------"
        return str
    }

    /**
     * Used to retrieve the current state of the game hold in the database
     * Makes the move given in the [move] without checking if the move is possible.
     */
    fun makeMoveWithCorrectString(move: String): Board {
        val currSquare = move.substring(1, 3).toSquareOrNull()
        val newSquare = move.substring(3, 5).toSquareOrNull()
        //val move = Move(getPieceType(move[0])!!,Square(currCol,currRow),Square(newCol,newRow))
        val piece = boardArr[currSquare!!.row.ordinal][currSquare.column.ordinal]
        val newBoard = boardArr.clone()
        boardArr[currSquare.row.ordinal][currSquare.column.ordinal] = null
        newBoard[newSquare!!.row.ordinal][newSquare.column.ordinal] = piece
        return Board(this,newBoard, finished)
    }

    /**
     * Used to make a move with a given String [str].
     * Needs also the [curPlayer] to check if the move is possible.
     * Always returns the given [str] but always complete, in cases where the given [str] is not complete.
     */
    fun makeMove(str: String, curPlayer: Player = Player.WHITE): Pair<Board,String>? {
        if (finished) return null
        val move = toMoveOrNull(str, curPlayer) ?: return null
        val newBoard = makeMove(move) ?: return null
        return Pair(newBoard,move.toString())
    }

    /**
     * Transforms a given [str] in a Move dataType to facilitate the operation in the makeMove().
     * Also checks if the [str] is incomplete and tries to reconstruct the complete [str].
     * Finally, checks if the give [str] represents a valid piece.
     */
    private fun toMoveOrNull(str: String, curPlayer: Player): Move? {
        val cmd = str.trim()
        val pieceType = getPieceType(cmd[0])
        var currSquare: Square? = null
        val newSquare: Square?
        // omitting currentSquare
        if (str.length == 3) {
            newSquare = cmd.substring(1, 3).toSquareOrNull()
            if (newSquare == null) return null
            currSquare = getOmittedCurrentSquare(newSquare, curPlayer)
        }
        else {
            currSquare = cmd.substring(1, 3).toSquareOrNull()
            newSquare = cmd.substring(3, 5).toSquareOrNull()
        }

        if (currSquare == null || newSquare == null || pieceType == null) return null
        val move = Move(pieceType, currSquare, newSquare)
        if (!isValidSquare(move, curPlayer)) return null
        return move
    }

    // TODO definir retornar uma mensagem
    private fun getOmittedCurrentSquare(newSquare: Square, curPlayer: Player): Square? {
        var counter = 0
        var currentSquare: Square? = null
        // tries to find a valid corespondicy
        Square.values.forEach { square ->
            val piece = boardArr[square.row.ordinal][square.column.ordinal]
            if (piece != null && piece.player == curPlayer) {
                val possibleSquares = piece.type.getAllMoves(Move(piece.type,square,newSquare),boardArr)
                if (possibleSquares.any{it.row == newSquare.row && it.column == newSquare.column}) {
                    currentSquare = square
                    ++counter
                }
            }
        }
        // there's ambiguity
        if (counter > 1) return null
        // no correspondicy found
        if (counter == 0) return null
        return currentSquare
    }


    /**
     * Checks if there's actually a Piece in the given Square
     * Checks also if the given Move is correct for the current player.
     */
    private fun isValidSquare(move: Move, curPlayer: Player): Boolean {
        // verifies if theres a piece in currentSquare
        val piece = boardArr[move.curSquare.row.ordinal][move.curSquare.column.ordinal] ?: return false
        // verifies if the piece type of the choses square is the one in the str command
        if (piece.type.toStr() != move.piece.toStr()) return false
        if (piece.player != curPlayer) return false
        return true
    }

    /**
     * Checks if the [move] is valid and if so, aplies the given [move].
     * Returns the new Board if the [move] was valid or null.
     */
    private fun makeMove(move: Move): Board? {
        val pos = move.piece.getAllMoves(move, boardArr)
        if (!pos.any {
                it.row == move.newSquare.row && it.column == move.newSquare.column
            }) return null
        val piece = boardArr[move.curSquare.row.ordinal][move.curSquare.column.ordinal]
        val newBoardArr = boardArr.clone()
        boardArr[move.curSquare.row.ordinal][move.curSquare.column.ordinal] = null
        newBoardArr[move.newSquare.row.ordinal][move.newSquare.column.ordinal] = piece
        return Board(this, newBoardArr)
    }
    /*
    private fun isInCheck(move: Move): Boolean {
        val piece = boardArr[move.curSquare.row.ordinal][move.curSquare.column.ordinal]
        val player = boardArr[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player
        val isInCheckMove = Move(move.piece,move.newSquare,move.newSquare)
        val allMoves = isInCheckMove.piece.getAllMoves(isInCheckMove,boardArr)
        if(player == Player.WHITE) {
            if (!allMoves.any {
                    it.row == blackKingPosition.row && it.column == blackKingPosition.column
                }) return false
        }
        else
            if (!allMoves.any {
                    it.row == whiteKingPosition.row && it.column == whiteKingPosition.column
                }) return false
        return true
    }*/

    /*private fun isInCheckMate(move: Move):Boolean {
        val player = boardArr[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player
        if(player == Player.WHITE) {
            val blackKingMove = Piece(,blackKingPosition,blackKingPosition) //Criar uma peça para calcular as posições possíveis para o rei se mexer
            val allBlackKingMoves = getAllMoves(blackKingPosition,boardArr)
        }
        else{
            val allWhiteKingMoves = getAllMoves(whiteKingPosition,boardArr)
        }
    }*/
}

