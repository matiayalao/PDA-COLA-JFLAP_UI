# PDA CON COLA

Como dice la descripción del repositorio, es una versión modificada del JFLAP original para que el PDA opere con esta definición:
<q,x,φ>⊢<q',x',φ'> por medio de δ(q,i,A) = <q',α>
si: x = ix'
φ = Aβ
φ' = βα

## Dependencia

Tener instalado el java (a mí me sirvió la 17.0.12, faltaría testing con otras versiones)

Instalar la librería batik (Ejemplo en Arch Linux)

```bash
sudo pacman -S java-batik
```

## Uso
Entrar en la raíz del proyecto (donde se halla MakeFile) y ejecutar:

```bash
make clean
```


```bash
make
```

```bash
java -jar JFLAP.jar
```


### Precuación
Con ciertos caracteres (como #) puede no funcionar las transiciones hechas, se recomienda usar letras.

## Archivos cambiados
El repositorio original se encuentra en este link:
https://github.com/evandroforks/JFLAP_FormalLanguagesExperimenting

### gui.Main.java
El código original de JFLAP incluye un método:
```java
public static void main(String[] args, boolean dont)
```
Esto NO es un entry point válido para la JVM. La máquina virtual únicamente reconoce:
```java
public static void main(String[] args)
```

Para solucionar esto, se creó un método main(String[] args) estándar que:

-Lee los argumentos pasados desde la terminal.

-Detecta si el último argumento es true o false (parámetro dont original).

-Llama internamente al main(String[], boolean) original.

-Esto preserva la funcionalidad de JFLAP y hace que el programa sea ejecutable.

### mainFile
En este archivo mostraba:
```java
Main-Class: JFLAP
```
Pero no existe ninguna clase llamada JFLAP en el código.
El entry point real está en:
```java
gui.Main
```

### MakeFile:
Esto fue más un error del IDE, y y consistió en que el IDE no podía ver la dependencia SVGGraphics2D, entonces en el makeFile se carga manualmente esta dependencia

### automata/pda/CharacterStack.java
Y acá obviamente se cambió la lógica para que cumpla con la definición de PDA con cola que se puso en el examen.
