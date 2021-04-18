CC=gcc
CFLAGS=-std=c99 -Wextra -Wall -pedantic 
LDFLAGS=-lm

ifeq ($(DEBUG),yes)
	CFLAGS += -g
	LDFLAGS +=
else
	CFLAGS += -O3 -DNDEBUG
	LDFLAGS +=
endif

EXEC=blockchain_test
SRC= $(wildcard *.c)
OBJ= $(SRC:.c=.o)

all: 
ifeq ($(DEBUG),yes)
	@echo "Generating in debug mode"
else
	@echo "Generating in release mode"
endif
	@$(MAKE) $(EXEC)

$(EXEC): $(OBJ)
	@$(CC) -o $@ $^ $(LDFLAGS)

%.o: %.c
	@$(CC) -o $@ -c $< $(CFLAGS)

.PHONY: clean mrproper

clean:
	@rm -rf *.o

mrproper: clean
	@rm -rf $(EXEC) documentation/html

doc: blockchain.h main.c
	@doxygen documentation/TP3
	
main.o:  blockchain.o

DELIVER_FMT=$(shell date "+$(shell id -un)_%d-%m-%y_%Hh%Mm%Ss")

deliver:
	zip -r $(DELIVER_FMT).zip .

