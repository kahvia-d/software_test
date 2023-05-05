package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * Test suite to confirm the public API of {@link Square} works as desired.
 *
 * @author Jeroen Roosen 
 */
class MySquareTest {

    /**
     * The square under test.
     */
    private nl.tudelft.jpacman.board.Square square;

    /**
     * Resets the square under test.
     */
    @BeforeEach
    void setUp() {
        square = new BasicSquare();
    }


    //未占据位置
    @Test
    void noStartSquare(){
        Unit occupant = mock(Unit.class);

        assertThat(occupant.getSquare()).isNull();
    }


    //占据一次位置
    @Test
    void testOccupy() {
        Unit occupant = spy(Unit.class);
        //尝试占据空间
        occupant.occupy(square);
        assertThat(square.getOccupants()).contains(occupant);
    }


    //重复占据同一位置
    @Test
    void testReoccupy() {
        Unit occupant = spy(Unit.class);
        occupant.occupy(square);
        occupant.occupy(square);

        assertThat(square.getOccupants()).contains(occupant);
    }


    @Test
    void testLeave() {
        Unit occupant = mock(Unit.class);
        square.put(occupant);
        square.remove(occupant);

        assertThat(square.getOccupants()).doesNotContain(occupant);
    }

    @Test
    void testOrder() {
        Unit o1 = mock(Unit.class);
        Unit o2 = mock(Unit.class);
        square.put(o1);
        square.put(o2);

        assertThat(square.getOccupants()).containsSequence(o1, o2);
    }

}
