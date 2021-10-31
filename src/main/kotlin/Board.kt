import java.util.*

enum class Player { WHITE, BLACK;
    fun advance() = if (this === WHITE) BLACK else WHITE
}

class Board {
    private val LINES = 8
    private val COLS = 8
    private val board: Array<Array<Pair<Piece,Player>?>>

    private val currentPlayer: Player

    constructor() {
        currentPlayer = Player.WHITE
        board = Array(LINES) { Array(COLS) { null } }
        init()
    }

    private constructor(board: Array<Array<Pair<Piece,Player>?>>, currentPlayer: Player) {
        this.currentPlayer = currentPlayer
        this.board = board
    }


    /**
     * Iniciates the board
     */
    private fun init() {
        initBlack()
        initWhite()
    }

    private fun initWhite() {
        val whitePos = 0
        board[whitePos][0] = Pair(Rook(),Player.WHITE)
        board[whitePos][1] = Pair(Knight(),Player.WHITE)
        board[whitePos][2] = Pair(Bishop(),Player.WHITE)
        board[whitePos][3] = Pair(Queen(),Player.WHITE)
        board[whitePos][4] = Pair(King(),Player.WHITE)
        board[whitePos][5] = Pair(Bishop(),Player.WHITE)
        board[whitePos][6] = Pair(Knight(),Player.WHITE)
        board[whitePos][7] = Pair(Rook(),Player.WHITE)
        for (i in 0..7) {
            board[whitePos+1][i] = Pair(Pawn(),Player.WHITE)
        }
    }

    private fun initBlack() {
        board[7][0] = Pair(Rook(),Player.BLACK)
        board[7][1] = Pair(Knight(),Player.BLACK)
        board[7][2] = Pair(Bishop(),Player.BLACK)
        board[7][3] = Pair(Queen(),Player.BLACK)
        board[7][4] = Pair(King(),Player.BLACK)
        board[7][5] = Pair(Bishop(),Player.BLACK)
        board[7][6] = Pair(Knight(),Player.BLACK)
        board[7][7] = Pair(Rook(),Player.BLACK)
        for (i in 0..7) {
            board[6][i] = Pair(Pawn(),Player.BLACK)
        }
    }

    /**
     * Indicates if the given line is empty
     */
    private fun howManyPiecesInLine(line: Int): Int {
        var count = 0
        for (piece in board[line])
            if (piece != null) ++count
        return count
    }

    /**
     * Converts the current state of the game in a String
     */
    override fun toString(): String {
        var str = ""
        for (line in board.indices) {
            for (piece in board[line]) {
                if (piece != null) {
                    str += if (piece.second === Player.WHITE)
                        piece.first.toStr().lowercase(Locale.getDefault())
                    else
                        piece.first.toStr()
                }
                else str += ' '
            }
        }
        return str
    }


    fun makeMove(move: String): Board {
        if (!move.isMoveValid()) return this
        val cLine = move[2] - '0'
        val cCol = move[1] - 'a'
        val nLine = move[4] - '0'
        val nCol = move[3] - 'a'

        val piece = if (board[cLine][cCol] != null) board[cLine][cCol]!!.first else return this

        val direction =
            if (nLine - cLine > 0) {
                if (nCol - cCol > 0) Dir.FRONT_LEFT
                if (nCol - cCol < 0) Dir.FRONT_RIGHT
                else Dir.FRONT
            }
            else if (nLine - cLine < 0) {
                if (nCol - cCol > 0) Dir.BACK_LEFT
                if (nCol - cCol < 0) Dir.BACK_RIGHT
                else Dir.BACK
            }
            else {
                if (nCol - cCol > 0) Dir.LEFT
                if (nCol - cCol < 0) Dir.RIGHT
                else null
            }

        // checks if the direction is valid
        val dirs = piece.getDirections()
        if (!dirs.contains(direction)) return this

        // Creates a new array board
        val newBoard = board.clone()
        newBoard[cLine][cCol] = null
        newBoard[nLine][nCol] = Pair(piece,currentPlayer)
        return Board(newBoard,currentPlayer.advance())
    }
}

private fun String.isMoveValid(): Boolean {
    val line = this.trim()
    if (!PieceTypes.contains(line[0])) return false
    val cLine = line[2]
    val cCol = line[1]
    val nLine = line[4]
    val nCol = line[3]
    // checks line
    if (cLine <= '1' || cLine >= '8' || nLine <= '1' || nLine >= '8' ) return false
    // checks col
    if (cCol <= 'a' || cCol >= 'h' || nCol <= 'a' || nCol >= 'h' ) return false
    return true
}