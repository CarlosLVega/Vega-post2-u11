# Vega Post-Contenido 2 U11

Proyecto Java para el Post-Contenido 2 de la Unidad 11: Refactorizacion Avanzada y Clean Code Profundo.

## Estado del laboratorio

### Checkpoint 1: pruebas base

- Lenguaje: Java.
- Framework: Spring Boot con Maven.
- Se agrego el codigo inicial con los smells solicitados:
  - `calcularEnvio`: `switch` con complejidad ciclomatica alta.
  - `aprobarCredito`: arrow code con condicionales anidados.
- Se agregaron pruebas JUnit 5 antes de refactorizar para documentar el comportamiento actual.

## Comandos

```bash
mvn test
```

## Metricas SonarQube

| Metodo | CC antes | CC despues | Tecnica aplicada |
| --- | ---: | ---: | --- |
| `calcularEnvio` | 5 | Pendiente | Replace Conditional with Polymorphism |
| `aprobarCredito` | 6 | Pendiente | Guard Clauses |

## Evidencia Quality Gate

Pendiente de agregar captura del dashboard de SonarQube con estado Passed despues del segundo analisis.

## Reflexion Open/Closed

Pendiente de completar despues de aplicar Strategy.
