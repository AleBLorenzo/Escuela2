# 📝 EJERCICIO BÁSICO 2: Generador de Secuencias de Teclas

## 🎯 Objetivo

Crear un programa que **genere archivos binarios** con secuencias de bytes que representen **pulsaciones de teclas en un videojuego**.

Este ejercicio te enseñará a:
- Escribir archivos binarios byte a byte
- Convertir valores int a bytes
- Crear estructuras de datos binarias simples
- Generar archivos con patrones específicos

---

## 📋 Descripción del Ejercicio

Imagina que estás desarrollando un sistema de replay para un juego de lucha. Necesitas guardar las pulsaciones de teclas del jugador en un archivo binario compacto.

Cada tecla se representa con un byte (0-255) según esta tabla:

### **Mapeo de Teclas:**

| Tecla | Código (byte) | Hexadecimal | Descripción |
|-------|---------------|-------------|-------------|
| W | 87 | 0x57 | Arriba |
| A | 65 | 0x41 | Izquierda |
| S | 83 | 0x53 | Abajo |
| D | 68 | 0x44 | Derecha |
| J | 74 | 0x4A | Puño |
| K | 75 | 0x4B | Patada |
| L | 76 | 0x4C | Bloqueo |
| SPACE | 32 | 0x20 | Salto |
| SHIFT | 16 | 0x10 | Correr |
| ENTER | 13 | 0x0D | Pausa |

Tu programa debe crear un archivo binario con:

1. **Header (cabecera):** 8 bytes
   - Bytes 0-3: Magic number "KEYS" (0x4B 0x45 0x59 0x53)
   - Byte 4: Versión del formato (0x01)
   - Bytes 5-7: Reservados (0x00)

2. **Metadata:** 8 bytes
   - Bytes 0-3: Cantidad de pulsaciones (int, 4 bytes, big-endian)
   - Bytes 4-7: Duración total en milisegundos (int, 4 bytes, big-endian)

3. **Secuencia de pulsaciones:**
   - Cada pulsación = 1 byte con el código de la tecla
   - N bytes (según cantidad de pulsaciones)

4. **Footer (pie):** 4 bytes
   - Checksum simple: suma de todos los bytes de pulsaciones módulo 256

---

## 📥 Entrada

El programa debe solicitar al usuario:

1. **Nombre del archivo de salida** (ej: `combo1.replay`)
2. **Secuencia de teclas** como String (ej: `"WWAASSJJK"`)
   - Cada carácter representa una tecla pulsada
   - Ignorar caracteres no válidos con un warning

3. **Duración estimada** en milisegundos (ej: `1500` para 1.5 segundos)

---

## 📤 Salida Esperada

### **Ejemplo de ejecución:**

```
╔════════════════════════════════════════════════════════════════╗
║     GENERADOR DE SECUENCIAS DE TECLAS - REPLAY SYSTEM         ║
╚════════════════════════════════════════════════════════════════╝

Nombre del archivo de salida: combo_dragon.replay
Secuencia de teclas (ej: WWAASSJJK): DDJJKKKDSJL
Duración total (ms): 2000

--- PROCESANDO SECUENCIA ---
Tecla 'D' (Derecha) → 0x44
Tecla 'D' (Derecha) → 0x44
Tecla 'J' (Puño) → 0x4A
Tecla 'J' (Puño) → 0x4A
Tecla 'K' (Patada) → 0x4B
Tecla 'K' (Patada) → 0x4B
Tecla 'K' (Patada) → 0x4B
Tecla 'D' (Derecha) → 0x44
Tecla 'S' (Abajo) → 0x53
Tecla 'J' (Puño) → 0x4A
Tecla 'L' (Bloqueo) → 0x4C

--- ESCRIBIENDO ARCHIVO ---
✓ Header escrito (8 bytes)
✓ Metadata escrita (8 bytes)
✓ Secuencia escrita (11 bytes)
✓ Checksum escrito (1 byte): 0xE7

Archivo creado exitosamente: combo_dragon.replay
Tamaño total: 28 bytes

╔════════════════════════════════════════════════════════════════╗
║                    ESTRUCTURA DEL ARCHIVO                      ║
╠════════════════════════════════════════════════════════════════╣
║ Offset  | Contenido          | Descripción                    ║
╠════════════════════════════════════════════════════════════════╣
║ 0x0000  | 4B 45 59 53        | Magic: "KEYS"                  ║
║ 0x0004  | 01                 | Versión: 1                     ║
║ 0x0005  | 00 00 00           | Reservado                      ║
║ 0x0008  | 00 00 00 0B        | Cantidad: 11 pulsaciones       ║
║ 0x000C  | 00 00 07 D0        | Duración: 2000 ms              ║
║ 0x0010  | 44 44 4A 4A ...    | Secuencia de teclas            ║
║ 0x001B  | E7                 | Checksum: 0xE7                 ║
╚════════════════════════════════════════════════════════════════╝

¡Replay guardado! Úsalo para reproducir el combo.
```

---

## 🔧 Especificaciones Técnicas

### **Clases Java a usar:**
- `FileOutputStream` - Para escribir el archivo
- `Scanner` - Para entrada del usuario (opcional)

### **Conceptos importantes:**

1. **Escribir un byte individual:**
   ```java
   fos.write(0x4B); // Escribe el byte 0x4B
   ```

