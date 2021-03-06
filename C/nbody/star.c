#include "globals.h"
#include "star.h"

int collision(sStar* s1, sStar* s2)
{
  float d = calcDistance(s1, s2);
  return (d < 1.0f) ? 1 : 0;
}

sStar* createStar(float x, float y, float vx, float vy, float mass)
{
  sStar* ret = malloc(sizeof(sStar));
  ret->X = x;
  ret->Y = y;
  ret->Mass = mass;
  ret->aX = .0f;
  ret->aY = .0f;
  ret->vX = vx;
  ret->vY = vy;
  ret->fX = .0f;
  ret->fY = .0f;
  // Set the colour of the star relative to it's mass - heavier stars are red, lighter stars are green.
  Uint8 red   = (Uint8)(174.0f*(mass/(MAXMASS*3.0f)) + 80.0f);
  Uint8 green = 255-red;//(Uint32)(255 - 174.0*(mass/(MAXMASS)));
  Uint8 blue = 0;
  Uint8 alpha = 255;
  // Pack the colors into an unsigned, 32 bit integer (a Uint32)
  // in this order: msb : AAAAAAAA RRRRRRRRR GGGGGGGG BBBBBBBB : lsb
  ret->Color = (alpha << 24) +  (red << 16) + (green << 8) + blue;
  return ret;
}

// Simple wrapper for freeing the memory used by a star; wrapped in case one should want to store dynamic data inside a star that would itself need to be freed as well.
void destroyStar(sStar* star)
{
  free(star);
}

// Draw star. Pixels is a dynamically allocated array of color-values, describing the color of any individual pixel in the window's drawing space.
void drawStar(sStar* star, Uint32* Pixels)
{
  // Sanity check
  if(star == NULL)
    return;
  int x = (int)star->X;
  int y = (int)star->Y;
  // Can't draw outside of window boundaries.
  if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT)
    return;
  // Draw out a star  based on its mass to max-mass ratio
  Uint32 color = star->Color;
  float radius = ((star->Mass / (MAXMASS*10.0f)) * 2.0f)+1;
  drawCircle(x, y, color, (int)radius, Pixels);
}

float calcDistance(sStar* s1, sStar* s2)
{
  float dx = s2->X - s1->X;
  float dy = s1->Y - s2->Y;
  return sqrt(dx*dx + dy*dy);
}

void calculateForce(sStar* s1, sStar* s2)
{
  float dist = calcDistance(s1, s2) * 5000.0f;
  float dx = s2->X - s1->X;
  float dy = s2->Y - s1->Y;
  float angle = atan2(dy, dx);
  float m1 = s1->Mass;
  float m2 = s2->Mass;
  float m = m1 * m2;
  float F = (G_CONST * m) / ((dist*dist));
  if(F > 1000000.0f)
    F = 1000000.0f;
  if(F < -1000000.0f)
    F = -1000000.0f;
  s1->fX += F * cos(angle);
  s1->fY += F * sin(angle);
  s2->fX += -1.0f * s1->fX;
  s2->fY += -1.0f * s1->fY;
}
