import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import simpleGame.core.Board;
import simpleGame.core.Direction;
import simpleGame.core.Pawn;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Eclipse might not find this one automatically:

@RunWith(MockitoJUnitRunner.class) // This is required for mocks to work
public class TestPawn{

    Pawn pawn;
    Board board;

    @Before
    public void setUp(){

        board=mock(Board.class);

        pawn = new Pawn('n',3,2,board);

    }
    @Test
    public void testGetX() throws Exception {

        assertEquals(3,pawn.getX());

    }


    @Test
    public void testGetY() throws Exception {
        assertEquals(2,pawn.getY());
    }

    @Test
    public void testGetLetter() throws Exception {
        assertEquals('n',pawn.getLetter());
    }

    @Test
    public void testGetGold() throws Exception {


    }

    @Test
    public void testMove() throws Exception {

        when(board.getXSize()).thenReturn(5);
        when(board.getYSize()).thenReturn(5);

        Direction up=Direction.Up;
        Direction down=Direction.Down;
        Direction left=Direction.Left;
        Direction right=Direction.Right;
        Pawn p2=new Pawn('m',2,2,board);
        when(board.getSquareContent(3,1)).thenReturn(null);
        when(board.getSquareContent(3,2)).thenReturn(null);
        when(board.getSquareContent(3,3)).thenReturn(null);
        when(board.getSquareContent(2,2)).thenReturn(p2);
        //pawn move down
        String msg=pawn.move(down);
        assertEquals("",msg);
        assertEquals(3,pawn.getX());
        assertEquals(1,pawn.getY());
        //pawn move up
        pawn.move(up);
        assertEquals(3,pawn.getX());
        assertEquals(2,pawn.getY());
        //pawn move left and a attack
        String resAtt=pawn.move(left);
        String cmp=pawn.getLetter() + " attacks!\n"+p2.getLetter()+" loses "+1
                +" hitpoints.";
        assertEquals(3,pawn.getX());
        assertEquals(2,pawn.getY());
        assertEquals(cmp,resAtt);

    }

    @Test
    public void testIsDead() throws Exception {

        assertFalse(pawn.isDead());
    }
}
