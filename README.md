# Proyecto Pac-Man

**Asignatura:** Laboratorio y Desarrollo de Herramientas  
**Repositorio:** [Proyecto_LDH en GitHub](https://github.com/PaulaDariasSosa/Proyecto_LDH)  
**Autores:** Paula María Darias Sosa, Daniel del Rosario Pimienta, Eduardo González Guitiérrez

---

## Descripción del Proyecto

Este proyecto es una implementación de un juego estilo **Pac-Man** en **Java**. El juego cuenta con tres niveles progresivos de dificultad y se ha desarrollado como parte de la asignatura **Laboratorio y Desarrollo de Herramientas**. Durante el desarrollo, se han aplicado metodologías de control de dependencias, documentación y análisis de código mediante herramientas como **Maven**, **Doxygen** y **SonarCloud** para mejorar la estructura, calidad y documentación del código.

## Características

- **Tres niveles de dificultad**: Cada nivel incrementa la complejidad y velocidad de los enemigos.
- **Lógica de Juego Completa**: Reglas de colisión, conteo de puntos y vidas.
- **Enemigos Inteligentes**: Los enemigos siguen una lógica básica para perseguir a Pac-Man.
- **Interfaz Gráfica**: Representación visual del laberinto y personajes.

## Requisitos

- **Java 11** o superior
- **Maven** para gestión de dependencias y compilación
- **Doxygen** para la generación de documentación
- **SonarCloud** para el análisis de calidad del código

## Instalación y Ejecución

### Clonar el Repositorio

Clona el repositorio del proyecto desde GitHub:

```bash
git clone https://github.com/PaulaDariasSosa/Proyecto_LDH.git
cd Proyecto_LDH
```

## Comandos Básicos de Maven

### Limpiar el Proyecto: `mvn clean`

El comando `mvn clean` elimina todos los archivos generados en construcciones anteriores. Esto es útil para asegurar que cada compilación comience desde cero, eliminando dependencias, archivos de compilación y otros archivos temporales.

```bash
mvn clean
```

## Análisis SonarCloud
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=PaulaDariasSosa_Proyecto_LDH)](https://sonarcloud.io/summary/new_code?id=PaulaDariasSosa_Proyecto_LDH)
