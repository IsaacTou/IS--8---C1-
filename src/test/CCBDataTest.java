package src.test;

import src.main.model.CCBData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

public class CCBDataTest {
    
    private CCBData ccbData;
    private String testFilePath;
    
    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        testFilePath = tempDir.resolve("testData.txt").toString();
        ccbData = new CCBData(testFilePath);
    }
    
    @Test
    @DisplayName("Cálculo CCB con valores válidos")
    void testCalcularCCBValoresValidos() {
        float resultado = ccbData.calcularCCB(1000f, 2000f, 50, 10f);
        assertEquals(66f, resultado, 0.001, "El cálculo no es correcto");
    }
    
    @Test
    @DisplayName("Cálculo CCB con costos fijos cero")
    void testCalcularCCBCostosFijosCero() {
        float resultado = ccbData.calcularCCB(0f, 2000f, 50, 10f);
        assertEquals(44f, resultado, 0.001, "El cálculo con costos fijos cero no es correcto");
    }
    
    @Test
    @DisplayName("Cálculo CCB con costos variables cero")
    void testCalcularCCBCostosVariablesCero() {
        float resultado = ccbData.calcularCCB(1000f, 0f, 50, 10f);
        assertEquals(22f, resultado, 0.001, "El cálculo con costos variables cero no es correcto");
    }
    
    @Test
    @DisplayName("Cálculo CCB con merma cero")
    void testCalcularCCBMermaCero() {
        float resultado = ccbData.calcularCCB(1000f, 2000f, 50, 0f);
        assertEquals(60f, resultado, 0.001, "El cálculo con merma cero no es correcto");
    }
    
    @Test
    @DisplayName("Puede cargar cuando no hay fecha previa")
    void testPuedeCargarSinFechaPrevia() {
        assertTrue(ccbData.puedeCargar(), "Debería permitir carga si no hay fecha previa");
    }
    
    @Test
    @DisplayName("No puede cargar cuando ya se cargó hoy")
    void testNoPuedeCargarMismoDia() throws IOException {
        ccbData.guardarDatos("123.45", testFilePath);
        assertFalse(ccbData.puedeCargar(), "No debería permitir carga si ya se cargó hoy");
    }
    
    @Test
    @DisplayName("Guardar datos válidos")
    void testGuardarDatosValidos() throws IOException {
        ccbData.guardarDatos("123.45", testFilePath);
        assertEquals(LocalDate.now(), ccbData.getFechaUltimaCarga());
        assertEquals("123.45", ccbData.getDatos());
    }
    
    @Test
    @DisplayName("Guardar datos vacíos")
    void testGuardarDatosVacios() throws IOException {
        ccbData.guardarDatos("", testFilePath);
        assertEquals("", ccbData.getDatos(), "Debería permitir guardar datos vacíos");
    }
    
    @Test
    @DisplayName("Error al guardar dos veces el mismo día")
    void testGuardarDatosDosVecesMismoDia() throws IOException {
        ccbData.guardarDatos("123.45", testFilePath);
        assertThrows(IOException.class, () -> {
            ccbData.guardarDatos("678.90", testFilePath);
        }, "Debería lanzar IOException al intentar cargar dos veces el mismo día");
    }
    
    @Test
    @DisplayName("Obtener CCB anterior con datos nulos")
    void testGetCCBAnteriorDatosNulos() {
        assertNull(ccbData.getCCBAnterior(), "Debería retornar null cuando no hay datos");
    }
    
    @Test
    @DisplayName("Obtener CCB anterior con datos vacíos")
    void testGetCCBAnteriorDatosVacios() throws IOException {
        ccbData.guardarDatos("", testFilePath);
        assertNull(ccbData.getCCBAnterior(), "Debería retornar null cuando los datos están vacíos");
    }
    
    @Test
    @DisplayName("Obtener CCB anterior con datos inválidos")
    void testGetCCBAnteriorDatosInvalidos() throws IOException {
        ccbData.guardarDatos("no es un número", testFilePath);
        assertNull(ccbData.getCCBAnterior(), "Debería retornar null cuando los datos no son numéricos");
    }
    
    @Test
    @DisplayName("Obtener CCB anterior con datos válidos")
    void testGetCCBAnteriorDatosValidos() throws IOException {
        ccbData.guardarDatos("123.45", testFilePath);
        assertEquals(123.45f, ccbData.getCCBAnterior(), 0.001, "Debería retornar el valor numérico correcto");
    }
    
    @Test
    @DisplayName("Constructor con ruta de archivo válida")
    void testConstructorRutaValida() {
        assertNotNull(ccbData, "Debería crearse la instancia con una ruta válida");
    }
    
    @Test
    @DisplayName("Carga inicial con archivo inexistente")
    void testCargaInicialArchivoInexistente() {
        CCBData data = new CCBData("ruta/inexistente.txt");
        assertNull(data.getDatos(), "Debería tener datos nulos si el archivo no existe");
    }
}