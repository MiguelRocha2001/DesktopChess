import model.Board
import model.Player
import org.junit.Test
import kotlin.test.assertEquals

class KnightTest {
    @Test
    fun `Moves Knight`() {
        val sut = Board()
            .makeMove("Nb1c3",Player.WHITE)!!.first//W
            .makeMove("Nb8a6",Player.BLACK)!!.first
            .makeMove("Nc3d5",Player.WHITE)!!.first//W
            .makeMove("Na6c5",Player.BLACK)!!.first

        assertEquals(
            "r bqkbnr"+
                    "pppppppp"+
                    "        "+
                    "  nN    "+
                    "        "+
                    "        "+
                    "PPPPPPPP"+
                    "R BQKBNR", sut.toStringTest() )
    }
}