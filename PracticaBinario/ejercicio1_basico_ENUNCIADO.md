# 📝 EJERCICIO BÁSICO 1: Analizador de Archivos Binarios

## 🎯 Objetivo

Crear un programa que **lea un archivo binario completo** y genere **estadísticas detalladas** sobre su contenido.

Este ejercicio te enseñará a:
- Leer archivos binarios byte a byte
- Trabajar con valores de bytes (0-255)
- Procesar datos mientras se leen
- Generar reportes a partir de datos binarios

---

## 📋 Descripción del Ejercicio

Debes crear un programa que analice cualquier archivo binario y muestre:

1. **Información básica:**
   - Tamaño total del archivo en bytes
   - Tamaño en KB, MB (si aplica)

2. **Estadísticas de bytes:**
   - Byte más frecuente (y cuántas veces aparece)
   - Byte menos frecuente (que aparezca al menos una vez)
   - Cantidad de bytes únicos diferentes

3. **Distribución de valores:**
   - Cantidad de bytes con valor 0 (bytes nulos)
   - Cantidad de bytes ASCII imprimibles (32-126)
   - Cantidad de bytes con el bit más significativo activado (128-255)

4. **Patrones:**
   - Detectar si hay secuencias repetitivas (al menos 4 bytes iguales consecutivos)

---

## 🎮 Contexto: Archivo de Configuración de Juego

Imagina que estás desarrollando un videojuego y necesitas analizar archivos de configuración binarios para:
- Detectar corrupción de datos
- Entender la estructura del archivo
- Verificar que los datos son válidos

---

## 📥 Entrada

- **Nombre de archivo** a analizar (como argumento del programa o solicitado por teclado)
- El archivo puede ser de cualquier tipo: imágenes, ejecutables, datos, etc.

---

## 📤 Salida Esperada

```
╔════════════════════════════════════════════════════════════════╗
║          ANÁLISIS DE ARCHIVO BINARIO                          ║
╚════════════════════════════════════════════════════════════════╝

Archivo: config.bin
Tamaño: 2048 bytes (2.00 KB)

--- ESTADÍSTICAS DE BYTES ---
Byte más frecuente: 0x00 (aparece 512 veces, 25.00%)
Byte menos frecuente: 0xFF (aparece 1 vez, 0.05%)
Bytes únicos diferentes: 187

--- DISTRIBUCIÓN DE VALORES ---
Bytes nulos (0x00): 512 (25.00%)
Bytes ASCII imprimibles (32-126): 1024 (50.00%)
Bytes con MSB activado (128-255): 256 (12.50%)

--- PATRONES DETECTADOS ---
✓ Se detectaron 15 secuencias de 4+ bytes consecutivos iguales
  Ejemplo: 8 bytes 0x00 consecutivos en offset 0x0100

--- PRIMEROS 64 BYTES (HEX) ---
00000000  43 4F 4E 46 01 00 00 00 00 00 00 00 48 65 72 6F
00000010  00 00 00 00 32 00 00 00 E8 03 00 00 64 00 00 00
00000020  00 00 00 00 03 00 00 00 45 73 70 61 64 61 00 00
00000030  00 00 00 00 64 00 00 00 50 6F 63 69 6F 6E 00 00

Análisis completado.
```

---

## 🔧 Especificaciones Técnicas

### **Clases Java a usar:**
- `FileInputStream` - Para leer el archivo
- `File` - Para obtener información del archivo

### **Conceptos importantes:**

1. **Lectura byte a byte:**
   - Usa `read()` que devuelve `int` (0-255 o -1 si EOF)
   - Importante: el valor devuelto es `int`, no `byte`

2. **Conversión de byte a int sin signo:**
   ```
   int valorSinSigno = byteValue & 0xFF;
   ```

3. **Conteo de frecuencias:**
   - Usa un array de 256 posiciones: `int[] frecuencias = new int[256]`
   - Para cada byte leído: `frecuencias[byteValue]++`

