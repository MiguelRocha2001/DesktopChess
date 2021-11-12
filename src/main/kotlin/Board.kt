import chess.model.Column
import chess.model.Square
import java.util.*

class Board {
    companion object {
        private var currentPlayer = false
    }
    enum class Player { WHITE, BLACK;
        fun advance() = if (this === WHITE) BLACK else WHITE
    }

    class Piece(val type: PieceType, val player: Player)
    class Move(val piece: PieceType, val cur: Square, val target: Square)

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
        var firstRow = 0
        var secondRow = firstRow+1
        if (player == Player.BLACK){
            firstRow = LINES-1
            secondRow = firstRow-1
        }
        board[firstRow][0] = Piece(Rook(),player)
        board[firstRow][1] = Piece(Knight(),player)
        board[firstRow][2] = Piece(Bishop(), player)
        board[firstRow][3] = Piece(Queen(), player)
        board[firstRow][4] = Piece(King(), player)
        board[firstRow][5] = Piece(Bishop(), player)
        board[firstRow][6] = Piece(Knight(), player)
        board[firstRow][7] = Piece(Rook(), player)
        for (i in 0..7)
            board[secondRow][i] = Piece(Pawn(),player)
    }

    /**
     * Converts the current state of the game in a String
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
    fun toMoveOrNull(move: String): Move? {
        val row = move.trim()
        val currCol = getColumn(row[1])
        val currRow = getRow(row[2])
        val newCol = getColumn(row[3])
        val newRow = getRow(row[4])
        val pieceType = getPieceType(move[0])
        if (currCol == null || currRow == null || newCol == null || newRow == null || pieceType == null) return null
        val move = Move(pieceType,Square(currCol,currRow),Square(newCol,newRow))
        if (!isValidSquare(move)) return null
        return move
    }

    /**
     * Checks if there's actually a Piece in the given Square
     * Checks also if the given Move is correct for the current player.
     */
    private fun isValidSquare(move: Move): Boolean {
        val player = if (!currentPlayer) Player.WHITE else Player.BLACK
        val piece = board[move.cur.row.ordinal][move.cur.column.ordinal] ?: return false
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
        val piece = board[move.cur.row.n][move.cur.column.n]
        val newBoard = board.clone()
        board[move.cur.row.n][move.cur.column.n] = null
        newBoard[move.target.row.n][move.target.column.n] = piece
        currentPlayer = !currentPlayer
        return Board(newBoard)
    }

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

}

