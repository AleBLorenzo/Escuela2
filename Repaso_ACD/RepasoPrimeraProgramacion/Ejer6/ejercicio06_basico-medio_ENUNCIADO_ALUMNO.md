# EJERCICIO 06 - Contador de Victorias Consecutivas
## Nivel: Básico-Medio

---

## 🎯 Objetivos de Aprendizaje
- Utilizar bucles while para entrada variable
- Trabajar con contadores acumulativos
- Implementar lógica de rachas/streaks
- Aplicar condiciones para romper rachas

---

## 📝 Descripción del Problema

Estás desarrollando un sistema de seguimiento de victorias para un juego competitivo online. El sistema debe registrar los resultados de partidas consecutivas y calcular estadísticas importantes, especialmente la racha de victorias más larga.

Debes crear un programa que:

1. **Solicite al usuario:**
   - Nombre del jugador
   - Resultados de partidas una por una hasta que escriba "FIN"
     - Para cada partida: "V" (Victoria), "D" (Derrota), o "E" (Empate)

2. **Calcule y registre:**
   - Total de partidas jugadas
   - Total de victorias, derrotas y empates
   - Racha actual de victorias (se resetea con derrota o empate)
   - Racha máxima de victorias alcanzada durante la sesión
   - Porcentaje de victorias (winrate)

3. **Muestre durante el proceso:**
   - Después de cada partida, mostrar la racha actual
   - Mensaje especial cuando se alcance una nueva racha máxima
   - Mensaje motivacional cada 5 victorias

4. **Al finalizar (escribir "FIN"), mostrar:**
   - Resumen completo de estadísticas
   - Clasificación del jugador según su winrate
   - Mensaje personalizado según su desempeño

---

## 💡 Conceptos Clave a Aplicar

- **Bucle while con centinela**: continuar hasta que se ingrese "FIN"
- **Contadores múltiples**: victorias, derrotas, empates, total
- **Variables de racha**: actual y máxima
- **Comparación de máximos**: actualizar racha máxima cuando corresponda
- **Cálculo de porcentajes**: winrate = (victorias / total) * 100
- **Validación de entrada**: solo aceptar V, D, E o FIN

---

## 🔍 Casos de Prueba

### Caso 1: Sesión perfecta (racha continua)
**Entrada:**
```
Nombre: Champion
Partida 1: V
Partida 2: V
Partida 3: V
Partida 4: V
Partida 5: V
Partida 6: V
FIN
```

**Salida durante ejecución:**
```
Partida 1: ✓ Victoria
Racha actual: 1

Partida 2: ✓ Victoria
Racha actual: 2

Partida 3: ✓ Victoria
Racha actual: 3

Partida 4: ✓ Victoria
Racha actual: 4

Partida 5: ✓ Victoria
Racha actual: 5
🔥 ¡5 VICTORIAS! ¡Imparable!

Partida 6: ✓ Victoria
Racha actual: 6
🏆 ¡NUEVA RACHA MÁXIMA! (6 victorias)
```

**Resumen final:**
```
=== RESUMEN DE SESIÓN ===
Jugador: Champion

Partidas jugadas: 6
✓ Victorias: 6 (100.0%)
✗ Derrotas: 0 (0.0%)
≈ Empates: 0 (0.0%)

Racha máxima: 6 victorias

Clasificación: ¡LEYENDA! 
Tu rendimiento es excepcional.
```

### Caso 2: Rachas interrumpidas
**Entrada:**
```
Nombre: Fighter
V, V, D, V, V, V, D, V, FIN
```

**Salida relevante:**
```
Partida 1: ✓ Victoria
Racha actual: 1

Partida 2: ✓ Victoria
Racha actual: 2

Partida 3: ✗ Derrota
Racha actual: 0

Partida 4: ✓ Victoria
Racha actual: 1

Partida 5: ✓ Victoria
Racha actual: 2

Partida 6: ✓ Victoria
Racha actual: 3
🏆 ¡NUEVA RACHA MÁXIMA! (3 victorias)

Partida 7: ✗ Derrota
Racha actual: 0

Partida 8: ✓ Victoria
Racha actual: 1
```

**Resumen final:**
```
=== RESUMEN DE SESIÓN ===
Jugador: Fighter

Partidas jugadas: 8
✓ Victorias: 5 (62.5%)
✗ Derrotas: 3 (37.5%)
≈ Empates: 0 (0.0%)

Racha máxima: 3 victorias

Clasificación: COMPETENTE
Buen rendimiento general.
```

