package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
class MapParserTest {
    @InjectMocks//将其它的mock对象作为依赖注入下面的parser
    @Spy
    private MapParser mapParser;
    @Mock
    private  LevelFactory levelFactory ;
    @Mock
    private  BoardFactory boardFactory ;

    @BeforeEach
    void setup() {
        //开启注解mock
        MockitoAnnotations.openMocks(this);
        //行为设定
        when(boardFactory.createGround()).thenReturn(mock(Square.class));
        when(boardFactory.createWall()).thenReturn(mock(Square.class));
        when(levelFactory.createGhost()).thenReturn(mock(Ghost.class));
        when(levelFactory.createPellet()).thenReturn(mock(Pellet.class));
    }

    @Test
    @DisplayName("读取的文件名为null")
    void nullFile() {
        assertThatThrownBy(() -> {
            mapParser.parseMap((String) null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("读取的文件不存在")
    void notExistFile() {
        String file = "/notexistmap.txt";
        assertThatThrownBy(() -> {
            mapParser.parseMap(file);
        }).isInstanceOf(PacmanConfigurationException.class).hasMessage("Could not get resource for: " + file);
    }

    @Test
    @DisplayName("读取的文件存在")
    void existsFile() throws IOException {
        String file = "/simplemap.txt";
        mapParser.parseMap(file);
        verify(boardFactory, times(4)).createGround();
        verify(boardFactory, times(2)).createWall();
        verify(levelFactory, times(1)).createGhost();
    }

    @Test
    @DisplayName("读取的地图文件无法识别")
    void unrecognizedMap() {
        String file = "/unrecognizedMap.txt";
        assertThatThrownBy(() -> {
            mapParser.parseMap(file);
        }).isInstanceOf(PacmanConfigurationException.class).hasMessage("Could not get resource for: /unrecognizedMap.txt");
    }

}
