package com.ejemplo;
package org.junit.jupiter.params;




import com.example.Calculadora;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class CalculadoraTest {

    private static Calculadora calc;

    @BeforeAll
    public static void initAll() {
        System.out.println("Inicializando todas las pruebas...\n");
        calc = new Calculadora();
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Antes de cada test");
    }

    @AfterEach
    public void endTest() {
        System.out.println("Después de cada test\n");
    }

    @AfterAll
    public static void endAll() {
        System.out.println("Pruebas finalizadas");
    }

    @Test
    @DisplayName("Test suma")
    public void testSuma() { // un test para comprobar la suma
        System.out.println("Test suma");
        assertEquals(4, calc.sumar(2, 2)); // salida esperada, salida obtenida
        assertEquals(5, calc.sumar(2, 2));
    }

    @ParameterizedTest
    @CsvSource({ // test se repetirá por cada grupo de parámetros
            "3, 1, 2",
            "10, 5, 5"
    })
    @DisplayName("Test suma parametrizada")
    public void testSumaParam(int esperado, int a, int b) {
        System.out.println("Test suma parametrizada");
        assertEquals(esperado, calc.sumar(a, b)); // salida esperada, salida obtenida
    }
}
