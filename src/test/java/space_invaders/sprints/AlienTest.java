package space_invaders.sprints;

import main.Commons;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlienTest {

    // ==================== PRUEBAS DE initAlien(int x, int y) - EJE X ====================

    @Test
    @DisplayName("ALIEN-1: Nominal (150, 150 -> x=150, y=150)")
    public void testAlien1Nominal() {
        Alien alien = new Alien(150, 150);
        assertEquals(150, alien.getX());
        assertEquals(150, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-2: x = min - 1 (-1, 150 -> x=0, y=150)")
    public void testAlien2XMinMenosUno() {
        Alien alien = new Alien(-1, 150);
        assertEquals(0, alien.getX());
        assertEquals(150, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-3: x = min (0, 150 -> x=0, y=150)")
    public void testAlien3XMin() {
        Alien alien = new Alien(0, 150);
        assertEquals(0, alien.getX());
        assertEquals(150, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-4: x = min + 1 (1, 150 -> x=1, y=150)")
    public void testAlien4XMinMasUno() {
        Alien alien = new Alien(1, 150);
        assertEquals(1, alien.getX());
        assertEquals(150, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-5: x = max - 1 (BOARD_WIDTH - 1, 150 -> x=BOARD_WIDTH - 1, y=150)")
    public void testAlien5XMaxMenosUno() {
        Alien alien = new Alien(Commons.BOARD_WIDTH - 1, 150);
        assertEquals(Commons.BOARD_WIDTH - 1, alien.getX());
        assertEquals(150, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-6: x = max (BOARD_WIDTH, 150 -> x=BOARD_WIDTH, y=150)")
    public void testAlien6XMax() {
        Alien alien = new Alien(Commons.BOARD_WIDTH, 150);
        assertEquals(Commons.BOARD_WIDTH, alien.getX());
        assertEquals(150, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-7: x = max + 1 (BOARD_WIDTH + 1, 150 -> x=BOARD_WIDTH, y=150)")
    public void testAlien7XMaxMasUno() {
        Alien alien = new Alien(Commons.BOARD_WIDTH + 1, 150);
        assertEquals(Commons.BOARD_WIDTH, alien.getX());
        assertEquals(150, alien.getY());
    }

    // ==================== PRUEBAS DE initAlien(int x, int y) - EJE Y ====================

    @Test
    @DisplayName("ALIEN-8: y = min - 1 (150, -1 -> x=150, y=0)")
    public void testAlien8YMinMenosUno() {
        Alien alien = new Alien(150, -1);
        assertEquals(150, alien.getX());
        assertEquals(0, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-9: y = min (150, 0 -> x=150, y=0)")
    public void testAlien9YMin() {
        Alien alien = new Alien(150, 0);
        assertEquals(150, alien.getX());
        assertEquals(0, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-10: y = min + 1 (150, 1 -> x=150, y=1)")
    public void testAlien10YMinMasUno() {
        Alien alien = new Alien(150, 1);
        assertEquals(150, alien.getX());
        assertEquals(1, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-11: y = max - 1 (150, BOARD_HEIGHT - 1 -> x=150, y=BOARD_HEIGHT - 1)")
    public void testAlien11YMaxMenosUno() {
        Alien alien = new Alien(150, Commons.BOARD_HEIGHT - 1);
        assertEquals(150, alien.getX());
        assertEquals(Commons.BOARD_HEIGHT - 1, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-12: y = max (150, BOARD_HEIGHT -> x=150, y=BOARD_HEIGHT)")
    public void testAlien12YMax() {
        Alien alien = new Alien(150, Commons.BOARD_HEIGHT);
        assertEquals(150, alien.getX());
        assertEquals(Commons.BOARD_HEIGHT, alien.getY());
    }

    @Test
    @DisplayName("ALIEN-13: y = max + 1 (150, BOARD_HEIGHT + 1 -> x=150, y=BOARD_HEIGHT)")
    public void testAlien13YMaxMasUno() {
        Alien alien = new Alien(150, Commons.BOARD_HEIGHT + 1);
        assertEquals(150, alien.getX());
        assertEquals(Commons.BOARD_HEIGHT, alien.getY());
    }

    // ==================== PRUEBAS DEL MÉTODO act(int direction) ====================

    @Test
    @DisplayName("ACT-A1: CE1 (direction < 0) - (150, 100) con direction = -1 -> (149, 100)")
    public void testActA1DirectionNegativa() {
        Alien alien = new Alien(150, 100);
        alien.act(-1);
        assertEquals(149, alien.getX());
        assertEquals(100, alien.getY());
    }

    @Test
    @DisplayName("ACT-A2: CE2 (direction = 0) - (150, 100) con direction = 0 -> (150, 100)")
    public void testActA2DirectionCero() {
        Alien alien = new Alien(150, 100);
        alien.act(0);
        assertEquals(150, alien.getX());
        assertEquals(100, alien.getY());
    }

    @Test
    @DisplayName("ACT-A3: CE3 (direction > 0) - (150, 100) con direction = 1 -> (151, 100)")
    public void testActA3DirectionPositiva() {
        Alien alien = new Alien(150, 100);
        alien.act(1);
        assertEquals(151, alien.getX());
        assertEquals(100, alien.getY());
    }
}