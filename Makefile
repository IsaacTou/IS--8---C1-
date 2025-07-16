JAVAC = javac
JAVA = java
SRC = $(shell find src/main -name '*.java')
OBJ = $(patsubst src/main/%.java, src/main/%.class, $(SRC))
MAIN = src/main/Main

all: run

run: $(MAIN)
	$(JAVA) $(MAIN)

$(MAIN):
	@$(JAVAC) $(SRC)

clean:
	rm -r $(OBJ)
