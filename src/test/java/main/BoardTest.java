package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Shot;
import space_invaders.sprites.Player;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    private void ejecutarMetodoPrivado(String nombreMetodo) {
        try {
            Method method = Board.class.getDeclaredMethod(nombreMetodo);
            method.setAccessible(true);
            method.invoke(board);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Alien prepararUnicoAlien(int x, int y, int direction) {
        Alien alien = new Alien(x, y);
        alien.setX(x);
        alien.setY(y);
        List<Alien> aliens = new ArrayList<>();
        aliens.add(alien);
        board.setAliens(aliens);
        board.setDirection(direction);
        return alien;
    }

    // ==================== PRUEBAS DEL MÉTODO gameInit() ====================

    @Test
    @DisplayName("INIT-B1: Inicialización de la partida mediante gameInit()")
    public void testInitB1GameInit() {
        List<Alien> aliens = board.getAliens();
        int totalAliensEsperados = Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS;
        assertNotNull(aliens, "La lista de aliens debe estar instanciada");
        assertEquals(totalAliensEsperados, aliens.size(),
                "Deben crearse ALIEN_ROWS * ALIEN_COLUMNS alienígenas");
        int index = 0;
        for (int fila = 0; fila < Commons.ALIEN_ROWS; fila++) {
            for (int col = 0; col < Commons.ALIEN_COLUMNS; col++) {
                Alien alien = aliens.get(index++);
                int expectedX = Commons.ALIEN_INIT_X + Commons.ALIEN_SEPARATOR * col;
                int expectedY = Commons.ALIEN_INIT_Y + Commons.ALIEN_SEPARATOR * fila;

                assertEquals(expectedX, alien.getX(),
                        "Coordenada X incorrecta para el alien en fila " + fila + ", columna " + col);
                assertEquals(expectedY, alien.getY(),
                        "Coordenada Y incorrecta para el alien en fila " + fila + ", columna " + col);
            }
        }
        assertTrue(board.isInGame(), "inGame debe ser true al iniciar la partida");
        assertEquals(0, board.getDeaths(), "El contador deaths debe inicializarse a 0");
        assertNotNull(board.getPlayer(), "El objeto player debe estar instanciado");
        assertNotNull(board.getShot(), "El objeto shot debe estar instanciado");
        assertFalse(board.getShot().isVisible(), "El disparo inicial debe estar en estado inactivo (no visible)");
    }



    // ==================== PRUEBAS DEL MÉTODO update() ====================

    @Test
    public void testUpd1DeathsMin() {
        board.setDeaths(0);
        ejecutarMetodoPrivado("update");
        assertTrue(board.isInGame());
    }

    @Test
    public void testUpd2DeathsMinMasUno() {
        board.setDeaths(1);
        ejecutarMetodoPrivado("update");
        assertTrue(board.isInGame());
    }

    @Test
    public void testUpd3DeathsNominal() {
        board.setDeaths(12);
        ejecutarMetodoPrivado("update");
        assertTrue(board.isInGame());
    }

    @Test
    public void testUpd4DeathsMaxMenosUno() {
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY - 1);
        ejecutarMetodoPrivado("update");
        assertTrue(board.isInGame());
    }

    @Test
    public void testUpd5DeathsMax() {
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        ejecutarMetodoPrivado("update");
        assertFalse(board.isInGame());
        assertEquals("Game won!", board.getMessage());
    }

    // ==================== PRUEBAS DEL MÉTODO update_shots() ====================

    @Test
    public void testUshot1VisibleSinColision() {
        Shot shot = new Shot(300, 200);
        shot.setX(300);
        shot.setY(200);
        board.setShot(shot);
        ejecutarMetodoPrivado("update_shots");
        assertEquals(200 - Commons.SHOT_SPEED, shot.getY());
        assertTrue(shot.isVisible());
    }

    @Test
    public void testUshot2ColisionConAlien() {
        Alien alien = new Alien(100, 100);
        List<Alien> aliens = new ArrayList<>();
        aliens.add(alien);
        board.setAliens(aliens);
        Shot shot = new Shot(106, 106);
        shot.setX(106);
        shot.setY(106);
        board.setShot(shot);
        board.setDeaths(0);
        ejecutarMetodoPrivado("update_shots");
        assertTrue(alien.isDying());
        assertEquals(1, board.getDeaths());
    }

    @Test
    public void testUshot3SalePorArriba() {
        Shot shot = new Shot(300, 3);
        shot.setX(300);
        shot.setY(3);
        board.setShot(shot);
        ejecutarMetodoPrivado("update_shots");
        assertFalse(shot.isVisible());
    }

    @Test
    public void testUshot4NoVisible() {
        Shot shot = new Shot(300, 200);
        shot.setX(300);
        shot.setY(200);
        shot.die();
        board.setShot(shot);
        ejecutarMetodoPrivado("update_shots");
        assertEquals(200, shot.getY());
        assertFalse(shot.isVisible());
    }


    // ==================== PRUEBAS DEL MÉTODO update_aliens() ====================

    @Test
    public void testUalien1Nominal() {
        Alien alien = prepararUnicoAlien(166, 151, 1);
        ejecutarMetodoPrivado("update_aliens");
        assertEquals(167, alien.getX());
        assertEquals(151, alien.getY());
        assertEquals(1, board.getDirection());
        assertTrue(board.isInGame());
    }

    @Test
    public void testUalien2XMinMenosUno() {
        Alien alien = prepararUnicoAlien(Commons.BORDER_LEFT - 1, 151, -1);
        ejecutarMetodoPrivado("update_aliens");
        assertEquals(1, board.getDirection());
        assertEquals(151 + Commons.GO_DOWN, alien.getY());
    }

    @Test
    public void testUalien3XMin() {
        Alien alien = prepararUnicoAlien(Commons.BORDER_LEFT, 151, -1);
        ejecutarMetodoPrivado("update_aliens");
        assertEquals(1, board.getDirection());
        assertEquals(151 + Commons.GO_DOWN, alien.getY());
    }

    @Test
    public void testUalien4XMinMasUno() {
        Alien alien = prepararUnicoAlien(Commons.BORDER_LEFT + 1, 151, -1);
        ejecutarMetodoPrivado("update_aliens");
        assertEquals(Commons.BORDER_LEFT, alien.getX());
        assertEquals(151, alien.getY());
        assertEquals(-1, board.getDirection());
    }

    @Test
    public void testUalien5XMaxMenosUno() {
        Alien alien = prepararUnicoAlien(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT - 1, 151, 1);
        ejecutarMetodoPrivado("update_aliens");
        assertEquals(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT, alien.getX());
        assertEquals(151, alien.getY());
        assertEquals(1, board.getDirection());
    }

    @Test
    public void testUalien6XMax() {
        Alien alien = prepararUnicoAlien(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT, 151, 1);
        ejecutarMetodoPrivado("update_aliens");
        assertEquals(-1, board.getDirection());
        assertEquals(151 + Commons.GO_DOWN, alien.getY());
    }

    @Test
    public void testUalien7XMaxMasUno() {
        Alien alien = prepararUnicoAlien(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT + 1, 151, 1);
        ejecutarMetodoPrivado("update_aliens");
        assertEquals(-1, board.getDirection());
        assertEquals(151 + Commons.GO_DOWN, alien.getY());
    }

    @Test
    public void testUalien8YMinMenosUno() {
        Alien alien = prepararUnicoAlien(166, -1, 1);
        ejecutarMetodoPrivado("update_aliens");
        assertEquals(167, alien.getX());
        assertEquals(-1, alien.getY());
        assertTrue(board.isInGame());
    }

    @Test
    public void testUalien9YMin() {
        Alien alien = prepararUnicoAlien(166, 0, 1);
        ejecutarMetodoPrivado("update_aliens");
        assertEquals(167, alien.getX());
        assertEquals(0, alien.getY());
        assertTrue(board.isInGame());
    }

    @Test
    public void testUalien10YMinMasUno() {
        Alien alien = prepararUnicoAlien(166, 1, 1);
        ejecutarMetodoPrivado("update_aliens");
        assertEquals(167, alien.getX());
        assertEquals(1, alien.getY());
        assertTrue(board.isInGame());
    }

    @Test
    public void testUalien11YMaxMenosUno() {
        prepararUnicoAlien(166, Commons.GROUND + Commons.ALIEN_HEIGHT - 1, 1);
        ejecutarMetodoPrivado("update_aliens");
        assertTrue(board.isInGame());
    }

    @Test
    public void testUalien12YMax() {
        prepararUnicoAlien(166, Commons.GROUND + Commons.ALIEN_HEIGHT, 1);
        ejecutarMetodoPrivado("update_aliens");
        assertTrue(board.isInGame());
    }

    @Test
    public void testUalien13YMaxMasUno() {
        prepararUnicoAlien(166, Commons.GROUND + Commons.ALIEN_HEIGHT + 1, 1);
        ejecutarMetodoPrivado("update_aliens");
        assertFalse(board.isInGame());
        assertEquals("Invasion!", board.getMessage());
    }


    // ==================== PRUEBAS DEL MÉTODO update_bomb() ====================

    @Test
    public void testUbomb1DestruidaSeActiva() {
        Alien alien = prepararUnicoAlien(150, 100, 1);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true);
        int intentos = 0;
        while (bomb.isDestroyed() && intentos < 1000) {
            ejecutarMetodoPrivado("update_bomb");
            intentos++;
        }
        assertFalse(bomb.isDestroyed());
        assertEquals(alien.getX(), bomb.getX());
        assertEquals(alien.getY(), bomb.getY());
    }


    @Test
    public void testUbomb2ActivaSinColision() {
        Alien alien = prepararUnicoAlien(150, 100, 1);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(10);
        bomb.setY(150);
        ejecutarMetodoPrivado("update_bomb");
        assertEquals(150 + Commons.BOMB_SPEED, bomb.getY());
        assertFalse(bomb.isDestroyed());
    }

    @Test
    public void testUbomb3ColisionConJugador() {
        Alien alien = prepararUnicoAlien(150, 100, 1);
        Player player = board.getPlayer();
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(player.getX() + 1);
        bomb.setY(player.getY());
        ejecutarMetodoPrivado("update_bomb");
        assertTrue(player.isDying());
    }

    @Test
    public void testUbomb4AlcanzaElSuelo() {
        Alien alien = prepararUnicoAlien(150, 100, 1);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(10);
        bomb.setY(Commons.GROUND - Commons.BOMB_HEIGHT);
        ejecutarMetodoPrivado("update_bomb");
        assertTrue(bomb.isDestroyed());
    }


}