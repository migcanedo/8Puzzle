# 8Puzzle

## Autores
- Miguel C. Canedo R.     13-10214
- Andres A. Buelvas V.    13-10184

## Instalacón de Groovy
  La forma mas sencilla de instalar Groovy en cualquier plataforma Bash es usando la herramienta SDKMAN!, en este repositorio se facilita un ejecutable para esta instalacion usando dicha herramienta y si lo desean utilizar deben seguir estos pasos:
  - Abrir una Terminal
  - Clonar el repositorio
  ```bash
git clone https://github.com/migcanedo/8Puzzle 8Puzzle
```
  - Entrar al Directorio 8Puzzle.
  ```bash
cd 8Puzzle/
```
  - Dar permisos de ejecucion al script de instalacion de Groovy.
```bash
sudo chmod 755 installGroovy.sh
```
  - Ejecutar dicho script para proceder a la instalacion.
```bash
sudo ./installGroovy.sh
```
  - Por ultimo, para probar que la instalacion finalizo satisfactoria, revisamos la version de Groovy.
```bash
groovy -version
```

  Pero si usted es una persona curiosa que desea saber trabajos realiza nuestro script, aqui le dejamos los pasos que debe realizar para la instalacion de Groovy sin el uso del script:
  
  - Abrir una nueva Terminal.
  - Introduzca la siguiente linea en ella.
```bash
curl -s get.sdkman.io | bash
```
  - Siga las instrucciones en pantalla para completar la instalacion.
  - Luego, introduzca la siguiente linea.
```bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```
  - Luego instale la version mas reciente de Groovy.
```bash
sdk install groovy
```
  - Por ultimo, para probar que la instalacion finalizo satisfactoria, revisamos la version de Groovy.
```bash
groovy -version
```
  

## Corrida del Proyecto
