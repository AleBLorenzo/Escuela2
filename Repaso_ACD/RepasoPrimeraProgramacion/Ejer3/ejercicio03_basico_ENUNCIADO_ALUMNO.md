# EJERCICIO 03 - Calculadora de Experiencia
## Nivel: Básico

---

## 🎯 Objetivos de Aprendizaje
- Aplicar operadores aritméticos complejos
- Trabajar con operadores de asignación compuesta (+=, -=, *=)
- Utilizar conversiones de tipo (casting)
- Combinar operadores y condicionales

---

## 📝 Descripción del Problema

Estás desarrollando un sistema de cálculo de experiencia (XP) para un juego de rol. El sistema debe calcular los puntos de experiencia que gana un jugador tras completar una misión, aplicando diferentes bonificaciones y penalizaciones según su desempeño.

Debes crear un programa que:

1. **Solicite los siguientes datos:**
   - Nombre del jugador
   - XP base de la misión (número entero, mínimo 100)
   - Nivel del jugador (número entero)
   - Número de enemigos derrotados (número entero)
   - Porcentaje de salud restante al finalizar (número entero entre 0 y 100)
   - Número de muertes durante la misión (número entero)
   - Tiempo empleado en minutos (número entero)

2. **Calcule la experiencia final** aplicando las siguientes reglas en orden:

   **Paso 1 - Bonus por nivel:**
   - XP base se multiplica por: `1 + (nivel * 0.1)`
   - Ejemplo: nivel 5 → multiplicador 1.5 (1 + 5*0.1)
   
   **Paso 2 - Bonus por enemigos:**
   - Sumar `30 XP` por cada enemigo derrotado
   
   **Paso 3 - Bonus por salud:**
   - Si salud > 75%: sumar 15% de XP acumulado hasta ahora
   - Si salud entre 50% y 75%: sumar 8% de XP acumulado
   - Si salud entre 25% y 49%: no sumar ni restar
   - Si salud < 25%: restar 10% de XP acumulado
   
   **Paso 4 - Penalización por muertes:**
   - Restar `50 XP` por cada muerte
   - Si tuvo 0 muertes: bonus adicional de 200 XP ("Flawless Victory")
   
   **Paso 5 - Bonus/Penalización por tiempo:**
   - Si tiempo < 10 minutos: sumar 20% de XP acumulado
   - Si tiempo > 30 minutos: restar 15% de XP acumulado

3. **Muestre por pantalla:**
   - Resumen de la misión con todos los datos
   - Desglose detallado de cada cálculo paso a paso
   - XP final obtenido
   - Indicación si subió de nivel (cada 1000 XP = 1 nivel)

---

## 💡 Conceptos Clave a Aplicar

- **Operadores aritméticos**: +, -, *, /
- **Operadores de asignación**: =, +=, -=, *=
- **Conversión de tipos**: casting de double a int cuando sea necesario
- **Cálculo de porcentajes**: valor * porcentaje / 100
- **Operadores relacionales**: comparaciones para condicionales
- **Variables acumuladoras**: mantener el XP que va cambiando

---

## 🔍 Casos de Prueba

### Caso 1: Misión perfecta
**Entrada:**
```
Nombre: ElitePro
XP base: 500
Nivel: 3
Enemigos: 10
Salud restante: 85%
Muertes: 0
Tiempo: 8 minutos
```

**Cálculo esperado:**
```
1. XP base: 500
2. Bonus nivel (3): 500 * 1.3 = 650 XP
3. Bonus enemigos (10): 650 + (10*30) = 950 XP
4. Bonus salud (85% > 75%): 950 + (950*0.15) = 1092 XP
5. Sin muertes: 1092 + 200 = 1292 XP
6. Bonus velocidad (<10 min): 1292 + (1292*0.20) = 1550 XP

XP FINAL: 1550
Niveles ganados: 1 (1550/1000 = 1.55)
```

### Caso 2: Misión con dificultades
**Entrada:**
```
Nombre: Newbie
XP base: 300
Nivel: 1
Enemigos: 5
Salud restante: 20%
Muertes: 3
Tiempo: 35 minutos
```

