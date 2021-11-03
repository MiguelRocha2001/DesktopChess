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

        board[firstRow][0] = Pair(Rook(), player)
        board[firstRow][1] = Pair(Knight(), player)
        board[firstRow][2] = Pair(Bishop(), player)
        board[firstRow][3] = Pair(Queen(), player)
        board[firstRow][4] = Pair(King(), player)
        board[firstRow][5] = Pair(Bishop(), player)
        board[firstRow][6] = Pair(Knight(), player)
        board[firstRow][7] = Pair(Rook(), player)
        for (i in 0..7) {
            board[secondRow][i] = Pair(Pawn(),player)
        }
    }

    /**
     * Indicates if the given line is empty
     */
    private fun howManyPiecesInLine(line: Int): Int {
        var count = 0
        for (piece in board[line])
            if (piece != null) count++
        return count
    }

    /**
     * Converts the current state of the game in a String
     */
    override fun toString(): String {
        var str = ""
        for (line in board.indices.reversed()) {
            for (piece in board[line]) {
                if (piece != null) {
                    str += if (piece.second == Player.BLACK)
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
        val currCol = move[1] - 'a'
        val currLine = move[2] - '1'
        val newCol = move[3] - 'a'
        val newLine = move[4] - '1'

        val piece = if (board[currLine][currCol] != null) board[currLine][currCol]!!.first else return this
        val test = board[currLine][currCol].second
        val direction = getDirection(newLine, currLine, newCol, currCol)

        // checks if the direction is valid
        val dirs = piece.getDirections()
        if (!dirs.contains(direction)) return this

        // Creates a new array board
        val newBoard = board.clone()
        newBoard[currLine][currCol] = null
        newBoard[newLine][newCol] = Pair(piece,currentPlayer)
        return Board(newBoard,currentPlayer.advance())
    }

    /**
     * Returns the according direction given in the parameters.
     */
    private fun getDirection(nLine: Int, cLine: Int, nCol: Int, cCol: Int): Dir? {
        val dir =
            if (nLine - cLine > 0) {
                if (nCol - cCol > 0) Dir.FRONT_LEFT
                if (nCol - cCol < 0) Dir.FRONT_RIGHT
                else Dir.FRONT
            } else if (nLine - cLine < 0) {
                if (nCol - cCol > 0) Dir.BACK_LEFT
                if (nCol - cCol < 0) Dir.BACK_RIGHT
                else Dir.BACK
            } else {
                if (nCol - cCol > 0) Dir.LEFT
                if (nCol - cCol < 0) Dir.RIGHT
                return null
            }
        //inverts direction for Black pieces
        return if (currentPlayer === Player.BLACK) dir.invertDirection() else dir
}

    private fun String.isMoveValid(): Boolean {
        val line = this.trim()
        if (!PieceTypes.contains(line[0])) return false

        val currCol = line[1]
        val currLine = line[2]
        val newCol = line[3]
        val newLine = line[4]

        //checks if piece belongs to player
        if(board[currLine-'1'][currCol-'a'].second != currentPlayer) return false

        // checks line
        if (currLine < '1' || currLine > '8' || newLine <= '1' || newLine >= '8' ) return false
        // checks col
        if (currCol < 'a' || currCol > 'h' || newCol < 'a' || newCol > 'h' ) return false

        return true
    }
}

