import model.Board
import org.junit.Test
import kotlin.test.assertEquals

class KnightTest {
    @Test
    fun `Moves Knight`() {
        val sut = Board()
            .makeMove("Nb1c3")!!.first//W
            .makeMove("Nb8a6")!!.first
            .makeMove("Nc3d5")!!.first//W
            .makeMove("Na6c5")!!.first

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