### Caso 3: Con empates
**Entrada:**
```
Nombre: Casual
V, E, V, V, E, D, FIN
```

**Resumen final:**
```
=== RESUMEN DE SESIÓN ===
Jugador: Casual

Partidas jugadas: 6
✓ Victorias: 3 (50.0%)
✗ Derrotas: 1 (16.7%)
≈ Empates: 2 (33.3%)

Racha máxima: 2 victorias

Clasificación: EQUILIBRADO
Rendimiento intermedio.
```

### Caso 4: Mala racha
**Entrada:**
```
Nombre: Newbie
D, D, V, D, D, D, FIN
```

**Resumen final:**
```
=== RESUMEN DE SESIÓN ===
Jugador: Newbie

Partidas jugadas: 6
✓ Victorias: 1 (16.7%)
✗ Derrotas: 5 (83.3%)
≈ Empates: 0 (0.0%)

Racha máxima: 1 victoria

Clasificación: APRENDIZ
¡No te rindas! Sigue practicando.
```

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✓ Continuar pidiendo resultados hasta recibir "FIN"
- ✓ Validar que la entrada sea V, D, E o FIN (ignorar mayúsculas/minúsculas)
- ✓ Mantener correctamente la racha actual de victorias
- ✓ Detectar y guardar la racha máxima alcanzada
- ✓ Contar todas las partidas y sus tipos
- ✓ Calcular correctamente el winrate (porcentaje)
- ✓ Mostrar mensaje especial cada 5 victorias consecutivas
- ✓ Mostrar mensaje cuando se alcanza nueva racha máxima
- ✓ Clasificar al jugador según su winrate al finalizar

---

## 🎮 Pistas Generales

### Estructura del bucle:
```
contador_partidas = 0
racha_actual = 0
racha_maxima = 0

mientras entrada != "FIN":
    leer resultado (V/D/E)
    
    si resultado == "V":
        incrementar victorias
        incrementar racha_actual
        
        si racha_actual > racha_maxima:
            actualizar racha_maxima
            mostrar mensaje especial
    
    si resultado == "D" o "E":
        racha_actual = 0  // Resetear racha
```

### Para calcular porcentajes:
```
winrate = (victorias / total_partidas) * 100
```

### Clasificación sugerida según winrate:
- **90-100%**: ¡LEYENDA!
- **70-89%**: ¡PRO!
- **50-69%**: COMPETENTE
- **30-49%**: EQUILIBRADO
- **0-29%**: APRENDIZ

---

## 📌 Notas Importantes

- **Entrada case-insensitive**: "v", "V", "victoria" deberían aceptarse
- **Racha se resetea**: Tanto derrota como empate rompen la racha
- **Racha máxima**: Se mantiene aunque luego baje
- **División por cero**: Si total=0, manejar el caso (aunque no debería ocurrir)
- **Mensaje cada 5**: Solo cuando racha_actual es múltiplo de 5
- **Formato de entrada**: Puedes pedir resultado por resultado o permitir entrada múltiple

---

## 🤔 Preguntas de Reflexión

Antes de empezar:
1. ¿Qué condición usarás para mantener el bucle activo?
2. ¿Cuándo se resetea la racha_actual?
3. ¿Cómo detectas si la racha actual supera la máxima?
4. ¿Qué pasa si el usuario ingresa algo distinto de V/D/E/FIN?
5. ¿Cómo evitas división por cero al calcular porcentajes?

---

## 🎲 Desafíos Extra (Opcional)

Para los que terminen antes:

1. **Racha de derrotas**: Registrar también la peor racha de derrotas
2. **Promedio móvil**: Mostrar winrate de las últimas 5 partidas
3. **Predicción**: Indicar cuántas victorias necesita para subir de categoría
4. **Gráfico ASCII**: Mostrar barra visual del winrate `[####------] 40%`
5. **Guardar histórico**: Permitir ver estadísticas de sesiones anteriores

---

## 💭 Ejemplo de Flujo Completo

```
=== CONTADOR DE VICTORIAS ===
Nombre del jugador: ProGamer

Introduce resultado (V/D/E) o FIN para terminar

Partida 1: v
✓ Victoria | Racha: 1

Partida 2: V
✓ Victoria | Racha: 2

Partida 3: d  
✗ Derrota | Racha: 0

Partida 4: V
✓ Victoria | Racha: 1

Partida 5: e
≈ Empate | Racha: 0

Partida 6: fin

=== RESUMEN DE SESIÓN ===
[resumen completo]
```

---

**¡Buena suerte rastreando esas victorias! Que las rachas te acompañen. 🏆**
