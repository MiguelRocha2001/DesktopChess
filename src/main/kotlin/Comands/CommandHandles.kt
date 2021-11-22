package Comands

import model.GameChess
import model.Player

/**
 * Type of each command handler.
 * Explicitly separates the action of the command and the presentation of the result.
 * It is possible to do tests on the action, without having tests on the presentation.
 * @property action The action function.
 * @property show The show function.
 */
data class Command(

    /**
     * Performs the action of the command returning type Result witch contains the result of the operation:
     * Success if the operation went well, otherwise Error. Both are a subtype of Result containing its own information.
     * Receive the parameter given on command line.
     * May throws exception in case of failure with appropriate error message.
     */
    val action: (GameChess, String?) -> Result,

    /**
     * Displays the result returned by the action.
     * Receive the result information to show.
     */
    val show: (Result) -> Unit = { }
)

/**
 * Build the associative map of command handlers to initiate/exit games.
 * @return The handlers map of all commands.
 */
fun buildMenuHandlers() = mapOf(
    "OPEN" to Command(
        // returns a new Board (restored or new)
        action = { gameChess: GameChess, gameId: String? ->
            val newGame = restoreGame(gameChess.mongoChessCommands, gameId)
            if (newGame == null)
                Error(ErrorType.MISSING_CONTENT)
            Success(gameChess.copy(player = Player.WHITE, status = newGame!!, gameId = gameId))
        },
        show = { result: Result ->
            if (result is Error)
                if (result.type === ErrorType.MISSING_CONTENT)
                    println("Missing gameId.")
            if (result is Success) {
                println(result.gameChess.status.board.toString())
                println("Game "+result.gameChess.gameId+" opened. Play with white pieces")
                println(result.gameChess.gameId+':'+result.gameChess.status.currentPlayer+'>')
            }
        }
    ),
    // returns a new Board
    "JOIN" to Command(
        action = { gameChess: GameChess, gameId: String? ->
            val newGame = joinGame(gameChess.mongoChessCommands, gameId)
            if (newGame == null)
                Error(ErrorType.MISSING_CONTENT)
            Success(gameChess.copy(player = Player.BLACK, status = newGame!!, gameId = gameId))
        },
        show = { result: Result ->
            if (result is Error)
                if (result.type === ErrorType.MISSING_CONTENT)
                    println("Missing gameId.")
            if (result is Success) {
                println(result.gameChess.status.board.toString())
                println("Game "+result.gameChess.gameId+" opened. Play with black pieces")
                println(result.gameChess.gameId+':'+result.gameChess.status.currentPlayer+'>')
            }
        }
    ),
    "EXIT" to Command( { _,_ -> Terminate }),
    "PLAY" to Command(
        action = { gameChess: GameChess, move: String? ->
            if (gameChess.gameId != null) {
                if (move == null)
                    Error(ErrorType.MISSING_CONTENT)
                val newGame = makeMove(gameChess.status, move, gameChess.player!!)
                if (newGame != null) {
                    saveMove(gameChess.mongoChessCommands, gameChess.gameId, newGame.second)
                    Success(gameChess.copy(status = newGame.first))
                }
                else
                    Error(ErrorType.INVALID_MOVE)
            }
            else
                Error(ErrorType.GAME_NOT_INITIATED)
        },
        show = { result: Result ->
            if (result is Success) {
                println(result.gameChess.status.board.toString())
                println(result.gameChess.gameId+':'+result.gameChess.status.currentPlayer+'>')
            }
            if (result is Error)
                when(result.type) {
                    ErrorType.GAME_NOT_INITIATED ->
                        println("Can't play without a game: try open or join commands.")
                    ErrorType.NOT_YOUR_TURN ->
                        println("Wait for your turn: try refresh command")
                    ErrorType.MISSING_CONTENT ->
                        println("Missing move.")
                    ErrorType.INVALID_MOVE ->
                        println("Invalid move.")
                }
        }
    ),
    "REFRESH" to Command(
        action = { gameChess: GameChess, gameId: String? ->
            if (gameChess.gameId != null) {
                val result = restoreGame(gameChess.mongoChessCommands, gameChess.gameId)
                if (result == null)
                    Error(ErrorType.GAME_NOT_INITIATED)
                Success(gameChess.copy(status = result!!))
            }
            else
                Error(ErrorType.GAME_NOT_INITIATED)
                 },
        show = {result ->
            if (result is Error)
                if (result.type === ErrorType.GAME_NOT_INITIATED)
                    println("Can't play without a game: try open or join commands.")
            if (result is Success) {
                println(result.gameChess.status.board.toString())
                println(result.gameChess.gameId+':'+result.gameChess.status.currentPlayer+'>')
            }
        }
    ),
    "MOVES" to Command(
        action = { gameChess: GameChess, gameId: String? ->
            if (gameChess.gameId != null)
                Success(gameChess)
            else
                Error(ErrorType.GAME_NOT_INITIATED)
        },
        show = { result ->
            if (result is Error)
                if (result.type === ErrorType.GAME_NOT_INITIATED)
                    println("No game, no moves.")
            if (result is Success) {
                println(result.gameChess.status.list.toString())
                println(result.gameChess.gameId+':'+result.gameChess.status.currentPlayer+'>')
            }

        },
    )
)

abstract class Result

class Error(val type: ErrorType): Result()

object Terminate: Result()

/**
 * Used to represent the possible Errors witch could have occured in the commands above.
 */
enum class ErrorType() {
    INVALID_MOVE, MISSING_CONTENT, GAME_NOT_INITIATED, NOT_YOUR_TURN
}

class Success(val gameChess: GameChess): Result()