package src.test;
import src.main.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    
    private User user;
    private final String testCi = "12345678";
    private final String testUser = "testUser";
    private final String testUserType = "admin";
    private final String testWallet = "100.50";
    
    @BeforeEach
    public void setUp() {
        user = new User(testCi, testUser, testUserType, testWallet);
    }
    
    @Test
    public void testUserCreation() {
        assertNotNull(user, "El objeto User no debería ser null");
    }
    
    @Test
    public void testGetCi() {
        assertEquals(testCi, user.getCi(), "El CI no coincide");
    }
    
    @Test
    public void testGetUser() {
        assertEquals(testUser, user.getUser(), "El nombre de usuario no coincide");
    }
    
    @Test
    public void testGetUserType() {
        assertEquals(testUserType, user.getUserType(), "El tipo de usuario no coincide");
    }
    
    @Test
    public void testGetWallet() {
        assertEquals(testWallet, user.getWallet(), "El valor de la wallet no coincide");
    }
    
    // Prueba adicional para verificar que los valores no son null
    @Test
    public void testNoNullFields() {
        assertAll("Todos los campos deberían tener valores",
            () -> assertNotNull(user.getCi()),
            () -> assertNotNull(user.getUser()),
            () -> assertNotNull(user.getUserType()),
            () -> assertNotNull(user.getWallet())
        );
    }

    // Prueba paara verificar que la CI tenga solo digitos
    @Test
    public void testCiFormat() {
        assertTrue(user.getCi().matches("\\d+"), "El CI debe contener solo dígitos");
    }
}
