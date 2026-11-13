package space_invaders.sprints;

import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlienBombTest {

    private Alien alien;

    @BeforeEach
    public void setUp() {
        alien = new Alien(0, 0);
    }

    // ==================== PRUEBAS DE initBomb(int x, int y) - EJE X ====================

    @Test
    @DisplayName("BOMB-1: Nominal (150, 150 -> x=150, y=150)")
    public void testBOMB1_Nominal() {
        Alien.Bomb bomb = alien.new Bomb(150, 150);
        assertEquals(150, bomb.getX());
        assertEquals(150, bomb.getY());
    }

    @Test
    @DisplayName("BOMB-2: x = min - 1 (-1, 150 -> x=0, y=150, imagen visible)")
    public void testBOMB2_XMinMenos1() {
        Alien.Bomb bomb = alien.new Bomb(-1, 150);
        assertEquals(0, bomb.getX());
        assertEquals(150, bomb.getY());
    }

    @Test
    @DisplayName("BOMB-3: x = min (0, 150 -> x=0, y=150, imagen visible)")
    public void testBOMB3_XMin() {
        Alien.Bomb bomb = alien.new Bomb(0, 150);
        assertEquals(0, bomb.getX());
        assertEquals(150, bomb.getY());
    }

    @Test
    @DisplayName("BOMB-4: x = min + 1 (1, 150 -> x=1, y=150)")
    public void testBOMB4_XMinMas1() {
        Alien.Bomb bomb = alien.new Bomb(1, 150);
        assertEquals(1, bomb.getX());
        assertEquals(150, bomb.getY());
    }

    @Test
    @DisplayName("BOMB-5: x = max - 1 (357, 150 -> x=357, y=150)")
    public void testBOMB5_XMaxMenos1() {
        Alien.Bomb bomb = alien.new Bomb(Commons.BOARD_WIDTH - 1, 150);
        assertEquals(Commons.BOARD_WIDTH - 1, bomb.getX());
        assertEquals(150, bomb.getY());
    }


    @Test
    @DisplayName("BOMB-6: x = max (358, 150 -> x=358, y=150, imagen visible)")
    public void testBOMB6_XMax() {
        Alien.Bomb bomb = alien.new Bomb(Commons.BOARD_WIDTH, 150);
        assertEquals(Commons.BOARD_WIDTH, bomb.getX());
        assertEquals(150, bomb.getY());
    }


    @Test
    @DisplayName("BOMB-7: x = max + 1 (359, 150 -> x=358, y=150, imagen visible)")
    public void testBOMB7_XMaxMas1() {
        Alien.Bomb bomb = alien.new Bomb(Commons.BOARD_WIDTH + 1, 150);
        assertEquals(Commons.BOARD_WIDTH, bomb.getX());
        assertEquals(150, bomb.getY());
    }


    // ==================== PRUEBAS DE initBomb(int x, int y) - EJE Y ====================

    @Test
    @DisplayName("BOMB-8: y = min - 1 (150, -1 -> x=150, y=0, imagen visible)")
    public void testBOMB8_YMinMenos1() {
        Alien.Bomb bomb = alien.new Bomb(150, -1);
        assertEquals(150, bomb.getX());
        assertEquals(0, bomb.getY());
    }


    @Test
    @DisplayName("BOMB-9: y = min (150, 0 -> x=150, y=0, imagen visible)")
    public void testBOMB9_YMin() {
        Alien.Bomb bomb = alien.new Bomb(150, 0);
        assertEquals(150, bomb.getX());
        assertEquals(0, bomb.getY());
    }

    @Test
    @DisplayName("BOMB-10: y = min + 1 (150, 1 -> x=150, y=1, imagen visible)")
    public void testBOMB10_YMinMas1() {
        Alien.Bomb bomb = alien.new Bomb(150, 1);
        assertEquals(150, bomb.getX());
        assertEquals(1, bomb.getY());
    }

    @Test
    @DisplayName("BOMB-11: y = max - 1 (150, 349 -> x=150, y=349, imagen visible)")
    public void testBOMB11_YMaxMenos1() {
        Alien.Bomb bomb = alien.new Bomb(150, Commons.BOARD_HEIGHT - 1);
        assertEquals(150, bomb.getX());
        assertEquals(Commons.BOARD_HEIGHT - 1, bomb.getY());
    }

    @Test
    @DisplayName("BOMB-12: y = max (150, 350 -> x=150, y=350, imagen visible)")
    public void testBOMB12_YMax() {
        Alien.Bomb bomb = alien.new Bomb(150, Commons.BOARD_HEIGHT);
        assertEquals(150, bomb.getX());
        assertEquals(Commons.BOARD_HEIGHT, bomb.getY());
    }


    @Test
    @DisplayName("BOMB-13: y = max + 1 (150, 351 -> x=150, y=350, imagen visible)")
    public void testBOMB13_YMaxMas1() {
        Alien.Bomb bomb = alien.new Bomb(150, Commons.BOARD_HEIGHT + 1);
        assertEquals(150, bomb.getX());
        assertEquals(Commons.BOARD_HEIGHT, bomb.getY());
    }
}