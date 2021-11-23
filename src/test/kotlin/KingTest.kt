import model.Board
import model.Player
import org.junit.Test
import kotlin.test.assertEquals


class KingTest {
    @Test
    fun `Moves King`() {
        val sut = Board()
            .makeMove("Pe2e4", Player.WHITE)!!.first//W
            .makeMove("Pb7b6", Player.BLACK)!!.first
            .makeMove("Ke1e2", Player.WHITE)!!.first//W
            .makeMove("Pb6b5", Player.BLACK)!!.first
            .makeMove("Ke2e3", Player.WHITE)!!.first//W
            .makeMove("Pb5b4", Player.BLACK)!!.first
            .makeMove("Ke3f3", Player.WHITE)!!.first//W
            .makeMove("Pb4b3", Player.BLACK)!!.first
            .makeMove("Kf3e3", Player.WHITE)!!.first//W

        assertEquals(
            "rnbqkbnr"+
                    "p pppppp"+
                    "        "+
                    "        "+
                    "    P   "+
                    " p  K   "+
                    "PPPP PPP"+
                    "RNBQ BNR", sut.toStringTest() )
    }
}