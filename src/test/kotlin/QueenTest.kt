import model.Board
import model.Player
import org.junit.Test
import kotlin.test.assertEquals

class QueenTest {
    @Test
    fun `Moves Queen`() {
        val sut = Board()
            .makeMove("Pe2e4",Player.WHITE)!!.first//W
            .makeMove("Pb7b6",Player.BLACK)!!.first
            .makeMove("Qd1e2",Player.WHITE)!!.first//W
            .makeMove("Pb6b5",Player.BLACK)!!.first
            .makeMove("Qe2c4",Player.WHITE)!!.first//W
            .makeMove("Pb5b4",Player.BLACK)!!.first
            .makeMove("Qc4f7",Player.WHITE)!!.first//W
            .makeMove("Pb4b3",Player.BLACK)!!.first
            .makeMove("Qf7f8",Player.WHITE)!!.first//W

        assertEquals(
            "rnbqkQnr"+
                    "p ppp pp"+
                    "        "+
                    "        "+
                    "    P   "+
                    " p      "+
                    "PPPP PPP"+
                    "RNB KBNR", sut.toStringTest() )
    }
}