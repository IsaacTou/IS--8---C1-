// src/test/java/src/main/model/SesionUserTest.java
package src.test;

import src.main.model.SesionUser;
import src.main.model.User;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas para el Singleton SesionUser")
public class SesionUserTest {


    @BeforeEach
    public void setUp() {
        SesionUser.getInstance().logout();
    }

    // Este método se ejecuta después de CADA prueba.
    @AfterEach
    public void tearDown() {
        SesionUser.getInstance().logout();
    }

    @Test
    @DisplayName("Debería devolver la misma instancia (Singleton)")
    public void testGetInstanceReturnsSameInstance() {
        SesionUser instance1 = SesionUser.getInstance();
        SesionUser instance2 = SesionUser.getInstance();
        SesionUser instance3 = SesionUser.getInstance();
        
        // Verificar que todas las llamadas a getInstance() devuelven el mismo objeto
        assertSame(instance1, instance2, "instance1 y instance2 deberían ser la misma instancia");
        assertSame(instance1, instance3, "instance1 y instance3 deberían ser la misma instancia");
    }

    @Test
    @DisplayName("Debería establecer y obtener el usuario correctamente")
    public void testSetAndGetUser() {
        User testUser = new User("31065844", "Isaac", "Estudiante", "0");
        SesionUser.getInstance().setSesionUser(testUser);

        User retrievedUser = SesionUser.getInstance().getUser();

        assertNotNull(retrievedUser, "El usuario recuperado no debería ser nulo");

        assertEquals("31065844", retrievedUser.getCi(), "La cédula del usuario debería coincidir");
        assertEquals("Isaac", retrievedUser.getUser(), "El nombre de usuario debería coincidir");
        assertEquals("Estudiante", retrievedUser.getUserType(), "El tipo de usuario debería coincidir");
        assertEquals("95.905", retrievedUser.getWallet(), "El saldo del usuario debería coincidir");
    }

    @Test
    @DisplayName("Debería estar logueado después de establecer un usuario")
    public void testIsUserLoggedInAfterSet() {
        User testUser = new User("31065844", "Isaac", "Estudiante", "0");
        SesionUser.getInstance().setSesionUser(testUser);

        assertTrue(SesionUser.getInstance().getUser() != null, "El usuario debería estar logueado");
    }

    @Test
    @DisplayName("Debería cerrar la sesión correctamente")
    public void testLogout() {

        User testUser = new User("31065844", "Isaac", "Estudiante", "0");
        SesionUser.getInstance().setSesionUser(testUser);
        assertNotNull(SesionUser.getInstance().getUser(), "El usuario debería estar logueado antes de cerrar sesión");


        SesionUser.getInstance().logout();

        assertNull(SesionUser.getInstance().getUser(), "El usuario debería ser nulo después de cerrar sesión");
    }

    @Test
    @DisplayName("Debería devolver nulo si no hay usuario logueado")
    public void testGetUserWhenNotLoggedIn() {

        assertNull(SesionUser.getInstance().getUser(), "El usuario debería ser nulo al inicio sin login");
    }

}
