# 8Puzzle

## Autores
- Miguel C. Canedo R.     13-10214
- Andres A. Buelvas V.    13-10184

## Instalacón de Groovy
  La forma mas sencilla de instalar Groovy en cualquier plataforma Bash es usando la herramienta **SDKMAN!**. Seguidamente se ilustran dos maneras de poder instalar Groovy mediante el suo de esta herramienta:
  
### Easy Way
  En este repositorio se facilita un ejecutable para esta instalacion usando dicha herramienta y si lo desean utilizar deben seguir estos pasos:
  1. Abrir una Terminal
  2. Clonar el repositorio
  ```bash
git clone https://github.com/migcanedo/8Puzzle 8Puzzle
```
  3. Entrar al Directorio **8Puzzle**.
  ```bash
cd 8Puzzle/
```
  4. Dar permisos de ejecucion al script de instalacion de Groovy.
```bash
chmod 755 installGroovy.sh
```
  5. Ejecutar dicho script para proceder a la instalacion.
```bash
./installGroovy.sh
```
  6. Por ultimo, para probar que la instalacion finalizo satisfactoria, revisamos la version de Groovy.
```bash
groovy -version
```
  
### Hard Way
  Pero si usted es una persona curiosa que desea saber que trabajos realiza nuestro script, aqui le dejamos los pasos que debe realizar para la instalacion de Groovy pero sin hacer uso del script facilitado:
  
  1. Abrir una nueva Terminal.
  2. Introduzca la siguiente linea en ella.
```bash
curl -s get.sdkman.io | bash
```
  3. Siga las instrucciones en pantalla para completar la instalacion.
  4. Luego, introduzca la siguiente linea.
```bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```
  5. Luego instale la version mas reciente de Groovy.
```bash
sdk install groovy
```
  6. Por ultimo, para probar que la instalacion finalizo satisfactoria, revisamos la version de Groovy.
```bash
groovy -version
```
  

## Corrida del Proyecto
  Para la corrida en este repositorio se facilita un directorio con diferentes *Casos de Prueba*, y para ello se deben seguir los siguientes pasos:
  1. Abrir una Terminal.
  2. Clonar el repositorio (En caso de haber instalado Groovy medienate el *'Easy Way'*, puede saltarse este paso).
```bash
git clone https://github.com/migcanedo/8Puzzle 8Puzzle
```
  3. Entrar al Directorio **8Puzzle**.
```bash
cd 8Puzzle/
```
  4. Correr el programa `8Puzzle.groovy` suministrandole la direccion del caso prueba que desee utilizar.
```bash
groovy 8Puzzle.groovy casosPrueba/caso1.txt
```
  5. Observe en la Terminal el resultado obtenido del programa.
