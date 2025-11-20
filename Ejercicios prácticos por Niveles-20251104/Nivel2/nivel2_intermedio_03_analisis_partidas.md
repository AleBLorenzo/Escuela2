# Nivel 2 - Ejercicio Intermedio 3: Analizador de Partidas (Análisis Estadístico)

**Dificultad:** ⭐⭐ Intermedio  
**Tema:** Valorant - Sistema de análisis de partidas

## Contexto

Eres un analista de datos para un equipo profesional de Valorant. El equipo juega múltiples partidas al día y necesitas un sistema que registre cada partida y genere estadísticas detalladas para mejorar el rendimiento.

## Objetivo

Crear un sistema que:
1. Registre información de cada partida jugada
2. Calcule estadísticas individuales y globales
3. Identifique tendencias y patrones
4. Genere reportes útiles para el entrenador

## Especificaciones de Datos

### Estructura de Partida

Cada partida contiene:
- **Match ID** (long): Timestamp de cuando se jugó (milisegundos desde epoch)
- **Mapa** (String): Nombre del mapa (ej: "Bind", "Haven", "Ascent")
- **Resultado** (boolean): true = victoria, false = derrota
- **Rondas ganadas** (byte): Rondas que ganó el equipo (0-13)
- **Rondas perdidas** (byte): Rondas que perdió el equipo (0-13)
- **Kills totales** (short): Total de eliminaciones del equipo
- **Deaths totales** (short): Total de muertes del equipo
- **Duración** (int): Duración en segundos
- **MVP** (String): Nombre del jugador destacado

**Nota:** Esta es una estructura de **longitud variable** (por los Strings), por lo que se usa lectura secuencial.

## Funcionalidades Requeridas

### 1. Registro de Partidas

#### `registrarPartida(...)`
```java
public static void registrarPartida(String archivo, String mapa, 
        boolean victoria, byte rondasGanadas, byte rondasPerdidas, 
        short kills, short deaths, int duracion, String mvp)
```
- Genera automáticamente el matchID con `System.currentTimeMillis()`
- Guarda la partida en modo append
- Muestra confirmación con resultado (Victoria/Derrota)

#### `cargarPartidas(String archivo)`
```java
public static List<Partida> cargarPartidas(String archivo)
```
- Lee TODAS las partidas del archivo
- Devuelve una lista de objetos Partida
- Maneja EOFException correctamente

### 2. Estadísticas Básicas

#### `mostrarEstadisticasGenerales(List<Partida> partidas)`
```java
public static void mostrarEstadisticasGenerales(List<Partida> partidas)
```
Debe calcular y mostrar:
- Total de partidas jugadas
- Victorias y derrotas
- Winrate (porcentaje de victorias)
- Total de kills y deaths
- K/D ratio (kills/deaths)
- Promedio de kills por partida
- Promedio de deaths por partida
- Duración promedio de partidas
- Rondas ganadas vs perdidas totales

**Formato esperado:**
```
=== ESTADÍSTICAS GENERALES ===
Partidas jugadas: 15
Victorias: 9 (60.00%)
Derrotas: 6 (40.00%)

Performance:
- Total Kills: 234
- Total Deaths: 189
- K/D Ratio: 1.24
- Promedio kills/partida: 15.6
- Promedio deaths/partida: 12.6

Rondas:
- Ganadas: 125
- Perdidas: 98
- Ratio: 1.28

Tiempo:
- Duración promedio: 34m 25s
- Tiempo total jugado: 8h 36m 15s
```

### 3. Análisis por Mapa

#### `estadisticasPorMapa(List<Partida> partidas)`
```java
public static void estadisticasPorMapa(List<Partida> partidas)
```
Para cada mapa diferente, muestra:
- Número de veces jugado
- Winrate en ese mapa
- K/D ratio en ese mapa
- Mapa con mejor winrate

