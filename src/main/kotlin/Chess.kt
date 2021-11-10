import DataBase.MongoChessCommands
import DataBase.getMoves
import DataBase.postMoves
import mongoDb.MongoDriver

/**
 * O tipo Chess vai guardar uma lista de Moves (estados do jogo) e um Board
 */

val moves = mutableListOf<String>()

fun main() {
    var board = Board()
    val player: Player = readPlayerId()
    // if the current player has any game on the database
    if (checkPlayerMoves(player))
        board = restoreGame(player)
    while (true) {
        println(board.toStr())
        val move = readLine()!!
        val newGame = board.makeMove(move)
        if (newGame != null) {
            board = newGame
            moves.add(move)
        }
    }
    saveGame(player, moves)
}

fun saveGame(player: Player, moves: List<String>) {
    MongoDriver().use { driver ->
        val commands = MongoChessCommands(driver)
        postMoves(commands,player,moves)
    }
}

fun checkPlayerMoves(player: Player): Boolean {
    MongoDriver().use { driver ->
        val commands = MongoChessCommands(driver)
        val moves = getMoves(commands,player.id) as List<Move>
        return moves.isEmpty()
    }
}


private fun restoreGame(player: Player): Board {
    val newGame = Board()
    MongoDriver().use{ driver ->
        val commands = MongoChessCommands(driver)
        val move: Iterable<Move> = getMoves(commands,player.id)
        move.forEach{ move -> newGame.makeMoveWithCorrectString(move.getMove())}
    }
    return newGame
}

/**
 * Requests the author info to be used when posting billboard messages.
 * @return  the author information
 */
fun readPlayerId(): Player {
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

