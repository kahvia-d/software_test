package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class PlayerCollisionsTest {
    @Mock
    private Player player;
    @Mock
    private Ghost ghost;
    @Mock
    private Pellet pellet;
    @InjectMocks//把pointCalculator注入到playerCollisions
    @Spy
    private PlayerCollisions playerCollisions;
    @Mock
    private PointCalculator pointCalculator;

    @BeforeEach
    void setUp(){
        //开启注解mock
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("玩家撞上幽灵")
    void testPlayerCollidedOnGhost(){
        playerCollisions.collide(player,ghost);
        Mockito.verify(playerCollisions,Mockito.times(1)).playerVersusGhost(player,ghost);
    }

    @Test
    @DisplayName("玩家撞上豆子")
    void testPlayerCollidedOnPellet(){
        playerCollisions.collide(player,pellet);
        Mockito.verify(playerCollisions,Mockito.times(1)).playerVersusPellet(player,pellet);
    }

    @Test
    @DisplayName("幽灵撞上玩家")
    void testGhostCollidedOnPlayer(){
        playerCollisions.collide(ghost,player);
        Mockito.verify(playerCollisions,Mockito.times(1)).playerVersusGhost(player,ghost);
    }

    @Test
    @DisplayName("豆子撞上玩家")
    void testPelletCollidedOnPlayer(){
        playerCollisions.collide(pellet,player);
        Mockito.verify(playerCollisions,Mockito.times(1)).playerVersusPellet(player,pellet);
    }
}