**Formato esperado:**
```
=== ESTADÍSTICAS POR MAPA ===

Bind:
- Partidas: 5
- Winrate: 80.00% (4V-1D)
- K/D: 1.45
- Promedio rondas ganadas: 9.2

Haven:
- Partidas: 4
- Winrate: 50.00% (2V-2D)
- K/D: 1.10
- Promedio rondas ganadas: 6.5

Ascent:
- Partidas: 6
- Winrate: 50.00% (3V-3D)
- K/D: 1.15
- Promedio rondas ganadas: 7.0

🏆 Mejor mapa: Bind (80.00% winrate)
💀 Peor mapa: Haven (50.00% winrate)
```

### 4. Análisis de MVPs

#### `analizarMVPs(List<Partida> partidas)`
```java
public static void analizarMVPs(List<Partida> partidas)
```
- Cuenta cuántas veces cada jugador fue MVP
- Ordena de mayor a menor
- Muestra el top 5 de MVPs
- Calcula el winrate cuando cada jugador es MVP

**Formato esperado:**
```
=== RANKING DE MVPs ===
1. TenZ       - 6 veces MVP | Winrate cuando es MVP: 83.33%
2. Shazham    - 4 veces MVP | Winrate cuando es MVP: 75.00%
3. Sick       - 3 veces MVP | Winrate cuando es MVP: 100.00%
4. Dapr       - 2 veces MVP | Winrate cuando es MVP: 50.00%
```

### 5. Análisis de Rachas

#### `analizarRachas(List<Partida> partidas)`
```java
public static void analizarRachas(List<Partida> partidas)
```
- Detecta la racha de victorias más larga
- Detecta la racha de derrotas más larga
- Muestra la racha actual (últimas partidas)

**Formato esperado:**
```
=== ANÁLISIS DE RACHAS ===
Mejor racha de victorias: 4 partidas consecutivas
Peor racha de derrotas: 2 partidas consecutivas

Racha actual:
✓ Victoria - Haven (Últimas 5 partidas)
✓ Victoria - Bind
✗ Derrota  - Ascent
✓ Victoria - Bind
✓ Victoria - Haven
→ Racha actual: 1 victoria
```

### 6. Reportes Avanzados

#### `generarReporteCompleto(String archivo, String archivoSalida)`
```java
public static void generarReporteCompleto(String archivo, String archivoSalida)
```
- Lee todas las partidas del archivo binario
- Genera un archivo de texto con reporte completo
- Incluye: estadísticas generales, por mapa, MVPs, rachas
- Formato: texto plano legible

## Datos de Prueba

Genera al menos 15 partidas con variedad:

**Partidas sugeridas:**
```
1.  Bind    | Victoria | 13-8  | Kills: 18 | Deaths: 14 | 35m | MVP: TenZ
2.  Haven   | Victoria | 13-10 | Kills: 16 | Deaths: 15 | 42m | MVP: Shazham
3.  Ascent  | Derrota  | 9-13  | Kills: 12 | Deaths: 16 | 38m | MVP: Sick
4.  Bind    | Victoria | 13-5  | Kills: 20 | Deaths: 10 | 28m | MVP: TenZ
5.  Bind    | Victoria | 13-7  | Kills: 17 | Deaths: 12 | 32m | MVP: TenZ
6.  Haven   | Derrota  | 10-13 | Kills: 14 | Deaths: 16 | 40m | MVP: Dapr
7.  Ascent  | Victoria | 13-9  | Kills: 15 | Deaths: 13 | 36m | MVP: Shazham
8.  Bind    | Victoria | 13-6  | Kills: 19 | Deaths: 11 | 30m | MVP: TenZ
9.  Haven   | Victoria | 13-8  | Kills: 17 | Deaths: 13 | 35m | MVP: Sick
10. Ascent  | Derrota  | 11-13 | Kills: 16 | Deaths: 17 | 41m | MVP: Shazham
11. Haven   | Derrota  | 8-13  | Kills: 13 | Deaths: 18 | 37m | MVP: Dapr
12. Ascent  | Victoria | 13-10 | Kills: 18 | Deaths: 15 | 39m | MVP: TenZ
13. Bind    | Derrota  | 9-13  | Kills: 14 | Deaths: 17 | 38m | MVP: Sick
14. Ascent  | Victoria | 13-7  | Kills: 21 | Deaths: 12 | 33m | MVP: TenZ
15. Ascent  | Victoria | 13-11 | Kills: 19 | Deaths: 16 | 43m | MVP: Shazham
```

