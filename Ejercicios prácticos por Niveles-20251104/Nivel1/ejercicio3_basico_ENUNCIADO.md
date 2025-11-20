# 📝 EJERCICIO BÁSICO 3: Cifrador/Descifrador XOR de Savegames

## 🎯 Objetivo

Crear un programa que **copie un archivo aplicando cifrado XOR** byte a byte, perfecto para **proteger archivos de guardado (savegames)** de videojuegos.

Este ejercicio te enseñará a:
- Copiar archivos aplicando transformaciones
- Usar operador XOR (^) para cifrado simple
- Leer y escribir simultáneamente con buffers
- Entender cifrados simétricos (misma clave para cifrar y descifrar)

---

## 📋 Descripción del Ejercicio

El **cifrado XOR** es uno de los métodos más simples de cifrado. Su característica principal es que es **simétrico**: la misma operación sirve para cifrar y descifrar.

### **¿Cómo funciona XOR?**

```
Operación XOR (^):
0 ^ 0 = 0
0 ^ 1 = 1
1 ^ 0 = 1
1 ^ 1 = 0

Propiedades importantes:
- A ^ B ^ B = A  (aplicar XOR dos veces con la misma clave devuelve el original)
- A ^ 0 = A
- A ^ A = 0
```

**Ejemplo de cifrado:**
```
Byte original: 01001010 (0x4A, 'J')
Clave:         10101010 (0xAA)
Resultado XOR: 11100000 (0xE0) ← byte cifrado

Para descifrar:
Byte cifrado:  11100000 (0xE0)
Clave:         10101010 (0xAA)
Resultado XOR: 01001010 (0x4A, 'J') ← byte original!
```

---

## 🎮 Contexto: Proteger Savegames

Imagina que desarrollas un RPG y quieres:
- Proteger los archivos de guardado contra edición manual
- Ofuscar los datos para que no sean fácilmente legibles
- Hacer más difícil hacer trampa modificando el savegame

El cifrado XOR es perfecto para esto: simple, rápido, y suficiente para uso casual (no es criptográficamente seguro, pero es mejor que nada).

---

## 📥 Entrada

El programa debe aceptar **argumentos de línea de comandos** o **solicitar interactivamente**:

1. **Modo de operación:**
   - `cifrar` o `c` - Cifrar un archivo
   - `descifrar` o `d` - Descifrar un archivo

2. **Archivo de entrada:** Path al archivo original

3. **Archivo de salida:** Path donde guardar el resultado

4. **Clave de cifrado:** Un número entre 1-255 (byte usado como clave XOR)

---

## 📤 Salida Esperada

### **Ejemplo 1: Cifrar un savegame**

```
╔════════════════════════════════════════════════════════════════╗
║         CIFRADOR/DESCIFRADOR XOR - PROTECTOR DE SAVES          ║
╚════════════════════════════════════════════════════════════════╝

Modo: CIFRAR
Archivo entrada: savegame.dat
Archivo salida: savegame.encrypted
Clave XOR: 0xA7 (167)

--- PROCESANDO ---
Leyendo archivo de entrada...
Tamaño: 4096 bytes (4.00 KB)

Aplicando cifrado XOR...
[████████████████████████████████████████] 100%

--- RESULTADO ---
✓ Archivo cifrado correctamente
✓ Bytes procesados: 4096
✓ Archivo de salida: savegame.encrypted

--- VERIFICACIÓN ---
Primeros 16 bytes ORIGINALES:
53 41 56 45 01 00 00 00 48 65 72 6F 00 00 00 00

Primeros 16 bytes CIFRADOS:
F4 E6 F1 E2 A6 A7 A7 A7 EF C2 D5 C8 A7 A7 A7 A7

¡Cifrado completado! Para descifrar usa la misma clave: 0xA7
```

### **Ejemplo 2: Descifrar el savegame**

