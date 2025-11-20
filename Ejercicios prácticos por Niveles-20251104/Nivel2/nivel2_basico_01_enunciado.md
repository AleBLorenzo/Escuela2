# Nivel 2 - Ejercicio Básico 1: Gestor Simple de Puntuaciones

**Dificultad:** ⭐ Básico  
**Tema:** Sistema de puntuaciones de arcade  
**Tiempo estimado:** 30 minutos

## Contexto

Estás desarrollando un juego de arcade retro. Necesitas un sistema simple para guardar y leer las puntuaciones más altas. Cada puntuación tiene dos datos:
- **Puntos** (int): La puntuación obtenida
- **Nivel** (int): El nivel alcanzado por el jugador

## Objetivo

Crear una clase `GestorPuntuaciones` que permita:
1. Guardar una puntuación en un archivo binario
2. Leer la puntuación guardada y mostrarla en consola

## Especificaciones Técnicas

### Método 1: guardarPuntuacion
```java
public static void guardarPuntuacion(String archivo, int puntos, int nivel) 
        throws IOException
```

**Funcionalidad:**
- Crea/sobrescribe el archivo especificado
- Escribe los puntos como un `int` (4 bytes)
- Escribe el nivel como un `int` (4 bytes)
- El archivo resultante debe tener **exactamente 8 bytes**
- Muestra mensaje de confirmación: "✓ Puntuación guardada correctamente"

**Clases a usar:**
- `FileOutputStream` para el archivo
- `DataOutputStream` para escribir enteros

### Método 2: leerPuntuacion
```java
public static void leerPuntuacion(String archivo) throws IOException
```

**Funcionalidad:**
- Abre el archivo especificado
- Lee los puntos (primer int de 4 bytes)
- Lee el nivel (segundo int de 4 bytes)
- Muestra en consola: `"Puntuación: [puntos] puntos - Nivel: [nivel]"`

**Clases a usar:**
- `FileInputStream` para el archivo
- `DataInputStream` para leer enteros

## Ejemplo de Uso

```java
// Guardar una puntuación
guardarPuntuacion("highscore.dat", 15000, 7);

// Leer la puntuación
leerPuntuacion("highscore.dat");
```

**Salida esperada:**
```
✓ Puntuación guardada correctamente
Puntuación: 15000 puntos - Nivel: 7
```

## Casos de Prueba

Tu programa debe probar:

1. **Guardar y leer una puntuación inicial:**
   - Guardar: 15000 puntos, nivel 7
   - Leer y verificar que se muestra correctamente

2. **Actualizar la puntuación (sobrescribir):**
   - Guardar: 25000 puntos, nivel 12
   - Leer y verificar que la anterior se sobrescribió

3. **Verificar tamaño del archivo:**
   - Usar `File.length()` para verificar que el archivo tiene exactamente 8 bytes

## Pistas

1. **Para escribir:**
   ```java
   DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo));
   dos.writeInt(puntos);
   dos.writeInt(nivel);
   dos.close(); // O mejor: usa try-with-resources
   ```

2. **Para leer:**
   ```java
   DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
   int puntos = dis.readInt();
   int nivel = dis.readInt();
   dis.close(); // O mejor: usa try-with-resources
   ```

3. **Try-with-resources (recomendado):**
   ```java
   try (DataOutputStream dos = new DataOutputStream(...)) {
       // Tu código aquí
   } // Se cierra automáticamente
   ```

4. **El orden importa:** Debes leer en el mismo orden que escribiste

## Preguntas para Reflexionar

1. ¿Qué pasa si intentas leer un archivo que no existe?
2. ¿Qué sucede si intentas leer primero el nivel y luego los puntos?
3. ¿Por qué el archivo ocupa exactamente 8 bytes?
4. ¿Qué diferencia hay entre usar `FileOutputStream` directamente vs `DataOutputStream`?

## Criterios de Evaluación

- ✅ **Funcionalidad:** Los métodos funcionan correctamente
- ✅ **Uso correcto de DataStreams:** Usa `writeInt()` y `readInt()`
- ✅ **Manejo de excepciones:** Usa try-catch o throws IOException
- ✅ **Tamaño del archivo:** Exactamente 8 bytes
- ✅ **Orden de lectura:** Lee en el mismo orden que escribió
- ✅ **Cierre de recursos:** Los streams se cierran apropiadamente

## Extensiones Opcionales

Si terminas antes, intenta:

1. **Validación:** Verifica que puntos y nivel sean positivos antes de guardar
2. **Método adicional:** Crea `boolean existePuntuacion(String archivo)` que verifique si hay datos guardados
3. **Comparación:** Agrega un método que compare si una nueva puntuación es mayor que la guardada
4. **Formato mejorado:** Muestra los puntos con separadores de miles (ej: "15,000")

## Entregables

- Archivo `GestorPuntuaciones.java` con:
  - Los dos métodos implementados
  - Un método `main` con los casos de prueba
  - Manejo apropiado de excepciones

¡Buena suerte! 🎮
