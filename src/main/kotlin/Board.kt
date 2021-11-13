<<<<<<< Updated upstream
import chess.model.Column
import chess.model.Square
=======
import chess.model.*
>>>>>>> Stashed changes
import java.util.*

class Board {
    companion object {
        private var currentPlayer = false
    }
    enum class Player { WHITE, BLACK;
        fun advance() = if (this === WHITE) BLACK else WHITE
    }

    class Piece(val type: PieceType, val player: Player)
    class Move(val piece: PieceType, val curSquare: Square, val newSquare: Square)

    private val LINES = 8
    private val COLS = 8
    private val board: Array<Array<Piece?>>

    constructor() {
        board = Array(LINES) { Array(COLS) { null } }
        init()
    }

    private constructor(board: Array<Array<Piece?>>) {
        this.board = board
    }

    /**
     * Initiates the board
     */
    private fun init() {
        initPlayer(Player.WHITE)
        initPlayer(Player.BLACK)
    }

    private fun initPlayer(player: Player) {
        var firstRow = Row.ONE.ordinal
        var currCol = Column.A.ordinal
        var secondRow = Row.TWO.ordinal
        if (player == Player.BLACK){
            firstRow = Row.EIGHT.ordinal
            secondRow = Row.SEVEN.ordinal
        }
        board[firstRow][currCol++] = Piece(Rook(),player)
        board[firstRow][currCol++] = Piece(Knight(),player)
        board[firstRow][currCol++] = Piece(Bishop(), player)
        board[firstRow][currCol++] = Piece(Queen(), player)
        board[firstRow][currCol++] = Piece(King(), player)
        board[firstRow][currCol++] = Piece(Bishop(), player)
        board[firstRow][currCol++] = Piece(Knight(), player)
        board[firstRow][currCol] = Piece(Rook(), player)
        for (i in 0..7)
            board[secondRow][i] = Piece(Pawn(),player)
    }

    /**
     * Converts the current state of the game in a String
     * Square.values.joinToString
     */
    override fun toString(): String {
        var str = ""
        for (square in Square.values) {
            val piece = board[square.row.ordinal][square.column.ordinal]
            if (piece != null) {
                var aux = piece.type.toStr()
                if (piece.player == Player.BLACK)
                    aux = aux.lowercase(Locale.getDefault())
                str += aux
            } else str += ' '
        }
        return str
    }

<<<<<<< Updated upstream
=======
    fun toStr(): String {
        var str = "  a  b  c  d  e  f  g  h \n  ------------------------- \n"
        for (square in Square.values) {
            val piece = board[square.row.ordinal][square.column.ordinal]
            str += " "
            if (piece != null) {
                var aux = piece.type.toStr()
                if (piece.player == Player.BLACK)
                    aux = aux.lowercase(Locale.getDefault())
                str += aux
            } else str += ' '
            // TODO -> add a new line '\n' to [str] for every line iterated
        }
        str += "  -------------------------"
        return str
    }

>>>>>>> Stashed changes
    /**
     * Durante o jogo guarda-se os estados do jogo através de uma lista de Move().
     * Na base de dados guarda-se as jogadas ocurridas (sempre válidas) no tipo String
     */


    /**
     * Recebe uma string do servidor com as jogadas sempre validas e aplica as jogadas todas guadradads no servidor ate chegar ao estado corrente
     * Utilizado mais para fazer jogadas para repor o jogo atraves das strings que estao guardadas no servidor
     */
    fun makeMoveWithCorrectString(move: String): Board {
        val currCol = move[1] - 'a'
        val currRow = move[2] - '1'
        val newCol = move[3] - 'a'
        val newRow = move[4] - '1'
        //val move = Move(getPieceType(move[0])!!,Square(currCol,currRow),Square(newCol,newRow))
        val piece = board[currRow][currCol]
        val newBoard = board.clone()
        board[currRow][currCol] = null
        newBoard[newRow][newCol] = piece
        currentPlayer = !currentPlayer
        return Board(newBoard)
    }


    fun makeMove(str: String): Board? {
        val move = toMoveOrNull(str)
        return if (move == null) return null else makeMove(move)
    }

    /**
     * recebe uma string e retorna um tipo MOVE() que tem a jogada com as coordenadas iniciais e finais e a peça
     */
    private fun toMoveOrNull(str: String): Move? {
        val cmd = str.trim()
        val pieceType = getPieceType(cmd[0])
        val currSquare = cmd.substring(1,3).toSquareOrNull()
        val newSquare = cmd.substring(3,5).toSquareOrNull()
        if (currSquare == null || newSquare == null || pieceType == null) return null
        val move = Move(pieceType,currSquare,newSquare)
        if (!isValidSquare(move)) return null
        return move
    }

    /**
     * Checks if there's actually a Piece in the given Square
     * Checks also if the given Move is correct for the current player.
     */
    private fun isValidSquare(move: Move): Boolean {
        val player = if (!currentPlayer) Player.WHITE else Player.BLACK
<<<<<<< Updated upstream
        val piece = board[move.cur.row.ordinal][move.cur.column.ordinal] ?: return false
=======
        val piece = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal] ?: return false
>>>>>>> Stashed changes
        if (piece.type.toStr() != move.piece.toStr()) return false
        if (piece.player !== player) return false
        return true
    }

    /**
     * Recebe um tipo MOVE() sempre valido e aplica a jogada.
     * Utilizado mais para fazer jogadas no jogo corrente
     */
    private fun makeMove(move: Move): Board? {
        if (!move.piece.canItMove(move,board)) return null
        val piece = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]
        val newBoard = board.clone()
        board[move.curSquare.row.ordinal][move.curSquare.column.ordinal] = null
        newBoard[move.newSquare.row.ordinal][move.newSquare.column.ordinal] = piece
        currentPlayer = !currentPlayer
        return Board(newBoard)
    }

<<<<<<< Updated upstream
    fun getColumn(column: Char) =
        when(column) {
            'a' -> Column.A
            'b' -> Column.B
            'c' -> Column.C
            'd' -> Column.D
            'e' -> Column.E
            'f' -> Column.F
            'g' -> Column.G
            'h' -> Column.H
            else -> null
        }
    fun getRow(row: Char) =
        when(row) {
            '1' -> Row.ONE
            '2' -> Row.TWO
            '3' -> Row.THREE
            '4' -> Row.FOUR
            '5' -> Row.FIVE
            '6' -> Row.SIX
            '7' -> Row.SEVEN
            '8' -> Row.EIGHT
            else -> null
        }

    /*private fun checkGameOver(): Boolean {

    }*/

=======
>>>>>>> Stashed changes
}

