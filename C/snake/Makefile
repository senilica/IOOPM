CC = gcc
OBJ = global.o LinkedList.o SDLWrapper.o game.o main.o
TARGET = snake
SDL_CFLAGS := $(shell sdl2-config --cflags)
SDL_LIBS := $(shell sdl2-config --libs) -lSDL2_ttf
CFLAGS = -Wall -std=c99 -ggdb $(SDL_CFLAGS)
PROFILE = 

release: CFLAGS = -O3 -std=c99 $(SDL_CFLAGS)
profile: PROFILE = -pg

.PHONY : all
all: $(OBJ)
	$(CC) $(CFLAGS) -o $(TARGET) $(OBJ) $(SDL_LIBS) $(PROFILE)

.PHONY : profile
profile: all

.PHONY : release
release: all

%.o : %.c
	$(CC) -c $(CFLAGS) $< $(PROFILE)

.PHONY : clean
clean:
	-rm $(TARGET) *.o
