/**
 * O tipo Chess vai guardar uma lista de Moves (estados do jogo) e um Board
 */

<<<<<<< Updated upstream
/*
val moves = listOf<Board.Move>()
val board: Board
=======
val moves = mutableListOf<String>()

fun main() {
    var board = Board()
    val game: Game = readPlayerId()
    // if the current player has any game on the database
    if (checkPlayerMoves(game))
        board = restoreGame(game)
    while (true) {
        println(board.toStr())
        val move = readLine()!!
        val newGame = board.makeMove(move)
        if (newGame != null) {
            board = newGame
            moves.add(move)
        }
    }
    saveGame(game, moves)
}

fun saveGame(game: Game, moves: List<String>) {
    MongoDriver().use { driver ->
        val commands = MongoChessCommands(driver)
        postMoves(commands,game,moves)
    }
}

fun checkPlayerMoves(game: Game): Boolean {
    MongoDriver().use { driver ->
        val commands = MongoChessCommands(driver)
        val moves = getMoves(commands,game.id) as List<Moves>
        return moves.isEmpty()
    }
}


private fun restoreGame(game: Game): Board {
    val newGame = Board()
    MongoDriver().use{ driver ->
        val commands = MongoChessCommands(driver)
        val moves: Iterable<Moves> = getMoves(commands,game.id)
        moves.forEach{ move -> newGame.makeMoveWithCorrectString(move.getMove())}
    }
    return newGame
}

/**
 * Requests the author info to be used when posting billboard messages.
 * @return  the author information
 */
fun readPlayerId(): Game {
    while (true) {
        print("Please provide your user id: ")
        readln().toPlayerOrNull()?.apply { return this }
        println("Invalid player.")
    }
}

/**
 * Functions to read a line from standard input.
 * While we don't have the 1.6 version of the Kotlin library.
 */
fun readln() = readLine()!!
>>>>>>> Stashed changes

 */