# ValenBisi Android App

Aplicación Android que muestra la disponibilidad de bicicletas de Valenbisi en tiempo real usando el CSV oficial.

---

## 📂 Estructura del proyecto

- `app/src/main/java/com/erreerre/valenbisi/`
  - `MainActivity.kt` → Lista de estaciones (RecyclerView)
  - `DetalleActivity.kt` → Detalles de la estación con mapa
  - `EstacionAdapter.kt` → Adaptador para el RecyclerView
  - `Estacion.kt` → Modelo de datos para cada estación

- `app/src/main/res/layout/`
  - `activity_main.xml` → Layout principal con RecyclerView
  - `activity_detalle.xml` → Layout de detalles de la estación
  - `item_estacion.xml` → Layout de cada tarjeta de estación (MaterialCardView)

- `app/src/main/assets/valenbisi.csv` → CSV oficial con datos de Valenbisi

---

## ⚡ Funcionalidades

- Muestra dirección, bicis disponibles y espacios libres de cada estación
- Colores dinámicos según disponibilidad:
  - 🔴 Rojo → 0 bicis
  - 🟡 Amarillo → 1–4 bicis
  - 🟢 Verde → 5 o más bicis
- Al pulsar una estación, se abre `DetalleActivity` con los datos completos
- Material Design 3 y MaterialCardView para un diseño moderno
- Carga dinámica del CSV
