SOURCEDIRS = automata file grammar gui regular pumping debug
APACHE = org
# ¡Corregido! No incluimos ningún .class aquí.
AUX = DOCS ICON MEDIA Makefile mainFile README.md LICENSE ChangeLog.txt

all: source-included ws two-jar
	rm build


source-included: build
	find $(SOURCEDIRS)  -name "*.class" -o -name "*.java" > OUTYOUTY
	jar cmf mainFile JFLAP_With_Source.jar @OUTYOUTY $(AUX) $(APACHE)
	rm OUTYOUTY

without-source ws: build
	find $(SOURCEDIRS)  -name "*.class" > OUTYOUTY
	jar cmf mainFile JFLAP.jar @OUTYOUTY $(AUX) $(APACHE)
	rm OUTYOUTY

two-jar: build
	find $(SOURCEDIRS) -name "*.class" > OUTYOUTY
	jar cmf mainFile JFLAP_Thin.jar @OUTYOUTY $(AUX)
	rm OUTYOUTY

	# --- INICIO DE LA VERSIÓN MEJORADA (TU LÓGICA) ---
	rm -rf temp_deps && mkdir -p temp_deps
	cp -r org/* temp_deps/

	# 1. Tu idea (simple y directa para batik)
	cd temp_deps
	jar -xf /usr/share/java/batik/batik-all-1.17.jar

	# 2. Mi adición (lo que faltaba, usando TU lógica)
	# Extraemos todos los JARs de xmlgraphics en este mismo directorio
	find /usr/share/java/xmlgraphics-commons/ -name "*.jar" -exec sh -c 'jar -xf "$0"' {} \;

	cd ..
	jar cf svg.jar -C temp_deps .
	rm -rf temp_deps

build:
	find . -name "*.java" | xargs javac \
		--add-exports java.xml/com.sun.org.apache.xalan.internal.xsltc.compiler=ALL-UNNAMED \
		--add-exports java.base/sun.security.util=ALL-UNNAMED \
		-cp ".:/usr/share/java/batik/*:/usr/share/java/xmlgraphics-commons/*"
	touch build


################################################################################
# Comentarios...
################################################################################
clean:
	find $(SOURCEDIRS) \( -name "*.class" -o -name "*~" -o -name ".DS_Store" \) \
       -a -delete
	# Limpia TODOS los archivos generados
	rm -f JFLAP.jar JFLAP_With_Source.jar JFLAP_Thin.jar svg.jar
	rm -f build
	rm -f OUTYOUTY
	rm -rf temp_deps