import model.Board
import model.Player
import org.junit.Test
import kotlin.test.assertEquals


class BishopTest {
    @Test
    fun `Move Bishop all possible ways`() {
        val sut = Board().makeMove("Pd2d4",Player.WHITE)!!.first//W
            .makeMove("Pd7d5",Player.BLACK)!!.first
            .makeMove("Bc1e3",Player.WHITE)!!.first//W
            .makeMove("Pa7a6",Player.BLACK)!!.first
            .makeMove("Be3d2",Player.WHITE)!!.first//W
            .makeMove("Pa6a5",Player.BLACK)!!.first
            .makeMove("Bd2b4",Player.WHITE)!!.first//W
            .makeMove("Pa5a4",Player.BLACK)!!.first
            .makeMove("Bb4c3",Player.WHITE)!!.first//W
        assertEquals(
            "rnbqkbnr"+
                    " pp pppp"+
                    "        "+
                    "   p    "+
                    "p  P    "+
                    "  B     "+
                    "PPP PPPP"+
                    "RN QKBNR", sut.toStringTest() )
    }
}