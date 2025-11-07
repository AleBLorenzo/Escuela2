# 🎮 Ejercicio 20: Simulador de Torneo

## 🎯 Objetivos de Aprendizaje
- **Integración completa** de todos los conceptos aprendidos
- Diseñar **sistemas complejos** con múltiples componentes
- Implementar **lógica de torneo** (eliminatorias, grupos)
- Gestionar **datos persistentes** y **estadísticas avanzadas**

---

## 📋 Descripción del Problema

Debes crear un simulador completo de torneo de videojuegos que gestione inscripciones, genere enfrentamientos, simule resultados, y determine un campeón. El sistema debe integrar gestión de jugadores, generación de brackets, simulación de partidas, y rankings finales.

El simulador debe:
1. Gestionar inscripción de jugadores al torneo
2. Generar bracket de eliminación simple (8, 16 o 32 jugadores)
3. Simular partidas con resultados basados en ELO
4. Avanzar ganadores a siguientes rondas
5. Determinar campeón y podio (top 3)
6. Generar estadísticas completas del torneo
7. Mostrar historial de todas las partidas

---

## 📥 Entrada

Solicitar:
1. Nombre del torneo
2. Modo (eliminación simple)
3. Cantidad de jugadores (potencia de 2: 8, 16, 32)
4. Datos de cada jugador inscrito
5. Ejecutar simulación automática o manual

---

## 📤 Salida Esperada (orientativa)

```
=== SIMULADOR DE TORNEO ===

Nombre del torneo: Copa de Campeones 2025
Modo: Eliminación Simple
Jugadores inscritos: 8/8

🏆 BRACKET GENERADO

=== CUARTOS DE FINAL ===
Partida 1: DragonSlayer (ELO 1850) vs MagicWizard (ELO 1920)
Partida 2: NightHunter (ELO 1780) vs IronWarrior (ELO 2100)
Partida 3: ShadowNinja (ELO 1650) vs StormMage (ELO 1890)
Partida 4: FireKnight (ELO 1720) vs IceQueen (ELO 1810)

Presiona ENTER para simular...

⚔️ SIMULANDO CUARTOS DE FINAL...

Partida 1: MagicWizard GANA (2-1) vs DragonSlayer
Partida 2: IronWarrior GANA (2-0) vs NightHunter
Partida 3: StormMage GANA (2-1) vs ShadowNinja
Partida 4: IceQueen GANA (2-1) vs FireKnight

=== SEMIFINALES ===
Partida 5: MagicWizard (ELO 1920) vs IronWarrior (ELO 2100)
Partida 6: StormMage (ELO 1890) vs IceQueen (ELO 1810)

Presiona ENTER para simular...

⚔️ SIMULANDO SEMIFINALES...

Partida 5: IronWarrior GANA (2-0) vs MagicWizard
Partida 6: StormMage GANA (2-1) vs IceQueen

=== FINAL ===
Partida 7: IronWarrior (ELO 2100) vs StormMage (ELO 1890)

Presiona ENTER para simular la FINAL...

⚔️ SIMULANDO FINAL...

🏆 IronWarrior GANA (3-1) vs StormMage

=== PODIO FINAL ===

🥇 CAMPEÓN: IronWarrior
   ELO: 2100 | Nivel: 67 | Rango: Platino
   Partidas: 3 | Victorias: 3 | Ratio: 7-2

🥈 SUBCAMPEÓN: StormMage
   ELO: 1890 | Nivel: 55 | Rango: Oro
   Partidas: 3 | Victorias: 2 | Ratio: 5-3

🥉 TERCER LUGAR: MagicWizard
   ELO: 1920 | Nivel: 52 | Rango: Oro
   Partidas: 2 | Victorias: 1 | Ratio: 3-2

=== ESTADÍSTICAS DEL TORNEO ===
Total de partidas: 7
Promedio de puntos por partida: 2.57
Partida más reñida: Partida 4 (IceQueen 2-1 FireKnight)
Partida más dominante: Partida 2 (IronWarrior 2-0 NightHunter)
```

---

## 🧪 Casos de Prueba

### **Caso 1: Torneo de 8 jugadores**
- 3 rondas (cuartos, semis, final)
- Total de 7 partidas
- 1 campeón

### **Caso 2: Simulación basada en ELO**
- Jugador con mayor ELO tiene mayor probabilidad de ganar
- Pero no siempre gana (factor aleatorio)

### **Caso 3: Bracket correcto**
- Ganadores avanzan correctamente
- No hay bye (todos juegan)

---

## ✅ Criterios de Éxito

- [ ] Gestiona inscripción de jugadores
- [ ] Genera bracket correcto (potencia de 2)
- [ ] Simula partidas con lógica basada en ELO
- [ ] Avanza ganadores a siguiente ronda
- [ ] Determina campeón y podio
- [ ] Calcula estadísticas del torneo
- [ ] Muestra historial completo

---

## 💡 Pistas Generales

1. **Generación de bracket:**
   - Usar arrays o ArrayList para cada ronda
   - Ronda 1: N/2 partidas
   - Ronda 2: N/4 partidas
   - Etc.

2. **Simulación de partida:**
   - Probabilidad basada en diferencia de ELO
   - Mayor ELO = mayor probabilidad
   - Usar Random para factor de aleatoriedad

3. **Determinar ganador:**
   - Best of 3 (BO3): primero en ganar 2
   - Best of 5 (BO5): primero en ganar 3

---

## 📝 Conceptos Técnicos Relevantes

### **Bracket de Eliminación Simple:**
Estructura de torneo donde perder una partida elimina al jugador.

### **Simulación basada en ELO:**
```
Probabilidad de victoria = 1 / (1 + 10^((ELO_oponente - ELO_jugador)/400))
```

---

## 🎯 Desafíos Opcionales

1. **Torneo suizo:** En lugar de eliminación directa
2. **Grupos + Playoffs:** Fase de grupos seguida de eliminatorias
3. **Seeding:** Ordenar jugadores por ELO antes del bracket
4. **Guardar torneo:** Persistir resultados en archivo
5. **Replay:** Poder ver cualquier partida del historial
