# 🎮 Ejercicio 19: Sistema de Matchmaking

## 🎯 Objetivos de Aprendizaje
- Implementar **algoritmos de emparejamiento**
- Aplicar **lógica de negocio compleja**
- Combinar **múltiples estructuras de datos**
- Resolver problemas con **múltiples restricciones**

---

## 📋 Descripción del Problema

Debes crear un sistema de matchmaking (emparejamiento) para un juego online que forme equipos balanceados. El sistema debe emparejar jugadores considerando su nivel, rango y disponibilidad, intentando crear partidas lo más equilibradas posible.

Cada jugador tiene:
- **Nombre**
- **Nivel** (1-100)
- **Rango** ("Bronce", "Plata", "Oro", "Platino", "Diamante")
- **Puntos ELO** (1000-3000, sistema de puntuación competitiva)
- **Estado**: "buscando", "en_partida", "desconectado"

El sistema debe:
1. Gestionar cola de jugadores buscando partida
2. Formar equipos de 2v2 o 3v3 balanceados
3. Calcular diferencia de ELO entre equipos
4. Validar que los equipos estén equilibrados
5. Mostrar partidas creadas
6. Actualizar estado de jugadores

---

## 📥 Entrada

Solicitar:
1. Cantidad de jugadores a registrar
2. Datos de cada jugador
3. Modo de juego (2v2 o 3v3)
4. Ejecutar matchmaking

---

## 📤 Salida Esperada (orientativa)

```
=== SISTEMA DE MATCHMAKING ===

1. Registrar jugadores
2. Agregar jugador a cola
3. Ver jugadores en cola
4. Ejecutar matchmaking 2v2
5. Ejecutar matchmaking 3v3
6. Ver partidas activas
0. Salir

Opción: 2

-- AGREGAR A COLA --
Nombre del jugador: DragonSlayer
✓ DragonSlayer agregado a la cola (ELO: 1850, Oro)

Opción: 3

=== JUGADORES EN COLA ===
1. DragonSlayer | ELO: 1850 | Oro | Nivel: 45
2. MagicWizard | ELO: 1920 | Oro | Nivel: 52
3. NightHunter | ELO: 1780 | Plata | Nivel: 38
4. IronWarrior | ELO: 2100 | Platino | Nivel: 67

Total: 4 jugadores buscando partida

Opción: 4

🎮 EJECUTANDO MATCHMAKING 2v2...

✓ PARTIDA #1 CREADA

EQUIPO A (ELO promedio: 1885):
  • DragonSlayer (ELO: 1850, Oro, Nivel 45)
  • MagicWizard (ELO: 1920, Oro, Nivel 52)

VS

EQUIPO B (ELO promedio: 1940):
  • NightHunter (ELO: 1780, Plata, Nivel 38)
  • IronWarrior (ELO: 2100, Platino, Nivel 67)

Diferencia de ELO: 55 puntos
Balance: ⚖️  ACEPTABLE (< 100 puntos)

Jugadores movidos a estado "en_partida"
Cola vaciada.

Opción: 6

=== PARTIDAS ACTIVAS ===
Partida #1 (2v2):
  Equipo A: DragonSlayer, MagicWizard
  Equipo B: NightHunter, IronWarrior
  Balance ELO: 55 pts

Total: 1 partida(s) activa(s)
```

---

## 🧪 Casos de Prueba

### **Caso 1: Matchmaking 2v2 perfecto**
- 4 jugadores con ELO similar (±50)
- Debe crear 1 partida balanceada

### **Caso 2: Matchmaking desbalanceado**
- Jugadores con ELO muy diferentes (>200)
- Debe mostrar advertencia de desbalance

### **Caso 3: Jugadores insuficientes**
- Solo 3 jugadores en cola para 2v2
- Debe indicar que faltan jugadores

### **Caso 4: Matchmaking 3v3**
- 6 jugadores en cola
- Crear 2 equipos de 3

---

## ✅ Criterios de Éxito

- [ ] Gestiona cola de jugadores correctamente
- [ ] Forma equipos del tamaño correcto (2v2 o 3v3)
- [ ] Calcula ELO promedio de cada equipo
- [ ] Calcula diferencia de ELO entre equipos
- [ ] Valida balance (diferencia < 100 puntos = aceptable)
- [ ] Actualiza estado de jugadores
- [ ] Maneja casos de jugadores insuficientes
- [ ] Almacena partidas creadas

---

## 💡 Pistas Generales

1. **Algoritmo básico de matchmaking:**
   - Verificar cantidad mínima de jugadores
   - Dividir jugadores en dos equipos
   - Calcular ELO promedio de cada equipo
   - Validar diferencia de ELO

2. **Balance de equipos:**
   - Intentar distribuir jugadores alternando entre equipos
   - O ordenar por ELO y distribuir equilibradamente

3. **Estados de jugador:**
   - "buscando" → en cola
   - "en_partida" → jugando
   - "desconectado" → no disponible

---

## 📝 Conceptos Técnicos Relevantes

### **Sistema ELO:**
Sistema de puntuación usado en ajedrez y videojuegos competitivos. Representa la habilidad relativa del jugador.

### **Balance de equipos:**
Diferencia de ELO promedio entre equipos:
- < 50 pts: Muy balanceado
- 50-100 pts: Aceptable
- > 100 pts: Desbalanceado

---

## 🎯 Desafíos Opcionales

1. **Optimización:** Encontrar la mejor distribución posible (mínima diferencia ELO)
2. **Restricciones de rango:** No emparejar Bronce con Diamante
3. **Historial:** Evitar emparejar jugadores que ya jugaron juntos recientemente
4. **Prioridad:** Dar prioridad a jugadores que llevan más tiempo esperando
5. **MMR oculto:** Sistema de matchmaking rating interno separado del ELO visible
