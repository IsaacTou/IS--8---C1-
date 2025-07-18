package src.test;
import src.main.model.SCUDataManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SCUDataManagerTest {
    
    private SCUDataManager dataManager, mockManager;
    private final String testCi = "12345678";
    private final String testUser = "testUser";
    private final String testUserType = "admin";
    private final String testWallet = "100.50";
    
    @BeforeEach
    public void setUp() {
        dataManager = new SCUDataManager();
        mockManager = new SCUDataManager("tmp");
    }
    
    @Test
    public void testAccountCreation() {
        assertDoesNotThrow( () -> 
            mockManager.createAccount(testCi, testUser, testUserType, testWallet),
            "Hubo un error al tratar de crear la cuenta"
        );
    }
    
    @Test
    public void testInvalidOperation() {
        int USER_TAKEN = 0;
        int ALREADY_REGISTER = 2;
        int NOT_INVALID_OPERATIONS = 3;
        assertEquals(NOT_INVALID_OPERATIONS, dataManager.invalidOperation(testCi, testUser), "Este C.I. no deberia estar registrado en el sistema, ni su nombre tomado.");
        assertEquals(USER_TAKEN, dataManager.invalidOperation("31000000", "Isaac"), "Este nombre deberia estar registrado en el sistema.");
        assertEquals(ALREADY_REGISTER, dataManager.invalidOperation("31065844", "Ramon"), "Este C.I. deberia estar registrado en el sistema.");
    }
    
    @Test
    public void testUserExist() {
        assertEquals(false, dataManager.userExist(testUser), "El nombre de usuario no deberia estar registrado");
        assertEquals(true, dataManager.userExist("Isaac"), "El nombre de usuario deberia estar registrado");
    }
    
    @Test
    public void testCorrectPassword() {
        dataManager.userExist("Isaac");
        assertEquals(false, dataManager.correctPassword("Isaac", "2004"), "La contraseña no debería ser correcta");
        assertEquals(true, dataManager.correctPassword("Isaac", "2005"), "La contraseña debería ser correcta");
    }
    
    @Test
    public void testGetCi() {
        mockManager.userExist(testUser);
        assertEquals(testCi, mockManager.getCi(), "El valor de la Ci no coincide");
    }

    @Test
    public void testGetUser() {
        mockManager.userExist(testUser);
        assertEquals(testUser, mockManager.getUser(), "El nombre de usuario no coincide");
    }

    @Test
    public void testGetUserType() {
        mockManager.userExist(testUser);
        assertEquals(testUserType, mockManager.getUserType(), "El tipo de usuario no coincide");
    }

    @Test
    public void testGetWallet() {
        mockManager.userExist(testUser);
        assertEquals("0", mockManager.getWallet(), "El valor del monedero no coincide");
    }
    
    // Prueba adicional para verificar que los valores no son null
    @Test
    public void testNoNullFields() {
        mockManager.userExist(testUser);
        assertAll("Todos los campos deberían tener valores",
            () -> assertNotNull(mockManager.getCi()),
            () -> assertNotNull(mockManager.getUser()),
            () -> assertNotNull(mockManager.getUserType()),
            () -> assertNotNull(mockManager.getWallet())
        );
    }

    // Prueba para verificar que la CI tenga solo digitos
    @Test
    public void testCiFormat() {
        mockManager.userExist(testUser);
        assertTrue(mockManager.getCi().matches("\\d+"), "El CI debe contener solo dígitos");
    }
}