**Cálculo esperado:**
```
1. XP base: 300
2. Bonus nivel (1): 300 * 1.1 = 330 XP
3. Bonus enemigos (5): 330 + (5*30) = 480 XP
4. Penalización salud (<25%): 480 - (480*0.10) = 432 XP
5. Penalización muertes (3): 432 - (3*50) = 282 XP
6. Penalización tiempo (>30 min): 282 - (282*0.15) = 239 XP

XP FINAL: 239
Niveles ganados: 0 (239/1000 = 0.23)
```

### Caso 3: Misión estándar
**Entrada:**
```
Nombre: Average
XP base: 400
Nivel: 2
Enemigos: 7
Salud restante: 60%
Muertes: 1
Tiempo: 15 minutos
```

**Cálculo esperado:**
```
1. XP base: 400
2. Bonus nivel (2): 400 * 1.2 = 480 XP
3. Bonus enemigos (7): 480 + (7*30) = 690 XP
4. Bonus salud medio (50-75%): 690 + (690*0.08) = 745 XP
5. Penalización muertes (1): 745 - 50 = 695 XP
6. Tiempo normal (10-30 min): sin cambios = 695 XP

XP FINAL: 695
Niveles ganados: 0 (695/1000 = 0.69)
```

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✓ Solicitar correctamente todos los datos de entrada
- ✓ Aplicar los cálculos en el ORDEN especificado (importante)
- ✓ Usar variables acumuladoras para mantener el XP actualizado
- ✓ Aplicar correctamente los porcentajes
- ✓ Manejar las conversiones de tipo cuando sea necesario
- ✓ Mostrar el desglose paso a paso de los cálculos
- ✓ Calcular correctamente cuántos niveles ganó

---

## 🎮 Pistas Generales

### Sobre el orden de cálculos:
- **Es crucial aplicar los pasos en orden secuencial**
- Cada paso usa el XP acumulado del paso anterior
- No calcules todo por separado y lo sumes al final

### Sobre porcentajes:
- Para calcular 15% de 1000: `1000 * 15 / 100` o `1000 * 0.15`
- Para sumar 15%: `xp = xp + (xp * 0.15)` o `xp *= 1.15`
- Para restar 15%: `xp = xp - (xp * 0.15)` o `xp *= 0.85`

### Sobre conversiones de tipo:
- Multiplicar por 0.1, 0.15, etc. puede dar decimales (double)
- Si necesitas enteros, usa casting: `(int)(valor * 0.15)`
- Java automáticamente convierte en muchos casos

### Sobre variables acumuladoras:
```
xpActual = xpBase;          // Empezar con base
xpActual = xpActual * 1.3;  // Aplicar primer bonus
xpActual = xpActual + 300;  // Aplicar segundo bonus
// etc...
```

### Sobre los niveles ganados:
- División entera: `niveles = xpFinal / 1000`
- Ejemplo: 1550 / 1000 = 1 nivel (en división entera)

---

## 📌 Notas Importantes

- **ORDEN IMPORTA**: No es lo mismo sumar enemigos antes que después del bonus de nivel
- **Variables intermedias**: Puedes ir guardando el XP en la misma variable
- **Redondeos**: Decide si quieres redondear decimales o trabajar con enteros
- **Validación**: Aunque no es obligatorio, pensar qué pasa si salud > 100 o muertes < 0
- **Formato de salida**: Muestra el proceso paso a paso para que sea educativo

---

## 🤔 Reflexión Previa

Antes de empezar:
1. ¿Cuál es la diferencia entre sumar 15% y multiplicar por 1.15?
2. ¿Por qué es importante el orden de los cálculos?
3. ¿Qué tipo de variable usarás para almacenar el XP? (int o double)
4. ¿Cómo representarás los porcentajes en código?

---

## 🎲 Desafío Extra (Opcional)

Para los que terminen antes:
- Añade validación: si XP final es negativo, poner 0
- Calcula el XP que falta para el siguiente nivel
- Muestra una barra de progreso visual usando caracteres: `[####------] 40%`

---

**¡Buena suerte calculando esa experiencia! Recuerda: el orden es fundamental. 🎮**