4. **Caracteres ASCII imprimibles:**
   - Rango: 32 (espacio) a 126 (~)
   - Incluye: letras, números, símbolos comunes

5. **Bit más significativo (MSB):**
   - Bytes con MSB=1 tienen valor >= 128
   - Verifica: `if (byteValue >= 128)`

6. **Detección de secuencias:**
   - Cuenta bytes consecutivos iguales
   - Si contador >= 4, es una secuencia repetitiva

---

## 💡 Pistas

1. **Estructura general:**
   ```
   - Abrir archivo con FileInputStream
   - Crear array de frecuencias[256]
   - Mientras haya bytes que leer:
       - Leer byte
       - Actualizar estadísticas
       - Detectar patrones
   - Calcular resultados finales
   - Mostrar reporte
   ```

2. **Para encontrar byte más/menos frecuente:**
   - Recorre el array de frecuencias
   - Busca el máximo y el mínimo (ignorando frecuencia 0)

3. **Para mostrar los primeros bytes en hex:**
   - Guarda los primeros 64 bytes en un array
   - Usa `String.format("%02X", valor)` para formato hexadecimal

4. **Try-with-resources:**
   - No olvides usar `try (FileInputStream fis = ...)` para cerrar automáticamente

---

## 🎓 Conocimientos que Practicarás

- ✅ Abrir y leer archivos binarios
- ✅ Procesar bytes individuales
- ✅ Mantener estadísticas durante la lectura
- ✅ Trabajar con arrays para conteo de frecuencias
- ✅ Detectar patrones en datos binarios
- ✅ Formatear salida de forma legible
- ✅ Conversión byte ↔ int sin signo

---

## 🧪 Casos de Prueba

### **Caso 1: Archivo pequeño (texto)**
- Crear archivo: `echo "Hello World" > test.txt`
- Debería mostrar muchos bytes ASCII imprimibles

### **Caso 2: Archivo con bytes nulos**
- Crear con tu programa un archivo lleno de ceros
- Debería detectar 100% bytes nulos

### **Caso 3: Imagen PNG**
- Analizar cualquier imagen PNG pequeña
- Debería tener distribución variada de bytes

### **Caso 4: Archivo con secuencias**
- Crear archivo con repeticiones: `0x00 0x00 0x00 0x00 0xFF 0xFF 0xFF 0xFF`
- Debería detectar las secuencias

---

## 🚀 Desafíos Adicionales (Opcional)

Si terminas rápido y quieres más desafío:

1. **Histograma visual:**
   - Muestra un gráfico ASCII de la distribución de bytes
   - Ejemplo: `0x00: ████████ (256)`

2. **Entropía:**
   - Calcula la entropía del archivo (medida de aleatoriedad)
   - Archivos comprimidos/cifrados tienen alta entropía

3. **Detección de formato:**
   - Detecta el tipo de archivo por los primeros bytes (magic numbers)
   - PNG: `89 50 4E 47`, JPEG: `FF D8 FF`, ZIP: `50 4B 03 04`

4. **Comparación:**
   - Compara dos archivos y muestra qué tan similares son

---

## 📚 Recursos

- Revisa `NIVEL1_TEORIA.md` para ejemplos de lectura con FileInputStream
- Usa `HERRAMIENTA_HEX_DUMP.md` para visualizar archivos de prueba
- Consulta `REFERENCIA_CLASES_STREAMS.md` para detalles de la API

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✅ Leer archivos binarios correctamente sin errores
- ✅ Calcular todas las estadísticas solicitadas
- ✅ Mostrar el reporte de forma clara y ordenada
- ✅ Manejar archivos de cualquier tamaño (al menos hasta 10 MB)
- ✅ Usar try-with-resources para cerrar el stream
- ✅ Manejar excepciones apropiadamente (archivo no encontrado, errores de lectura)

---

**Tiempo estimado:** 45-60 minutos

**Dificultad:** ⭐⭐ Básica

**¡Buena suerte!** 🎮
