package src.test;

import src.main.model.User;
import src.main.model.SesionUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SesionUserTest {
    
    private SesionUser sesionUser;
    private User testUser;
    
    @BeforeEach
    public void setUp() {
        // Resetear la instancia singleton antes de cada prueba
        sesionUser = SesionUser.getInstance();
        sesionUser.logout(); // Asegurarse que comienza sin usuario
        
        testUser = new User("12345678", "testUser", "admin", "100.50");
    }
    
    @AfterEach
    public void tearDown() {
        // Limpiar después de cada prueba
        sesionUser.logout();
    }
    
    @Test
    public void testSingletonInstance() {
        SesionUser anotherInstance = SesionUser.getInstance();
        assertSame(sesionUser, anotherInstance, "Debería devolver la misma instancia singleton");
    }
    
    @Test
    public void testSetAndGetUser() {
        sesionUser.setSesionUser(testUser);
        User retrievedUser = sesionUser.getUser();
        
        assertNotNull(retrievedUser, "El usuario no debería ser null después de establecerse");
        assertEquals(testUser, retrievedUser, "El usuario recuperado debería ser igual al establecido");
        assertEquals("12345678", retrievedUser.getCi(), "El CI del usuario debería coincidir");
    }
    
    @Test
    public void testLogout() {
        sesionUser.setSesionUser(testUser);
        assertNotNull(sesionUser.getUser(), "Debería haber un usuario después de establecerse");
        
        sesionUser.logout();
        assertNull(sesionUser.getUser(), "El usuario debería ser null después de logout");
    }
    
    @Test
    public void testGetUserWhenNotLoggedIn() {
        assertNull(sesionUser.getUser(), "Debería devolver null cuando no hay usuario establecido");
    }
    
    @Test
    public void testMultipleSessions() {
        SesionUser firstInstance = SesionUser.getInstance();
        SesionUser secondInstance = SesionUser.getInstance();
        
        firstInstance.setSesionUser(testUser);
        
        assertEquals(testUser, secondInstance.getUser(), 
            "Ambas instancias deberían compartir el mismo estado de usuario");
    }
    
    @Test
    public void testSetNullUser() {
        sesionUser.setSesionUser(null);
        assertNull(sesionUser.getUser(), "Debería permitir establecer un usuario null");
    }
}