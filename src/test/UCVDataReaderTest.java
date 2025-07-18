package src.test;
import src.main.model.UCVDataReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test; // Importa @Test de JUnit 5
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@DisplayName("Pruebas para UCVDataReader")
class UCVDataReaderTest {

    private UCVDataReader reader;
    private File tempDbFile; // Archivo temporal para la base de datos de prueba

    @BeforeEach
    public void setUp() throws IOException {
        // Crear un archivo temporal con datos de prueba CONTROLADOS
        tempDbFile = File.createTempFile("UCVTestDB", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempDbFile))) {
            writer.write("31065844,Estudiante\n"); 
            writer.write("12345678,Profesor\n");
            writer.write("98765432,Admin\n");
        }

        reader = new UCVDataReader(tempDbFile.getAbsolutePath());
    }
    

    @AfterEach
    public void tearDown() {
        // Asegurarse de que el archivo temporal se elimine después de cada prueba
        if (tempDbFile != null && tempDbFile.exists()) {
            tempDbFile.delete();
        }
    }

    @Test
    @DisplayName("Debe devolver true si la CI existe y establecer el tipo de usuario correcto")
    public void testFindCi_TrueAndGetUserType() {
        // Prueba 1: CI existente y tipo esperado "Estudiante"
        String ciExistente1 = "31065844";
        assertTrue(reader.findCi(ciExistente1), "La CI '" + ciExistente1 + "' debería existir.");
        assertEquals("Estudiante", reader.getUserType(), "El tipo de usuario para '" + ciExistente1 + "' debería ser 'Estudiante'.");

        // Prueba 2: Otra CI existente y tipo esperado "Profesor"
        String ciExistente2 = "12345678";
        assertTrue(reader.findCi(ciExistente2), "La CI '" + ciExistente2 + "' debería existir.");
        assertEquals("Profesor", reader.getUserType(), "El tipo de usuario para '" + ciExistente2 + "' debería ser 'Profesor'.");

        // Prueba 3: CI existente y tipo esperado "Admin"
        String ciExistente3 = "98765432";
        assertTrue(reader.findCi(ciExistente3), "La CI '" + ciExistente3 + "' debería existir.");
        assertEquals("Admin", reader.getUserType(), "El tipo de usuario para '" + ciExistente3 + "' debería ser 'Admin'.");
    }

    @Test
    @DisplayName("Debe devolver false si la CI NO existe y userType debe estar vacío")
    public void testFindCi_FalseAndGetUserTypeEmpty() {
        String ciNoExistente = "99999999";
        assertFalse(reader.findCi(ciNoExistente), "La CI '" + ciNoExistente + "' no debería existir.");
        assertEquals("", reader.getUserType(), "El userType debería ser una cadena vacía para una CI no existente.");
    }

    @Test
    @DisplayName("Debe manejar CI vacía o nula, devolviendo false y userType vacío")
    public void testFindCi_EmptyOrNullCi() {
        // Probar con CI vacía
        assertFalse(reader.findCi(""), "Buscar CI vacía debería devolver false.");
        assertEquals("", reader.getUserType(), "El userType debería estar vacío para CI vacía.");

        // Probar con CI nula
        assertFalse(reader.findCi(null), "Buscar CI nula debería devolver false.");
        assertEquals("", reader.getUserType(), "El userType debería estar vacío para CI nula.");
    }

}