```
╔════════════════════════════════════════════════════════════════╗
║         CIFRADOR/DESCIFRADOR XOR - PROTECTOR DE SAVES          ║
╚════════════════════════════════════════════════════════════════╝

Modo: DESCIFRAR
Archivo entrada: savegame.encrypted
Archivo salida: savegame_restored.dat
Clave XOR: 0xA7 (167)

--- PROCESANDO ---
Leyendo archivo de entrada...
Tamaño: 4096 bytes (4.00 KB)

Aplicando descifrado XOR...
[████████████████████████████████████████] 100%

--- RESULTADO ---
✓ Archivo descifrado correctamente
✓ Bytes procesados: 4096
✓ Archivo de salida: savegame_restored.dat

--- VERIFICACIÓN ---
Primeros 16 bytes CIFRADOS:
F4 E6 F1 E2 A6 A7 A7 A7 EF C2 D5 C8 A7 A7 A7 A7

Primeros 16 bytes DESCIFRADOS:
53 41 56 45 01 00 00 00 48 65 72 6F 00 00 00 00

¡Descifrado completado! El archivo original ha sido restaurado.
```

---

## 🔧 Especificaciones Técnicas

### **Clases Java a usar:**
- `FileInputStream` - Leer archivo original
- `FileOutputStream` - Escribir archivo procesado
- `File` - Información del archivo

### **Conceptos importantes:**

1. **Operador XOR en Java:**
   ```java
   byte original = 0x4A;
   byte clave = (byte) 0xA7;
   byte cifrado = (byte) (original ^ clave);
   ```

2. **Procesamiento con buffer:**
   ```java
   byte[] buffer = new byte[8192];
   int bytesLeidos;
   
   while ((bytesLeidos = fis.read(buffer)) != -1) {
       // Aplicar XOR a cada byte del buffer
       for (int i = 0; i < bytesLeidos; i++) {
           buffer[i] ^= clave;
       }
       fos.write(buffer, 0, bytesLeidos);
   }
   ```

3. **Conversión byte ↔ int:**
   ```java
   // De int a byte
   int claveInt = 167;
   byte claveByte = (byte) claveInt;
   
   // De byte a int (sin signo)
   byte b = -89; // 0xA7 en byte con signo
   int valorSinSigno = b & 0xFF; // 167
   ```

4. **Simetría del XOR:**
   - Cifrar: `byteCifrado = byteOriginal ^ clave`
   - Descifrar: `byteOriginal = byteCifrado ^ clave`
   - ¡Misma operación para ambas!

---

## 💡 Pistas

1. **Estructura general:**
   ```
   1. Solicitar/leer parámetros (modo, archivos, clave)
   2. Validar entradas
   3. Abrir FileInputStream y FileOutputStream
   4. Crear buffer (8KB recomendado)
   5. Leer chunk del archivo
   6. Aplicar XOR a cada byte
   7. Escribir chunk procesado
   8. Repetir hasta EOF
   9. Mostrar resultados
   ```

2. **Validación de clave:**
   - Debe estar entre 1 y 255
   - No uses 0 (XOR con 0 no hace nada: `A ^ 0 = A`)

3. **Barra de progreso (opcional):**
   ```java
   long totalBytes = archivoEntrada.length();
   long procesados = 0;
   // Actualizar cada cierto % de avance
   ```

4. **Mostrar primeros bytes:**
   - Guarda los primeros 16 bytes antes y después de XOR
   - Úsalos para verificación visual

5. **Manejo de archivos grandes:**
   - Con buffer de 8KB, un archivo de 10MB se procesa en ~1280 lecturas
   - No intentes cargar todo el archivo en memoria

---

## 🎓 Conocimientos que Practicarás

- ✅ Copiar archivos con transformación
- ✅ Usar operador XOR (^) bit a bit
- ✅ Procesar archivos en chunks con buffer
- ✅ Leer y escribir simultáneamente
- ✅ Manejar archivos de cualquier tamaño
- ✅ Entender cifrado simétrico
- ✅ Validación de parámetros de entrada

---

## 🧪 Casos de Prueba