2. **Escribir un int como 4 bytes (big-endian):**
   ```java
   int valor = 12345;
   fos.write((valor >> 24) & 0xFF); // Byte más significativo
   fos.write((valor >> 16) & 0xFF);
   fos.write((valor >> 8) & 0xFF);
   fos.write(valor & 0xFF);         // Byte menos significativo
   ```

3. **Conversión char a byte código:**
   ```java
   char tecla = 'W';
   int codigo = (int) tecla; // ASCII de 'W' = 87
   ```

4. **Checksum simple:**
   ```java
   int suma = 0;
   for (byte b : secuencia) {
       suma += (b & 0xFF);
   }
   byte checksum = (byte) (suma & 0xFF);
   ```

5. **Magic numbers:**
   - Son secuencias de bytes al inicio de archivos para identificar el formato
   - "KEYS" = `0x4B 0x45 0x59 0x53`

---

## 💡 Pistas

1. **Estructura general:**
   ```
   1. Solicitar datos al usuario
   2. Validar la secuencia de teclas
   3. Abrir FileOutputStream
   4. Escribir header (8 bytes)
   5. Escribir metadata (8 bytes)
   6. Escribir cada tecla de la secuencia
   7. Calcular y escribir checksum
   8. Cerrar archivo
   9. Mostrar reporte
   ```

2. **Mapeo de teclas:**
   - Crea un método `int obtenerCodigoTecla(char tecla)`
   - Usa un switch o un Map<Character, Integer>
   - Devuelve -1 si la tecla no es válida

3. **Validación:**
   - Ignora teclas no válidas pero avisa al usuario
   - No permitas secuencias vacías

4. **Formato de salida:**
   - Usa `String.format()` para formatear hexadecimales
   - Ejemplo: `String.format("0x%02X", valor)`

---

## 🎓 Conocimientos que Practicarás

- ✅ Crear archivos binarios desde cero
- ✅ Escribir bytes individuales
- ✅ Escribir enteros como múltiples bytes (serialización manual)
- ✅ Diseñar formatos de archivo binarios
- ✅ Implementar headers y footers
- ✅ Calcular checksums
- ✅ Validación de entrada del usuario

---

## 🧪 Casos de Prueba

### **Caso 1: Combo simple**
- Secuencia: `"JJJKKK"`
- Duración: 500 ms
- Resultado: 6 pulsaciones, checksum calculado

### **Caso 2: Movimiento complejo**
- Secuencia: `"WWDDSSAAJKL"`
- Duración: 1200 ms
- Resultado: 11 pulsaciones

### **Caso 3: Con teclas inválidas**
- Secuencia: `"WWXXDDZZ"`
- Debe ignorar X y Z, avisar al usuario
- Resultado: solo 4 teclas válidas (W W D D)

### **Caso 4: Secuencia vacía**
- Secuencia: `""`
- Debe rechazarse o crear archivo mínimo válido

---

## 🔍 Verificación con HexDump

Después de crear el archivo, **visualízalo con la herramienta HexDump** para verificar:

```
HexDump.dump("combo_dragon.replay");
```

Deberías ver:
```
00000000  4B 45 59 53 01 00 00 00 00 00 00 0B 00 00 07 D0  KEYS............
00000010  44 44 4A 4A 4B 4B 4B 44 53 4A 4C E7              DDJJKKKDSJL.
```

Verifica:
- ✅ Primeros 4 bytes son "KEYS" (0x4B 0x45 0x59 0x53)
- ✅ Byte 4 es 0x01 (versión)
- ✅ Bytes 8-11 contienen cantidad (0x0000000B = 11)
- ✅ Bytes 12-15 contienen duración (0x000007D0 = 2000)
- ✅ Última byte es el checksum

---

## 🚀 Desafíos Adicionales (Opcional)

1. **Timestamps individuales:**
   - En lugar de duración total, guarda el timestamp de cada pulsación
   - Formato: 1 byte código + 2 bytes timestamp (0-65535 ms)

2. **Compresión simple:**
   - Si una tecla se repite N veces, guarda: byte especial (0xFF) + código tecla + cantidad
   - Ejemplo: "JJJJJ" → 0xFF 0x4A 0x05

3. **Modo lectura:**
   - Crea otro programa que **lea** el archivo .replay y muestre la secuencia

4. **Combos predefinidos:**
   - Define combos famosos (ej: "Hadouken" = DSDJ, "Shoryuken" = WDDJ)
   - Permite que el usuario escriba el nombre del combo

---

## 📚 Recursos

- Revisa `NIVEL1_TEORIA.md` para ejemplos de escritura con FileOutputStream
- Usa `HERRAMIENTA_HEX_DUMP.md` para verificar tus archivos
- Consulta la tabla ASCII para entender los códigos de teclas

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✅ Crear archivos binarios con la estructura especificada
- ✅ Escribir correctamente header, metadata, secuencia y checksum
- ✅ Validar la entrada del usuario (ignorar teclas inválidas)
- ✅ Calcular el checksum correctamente
- ✅ Mostrar un reporte claro de lo que se escribió
- ✅ Usar try-with-resources para cerrar el stream
- ✅ El archivo generado debe poder visualizarse con HexDump

---

**Tiempo estimado:** 45-60 minutos

**Dificultad:** ⭐⭐ Básica

**¡A programar ese replay system!** 🎮🥊