## Casos de Prueba Obligatorios

Tu programa debe:

1. **Registrar las 15 partidas** en el archivo
2. **Cargar todas las partidas** en memoria
3. **Mostrar estadísticas generales** completas
4. **Analizar rendimiento por mapa** (3 mapas)
5. **Generar ranking de MVPs** (4-5 jugadores diferentes)
6. **Detectar rachas** de victorias/derrotas
7. **Generar reporte completo** en archivo de texto
8. **Validar el tamaño del archivo** (debe ser coherente con 15 partidas)

## Clase Auxiliar Sugerida

```java
class Partida {
    private long matchId;
    private String mapa;
    private boolean victoria;
    private byte rondasGanadas;
    private byte rondasPerdidas;
    private short kills;
    private short deaths;
    private int duracion;
    private String mvp;
    
    // Constructor, getters, setters
    
    public double getKD() {
        return deaths == 0 ? kills : (double) kills / deaths;
    }
    
    public String getDuracionFormateada() {
        int minutos = duracion / 60;
        int segundos = duracion % 60;
        return minutos + "m " + segundos + "s";
    }
}
```

## Aspectos Técnicos Importantes

### 1. Orden de Escritura/Lectura
```java
// Escritura
dos.writeLong(matchId);
dos.writeUTF(mapa);
dos.writeBoolean(victoria);
dos.writeByte(rondasGanadas);
dos.writeByte(rondasPerdidas);
dos.writeShort(kills);
dos.writeShort(deaths);
dos.writeInt(duracion);
dos.writeUTF(mvp);

// Lectura (MISMO ORDEN)
long matchId = dis.readLong();
String mapa = dis.readUTF();
boolean victoria = dis.readBoolean();
// ... etc
```

### 2. Uso de Colecciones
Para análisis, usa:
- `HashMap<String, List<Partida>>` para agrupar por mapa
- `HashMap<String, Integer>` para contar MVPs
- `List<Partida>` para mantener orden cronológico

### 3. Formateo de Números
```java
// Porcentajes
String.format("%.2f%%", winrate * 100)

// Números con comas
String.format("%,d", totalKills)

// K/D con 2 decimales
String.format("%.2f", kd)
```

## Salida Esperada del Main

```
=== REGISTRAR PARTIDAS ===
✓ Partida registrada: Victoria en Bind (13-8) - MVP: TenZ
✓ Partida registrada: Victoria en Haven (13-10) - MVP: Shazham
...
✓ 15 partidas registradas correctamente

=== CARGANDO DATOS ===
✓ 15 partidas cargadas desde archivo

[Aquí van todas las estadísticas según los métodos implementados]

=== REPORTE GENERADO ===
✓ Reporte completo guardado en: reporte_valorant.txt
```

## Retos Adicionales (Opcional)

1. **`mejorRendimiento()`** - Encuentra la partida con mejor K/D
2. **`tendencias()`** - Detecta si el rendimiento mejora o empeora con el tiempo
3. **`prediccion()`** - Predice probabilidad de victoria en el próximo mapa
4. **Exportar a CSV** - Genera archivo CSV para análisis en Excel
5. **Gráficos en consola** - Muestra barras ASCII de estadísticas

## Pistas

1. Para rachas, recorre la lista y mantén contadores de victorias/derrotas consecutivas
2. Para agrupar por mapa, usa `Map<String, List<Partida>>`
3. Para MVPs, usa `Map<String, Integer>` y luego ordena por valor
4. Calcula K/D como double, no int
5. Para el timestamp, usa `System.currentTimeMillis()` al registrar

## Criterios de Evaluación

- ✅ Todos los cálculos estadísticos correctos
- ✅ Manejo robusto de EOFException
- ✅ Código bien organizado con métodos separados
- ✅ Salida formateada profesionalmente
- ✅ Reporte de texto generado correctamente
- ✅ Uso apropiado de colecciones para análisis

Este ejercicio simula análisis de datos reales en esports. ¡Diviértete! 🎮📊
