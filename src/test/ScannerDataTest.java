package src.test;

import java.io.File;

import src.main.model.*;

import org.junit.Test;	
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas para el Scanner Data")
public class ScannerDataTest {

	File invalid, valid;

	@BeforeEach
	public void setUp() {
		invalid = new File("invalid_route");
		valid = new File("src/main/data/FaceID/test.jpg");
		
	}

	@Test
	public void testIsEqualInvalidFiles() {
		assertEquals(
			ScannerData.isEqual(invalid, valid),
			false,
			"Los archivos son inválidos. Este test debería retornar false."
		);
		assertEquals(
			ScannerData.isEqual(valid, invalid),
			false,
			"El primer archivo es inválido. Este test debería retornar false."
		);
		assertEquals(
			ScannerData.isEqual(invalid, invalid),
			false,
			"El segundo archivo es inválido. Este test debería retornar false."
		);
	}

	@Test
	public void testIsEqual() {
		assertTrue(
			ScannerData.isEqual(valid, valid),
			"Una imagen debería ser igual a sí misma."
		);
	}
}
