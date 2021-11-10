
import DataBase.MongoChessCommands
import mongoDb.MongoDriver

/**
 * O tipo Chess vai guardar uma lista de Moves (estados do jogo) e um Board
 */

val moves = mutableListOf<String>()

fun main() {
    var board = Board()
    var player = readPlayerId()
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
}

fun checkPlayerMoves(player: Player): Boolean {
    MongoDriver().use { driver ->
        if (driver.getCollection(player.id))
    }
}


private fun restoreGame(playerId: Player): Board {
    var newGame = Board()
    MongoDriver().use{ driver ->
        val commands = MongoChessCommands(driver)
        val moves: Iterable<Move> = commands.getMessagesByPlayer(playerId)
        moves.forEach{ move -> newGame.makeMoveWithCorrectString(move.getContent())}
    }
    return newGame
}


/**
 * Command line after is parsed.
 * first: name of command in uppercase.
 * second: optional parameter (one or more words)
 */
typealias LineCommand = Pair<String, String?>

/**
 * Reads and parses a command line after write the prompt.
 * @return command parsed
 */
fun readCommand(): LineCommand {
    print("> ")
    return readln().parseCommand()
}

/**
 * Parses a string to extract a command.
 * Pure function to be tested.
 * @return command parsed.
 */
fun String.parseCommand(): LineCommand {
    val line = this.trim()
    val cmd = line.substringBefore(' ').uppercase()
    val param = line.substringAfter(' ',"").ifBlank { null }
    return cmd to param
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
fun readlnOrNull() = readLine()
