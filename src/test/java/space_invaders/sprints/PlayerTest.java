package space_invaders.sprints;

import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    private void setPlayerDx(int dx) throws Exception {
        Field field = player.getClass().getSuperclass().getDeclaredField("dx");
        field.setAccessible(true);
        field.setInt(player, dx);
    }

    private int getPlayerDx() throws Exception{
        Field field = player.getClass().getSuperclass().getDeclaredField("dx");
        field.setAccessible(true);
        return field.getInt(player);
    }

    private KeyEvent crearKeyEvent(int id, int keyCode) {
        Component component = new JPanel();
        return new KeyEvent(component, id, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
    }


    // ==================== PRUEBAS DEL MÉTODO act() ====================

    @Test
    @DisplayName("BVT-1: Límite inferior (x = 0, dx = -2 -> x = 0)")
    public void testBVT1_XMinimoDxNegativo() throws Exception {
        player.setX(0);
        setPlayerDx(-2);
        player.act();
        assertEquals(0, player.getX());
    }

    @Test
    @DisplayName("BVT-2: Límite inferior + 1 (x = 1, dx = -2 -> x = 0)")
    public void testBVT2_XIntermedioIzquierdaDxNegativo() throws Exception {
        player.setX(1);
        setPlayerDx(-2);
        player.act();
        assertEquals(0, player.getX());
    }


    @Test
    @DisplayName("BVT-3: Nominal (x = 179, dx = -2 -> x = 177)")
    public void testBVT3_XCentroDxNegativo() throws Exception {
        player.setX(179);
        setPlayerDx(-2);
        player.act();
        assertEquals(177, player.getX());
    }

    @Test
    @DisplayName("BVT-4: Límite superior - 1 (x = 357, dx = 2 -> x = 358)")
    public void testBVT4_XIntermedioDerechaDxPositivo() throws Exception {
        player.setX(357);
        setPlayerDx(2);
        player.act();
        assertEquals(Commons.BOARD_WIDTH, player.getX());
    }

    @Test
    @DisplayName("BVT-5: Límite superior (x = 358, dx = 2 -> x = 358)")
    public void testBVT5_XMaximoDxPositivo() throws Exception {
        player.setX(Commons.BOARD_WIDTH);
        setPlayerDx(2);
        player.act();
        assertEquals(Commons.BOARD_WIDTH, player.getX());
    }

    // ==================== PRUEBAS DEL MÉTODO keyPressed(KeyEvent) ====================

    @Test
    @DisplayName("KP-1: Pulsar flecha izquierda (VK_LEFT) con dx=0 -> dx=-2")
    public void testKP1_TeclaIzquierda() throws Exception {
        setPlayerDx(0);
        KeyEvent event = crearKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_LEFT);
        player.keyPressed(event);
        assertEquals(-2, getPlayerDx());
    }

    @Test
    @DisplayName("KP-2: Pulsar flecha derecha (VK_RIGHT) con dx=0 -> dx=2")
    public void testKP2_TeclaDerecha() throws Exception {
        setPlayerDx(0);
        KeyEvent event = crearKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_RIGHT);
        player.keyPressed(event);
        assertEquals(2, getPlayerDx());
    }

    @Test
    @DisplayName("KP-3: Pulsar otra tecla (VK_SPACE) con dx=0 -> dx=0 (no cambia)")
    public void testKP3_TeclaEspacio() throws Exception {
        setPlayerDx(0);
        KeyEvent event = crearKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_SPACE);
        player.keyPressed(event);
        assertEquals(0, getPlayerDx());
    }

    @Test
    public void testKR1_TeclaIzquierdaLiberada() throws Exception {
        setPlayerDx(-2);
        KeyEvent event = crearKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_LEFT);
        player.keyReleased(event);
        assertEquals(0, getPlayerDx());
    }

    @Test
    public void testKR2_TeclaDerechaLiberada() throws Exception {
        setPlayerDx(2);
        KeyEvent event = crearKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_RIGHT);
        player.keyReleased(event);
        assertEquals(0, getPlayerDx());
    }

    @Test
    public void testKR3_TeclaEspacioLiberada() throws Exception {
        setPlayerDx(2);
        KeyEvent event = crearKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_SPACE);
        player.keyReleased(event);
        assertEquals(2, getPlayerDx());
    }

    @Test
    public void testINIT1_PosicionInicial() {
        assertEquals(Commons.BOARD_WIDTH / 2, player.getX());
        assertEquals(Commons.GROUND - 10, player.getY());
    }


}