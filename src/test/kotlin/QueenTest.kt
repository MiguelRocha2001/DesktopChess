import model.Board
import org.junit.Test
import kotlin.test.assertEquals

class QueenTest {
    @Test
    fun `Moves Queen`() {
        val sut = Board()
            .makeMove("Pe2e4")!!.first//W
            .makeMove("Pb7b6")!!.first
            .makeMove("Qd1e2")!!.first//W
            .makeMove("Pb6b5")!!.first
            .makeMove("Qe2c4")!!.first//W
            .makeMove("Pb5b4")!!.first
            .makeMove("Qc4f7")!!.first//W
            .makeMove("Pb4b3")!!.first
            .makeMove("Qf7f8")!!.first//W

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