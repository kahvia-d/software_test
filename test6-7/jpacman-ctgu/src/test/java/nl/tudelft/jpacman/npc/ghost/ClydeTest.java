package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
public class ClydeTest {

    private GhostMapParser parser;
    private PlayerFactory playerFactory;

    @BeforeEach
    public void setUp(){
        PacManSprites sprites = new PacManSprites();
        //创建棋盘工厂
        BoardFactory boardFactory = new BoardFactory(sprites);
        //创建幽灵工厂
        GhostFactory ghostFactory = new GhostFactory(sprites);
        //创建关卡工厂
        LevelFactory levelFactory = new LevelFactory(
            sprites,
            ghostFactory,
            mock(PointCalculator.class));
        parser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);

        playerFactory=new PlayerFactory(sprites);
    }

    @Test
    @DisplayName("简单地形克劳德远离玩家")
    void testEasyClydeFlee(){
        //创建地图
        List<String> mapList= new ArrayList<>();
        mapList.add("############");
        mapList.add("#P       C #");
        mapList.add("############");
        Level level=parser.parseMap(mapList);
        Board board=level.getBoard();

        //放置玩家并设置玩家初始方向
        Player player=playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        //获取棋盘上的克劳德，并设置初始方向
        Clyde clyde= Navigation.findUnitInBoard(Clyde.class,board);
        assert clyde != null;
        clyde.setDirection(Direction.WEST);

        //克劳德与玩家的距离相距8格子以内，克劳德的下一步移动就会远离玩家。否则，接近玩家。
        //这个地图情况，克劳德要走8步才能抵达玩家所在位置，路径长度小于等于害羞距离，所以远离玩家，下一步的方向是右（东）
        assertThat(Optional.of(Direction.EAST)).isEqualTo(clyde.nextAiMove());

    }
    @Test
    @DisplayName("简单地形克劳德接近玩家")
    void testEasyClydeApproach(){
        //创建地图
        List<String> mapList= new ArrayList<>();
        mapList.add("############");
        mapList.add("#P        C#");
        mapList.add("############");
        Level level=parser.parseMap(mapList);
        Board board=level.getBoard();

        //放置玩家并设置玩家初始方向
        Player player=playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        //获取棋盘上的克劳德，并设置初始方向
        Clyde clyde= Navigation.findUnitInBoard(Clyde.class,board);
        assert clyde != null;
        clyde.setDirection(Direction.WEST);

        //克劳德与玩家的距离相距8格子以内，克劳德的下一步移动就会远离玩家。否则，接近玩家。
        //这个地图情况，克劳德要走9步才能抵达玩家所在位置，路径长度大于害羞距离，所以接近玩家，下一步的方向是左（西）
        assertThat(Optional.of(Direction.WEST)).isEqualTo(clyde.nextAiMove());

    }

    @Test
    @DisplayName("复杂地形克劳德远离玩家")
    void testElaborateClydeFlee(){
        //创建地图
        List<String> mapList= new ArrayList<>();
        mapList.add("####    # ##");
        mapList.add("#    #    C ");
        mapList.add("# # ## ## ##");
        mapList.add("            ");
        mapList.add("######P ####");
        Level level=parser.parseMap(mapList);
        Board board=level.getBoard();

        //放置玩家并设置玩家初始方向
        Player player=playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        //获取棋盘上的克劳德，并设置初始方向
        Clyde clyde= Navigation.findUnitInBoard(Clyde.class,board);
        assert clyde != null;
        clyde.setDirection(Direction.WEST);

        //克劳德与玩家的距离相距8格子以内，克劳德的下一步移动就会远离玩家。否则，接近玩家。
        //这个地图情况，克劳德要走7步才能抵达玩家所在位置，路径长度小于等于害羞距离，所以远离玩家，下一步的方向是右（东）
        assertThat(Optional.of(Direction.EAST)).isEqualTo(clyde.nextAiMove());

    }

    @Test
    @DisplayName("复杂地形克劳德接近玩家")
    void testElaborateClydeApproach(){
        //创建地图
        List<String> mapList= new ArrayList<>();
        mapList.add("####    #  C");
        mapList.add("#    #     #");
        mapList.add("# # ## ## ##");
        mapList.add("            ");
        mapList.add("###P  # ####");
        Level level=parser.parseMap(mapList);
        Board board=level.getBoard();

        //放置玩家并设置玩家初始方向
        Player player=playerFactory.createPacMan();
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);

        //获取棋盘上的克劳德，并设置初始方向
        Clyde clyde= Navigation.findUnitInBoard(Clyde.class,board);
        assert clyde != null;
        clyde.setDirection(Direction.WEST);

        //克劳德与玩家的距离相距8格子以内，克劳德的下一步移动就会远离玩家。否则，接近玩家。
        //这个地图情况，克劳德要走11步才能抵达玩家所在位置，路径长度大于害羞距离，所以接近玩家，下一步的方向是左（西）
        assertThat(Optional.of(Direction.WEST)).isEqualTo(clyde.nextAiMove());

    }
}
