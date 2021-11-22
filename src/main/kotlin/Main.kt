import Comands.Success
import Comands.Terminate
import Comands.buildMenuHandlers
import DataBase.MongoChessCommands
import model.GameChess
import model.StatusGame
import mongoDb.MongoDriver

/**
 * It's the main entry point of the program.
 * Its contantly in a cycle waiting for commands and showing their result.
 *
 */

//var variable = "MONGO_CONNECTION=mongodb+srv://miguel:RGJ#rc74JhB3V.f@cluster0.pwqp1.mongodb.net/TDS?retryWrites=true&w=majority"
//val var2 = "MONGO_DB_NAME=TDS"

val moves = mutableListOf<String>()

fun main() {
    MongoDriver().use { driver ->
        try {
            val mongoChessCommands = MongoChessCommands(driver)
            var gameChess = GameChess(mongoChessCommands, null, null, StatusGame(null,listOf(),null))
            val menuHandlers = buildMenuHandlers()

            while (true) {
                val (name, parameter) = readCommand()
                // parameter can never be null
                //if (parameter == null) continue
                val cmd = menuHandlers[name]
                if (cmd == null)
                    println("Invalid command")
                else {
                    val result = cmd.action(gameChess, parameter)
                    if (result is Success)
                        gameChess = result.gameChess
                    else if (result is Terminate)
                        break
                    cmd.show(result)
                }
            }
        } catch (ex: Exception) {
            println("Error: ${ex.message}.")
        }
    }
    println("Bye")
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
