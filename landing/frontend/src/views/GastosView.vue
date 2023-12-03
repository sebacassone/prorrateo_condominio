<template>
    <v-container fluid>
      <v-row>
        <v-col cols="12" md="6">
          <!-- Contenido del primer bloque -->
          <div class="border-box"></div>
        </v-col>
  
        <v-col cols="12" md="6">
          <!-- Contenido del segundo bloque -->
          <div class="border-box"></div>
        </v-col>
      </v-row>
    </v-container>
  </template>
  
  <style scoped>
  .border-box {
    border: 2px solid red;
    height: 300px;
    background-color: #2D2D2D;
  }
  </style>