### **Caso 1: Archivo de texto pequeño**
1. Crear archivo: `echo "Hello World" > test.txt`
2. Cifrar con clave 123: `java CifradorXOR c test.txt test.enc 123`
3. Ver con HexDump que está cifrado
4. Descifrar: `java CifradorXOR d test.enc test_restored.txt 123`
5. Verificar: `diff test.txt test_restored.txt` (deben ser idénticos)

### **Caso 2: Imagen**
1. Cifrar una imagen PNG pequeña
2. Intentar abrirla (no debería verse)
3. Descifrarla con la misma clave
4. Verificar que la imagen restaurada es idéntica

### **Caso 3: Archivo binario con datos estructurados**
1. Usar el archivo del ejercicio 2 (combo de teclas)
2. Cifrarlo
3. Descifrar con **clave incorrecta** → datos corruptos
4. Descifrar con **clave correcta** → datos restaurados

### **Caso 4: Archivo grande (> 1 MB)**
1. Crear archivo de prueba grande: `dd if=/dev/zero of=grande.bin bs=1M count=5`
2. Cifrar y verificar performance
3. Descifrar y verificar integridad

---

## 🔍 Verificación con HexDump

**Archivo original (primeros bytes):**
```
00000000  53 41 56 45 01 00 00 00 48 65 72 6F  SAVE....Hero
```

**Después de XOR con clave 0xA7:**
```
00000000  F4 E6 F1 E2 A6 A7 A7 A7 EF C2 D5 C8  ............
```

**Cálculos manuales:**
```
0x53 ^ 0xA7 = 0xF4  ('S' cifrado)
0x41 ^ 0xA7 = 0xE6  ('A' cifrado)
0x56 ^ 0xA7 = 0xF1  ('V' cifrado)
0x45 ^ 0xA7 = 0xE2  ('E' cifrado)
```

---

## 🚀 Desafíos Adicionales (Opcional)

1. **Clave multi-byte:**
   - En lugar de 1 byte, usa una clave de N bytes
   - Rota entre los bytes de la clave: `buffer[i] ^= clave[i % clave.length]`

2. **Header sin cifrar:**
   - Los primeros 16 bytes no se cifran (contienen metadatos)
   - Solo cifra el contenido real del savegame

3. **Checksum de verificación:**
   - Añade un checksum al final del archivo cifrado
   - Al descifrar, verifica que el checksum coincida

4. **Cifrado en paralelo:**
   - Divide el archivo en chunks
   - Procesa cada chunk en un thread diferente
   - Mejora dramática de performance en archivos grandes

5. **GUI simple:**
   - Interfaz gráfica con botones "Cifrar" y "Descifrar"
   - Selección de archivos con JFileChooser
   - Barra de progreso visual

---

## ⚠️ Limitaciones del Cifrado XOR

**Importante entender:**
- ❌ **NO es criptográficamente seguro** para datos sensibles
- ❌ Vulnerable a ataques de análisis de frecuencia
- ❌ Si conoces parte del texto original, puedes deducir la clave
- ✅ **PERO es perfecto para:**
  - Ofuscar savegames de juegos
  - Evitar edición casual de archivos
  - Aprender conceptos de cifrado
  - Performance: extremadamente rápido

Para protección real, usa: AES, RSA, o librerías como Java Cryptography Extension (JCE).

---

## 📚 Recursos

- Revisa `NIVEL1_TEORIA.md` para copiar archivos con buffers
- Usa `HERRAMIENTA_HEX_DUMP.md` para ver archivos cifrados
- Operadores bit a bit: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✅ Cifrar y descifrar archivos correctamente con XOR
- ✅ Usar buffers para eficiencia (no cargar todo en memoria)
- ✅ Validar parámetros de entrada (clave 1-255)
- ✅ Soportar archivos de cualquier tamaño (hasta GB)
- ✅ Demostrar que cifrar → descifrar devuelve el archivo original
- ✅ Usar try-with-resources
- ✅ Manejar excepciones apropiadamente
- ✅ Mostrar progreso o feedback al usuario

---

**Tiempo estimado:** 60-75 minutos

**Dificultad:** ⭐⭐⭐ Básica-Intermedia

**¡Protege esos savegames!** 🎮🔐
