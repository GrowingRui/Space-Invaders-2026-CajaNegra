package space_invaders.sprints;

import main.Commons;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShotTest {

    private static final int H_SPACE = 6;
    private static final int V_SPACE = 1;

    // ==================== PRUEBAS DE initShot(int x, int y) - EJE X ====================

    @Test
    @DisplayName("SHOT-1: Nominal (150, 280 -> 150 + H_SPACE, 280 - V_SPACE)")
    public void testShot1Nominal() {
        Shot shot = new Shot(150, 280);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    @DisplayName("SHOT-2: x = min (0, 280 -> 0 + H_SPACE, 280 - V_SPACE)")
    public void testShot2XMin() {
        Shot shot = new Shot(0, 280);
        assertEquals(0 + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    @DisplayName("SHOT-3: x = min + 1 (1, 280 -> 1 + H_SPACE, 280 - V_SPACE)")
    public void testShot3XMinMasUno() {
        Shot shot = new Shot(1, 280);
        assertEquals(1 + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    @DisplayName("SHOT-4: x = max - 1 (BOARD_WIDTH - 1, 280 -> (BOARD_WIDTH - 1) + H_SPACE, 280 - V_SPACE)")
    public void testShot4XMaxMenosUno() {
        Shot shot = new Shot(Commons.BOARD_WIDTH - 1, 280);
        assertEquals((Commons.BOARD_WIDTH - 1) + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    @DisplayName("SHOT-5: x = max (BOARD_WIDTH, 280 -> BOARD_WIDTH + H_SPACE, 280 - V_SPACE)")
    public void testShot5XMax() {
        Shot shot = new Shot(Commons.BOARD_WIDTH, 280);
        assertEquals(Commons.BOARD_WIDTH + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    // ==================== PRUEBAS DE initShot(int x, int y) - EJE Y ====================

    @Test
    @DisplayName("SHOT-6: y = min (150, 0 -> 150 + H_SPACE, 0 - V_SPACE)")
    public void testShot6YMin() {
        Shot shot = new Shot(150, 0);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(0 - V_SPACE, shot.getY());
    }

    @Test
    @DisplayName("SHOT-7: y = min + 1 (150, 1 -> 150 + H_SPACE, 1 - V_SPACE)")
    public void testShot7YMinMasUno() {
        Shot shot = new Shot(150, 1);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(1 - V_SPACE, shot.getY());
    }

    @Test
    @DisplayName("SHOT-8: y = max - 1 (150, BOARD_HEIGHT - 1 -> 150 + H_SPACE, (BOARD_HEIGHT - 1) - V_SPACE)")
    public void testShot8YMaxMenosUno() {
        Shot shot = new Shot(150, Commons.BOARD_HEIGHT - 1);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals((Commons.BOARD_HEIGHT - 1) - V_SPACE, shot.getY());
    }

    @Test
    @DisplayName("SHOT-9: y = max (150, BOARD_HEIGHT -> 150 + H_SPACE, BOARD_HEIGHT - V_SPACE)")
    public void testShot9YMax() {
        Shot shot = new Shot(150, Commons.BOARD_HEIGHT);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(Commons.BOARD_HEIGHT - V_SPACE, shot.getY());
    }
}
