<template>
    <v-app>
      <v-app-bar class="Head"  color="rgb(226, 235, 171)" :elevation="3" height="130">
        <h1 class="text-center" id="Titulo">Emitir Egreso</h1>

            <div id="Marco_logo">
                <img class="soge-logo" src="../components/images/soge_logo2.png" alt="Logo">
            </div>
        </v-app-bar>

        <v-navigation-drawer color="rgb(226, 235, 171)" rail permanent>
        <template v-slot:append>
          <div class="pa-2">
            <v-btn block href="/login" color="rgb(226, 235, 171)">
              <v-icon  icon="mdi-logout"></v-icon>
            </v-btn>
          </div>
        </template>
        </v-navigation-drawer>
    
    <v-main class="my-main">
    <v-container id="main">
      <v-row>
        <v-col cols="12" md="6">
          <!-- Contenido del primer bloque -->
          <v-card
            class="rounded-lg px-10 py-5 align-center text-center"
            :title="saludo"
            color="#90a955"
            :elevation="3"

          
          >
          <v-form >
              <v-text-field
                  v-model="monto"
                  :rules="montoRules"
                  label="Monto"
                  variant="underlined"
                  required
                  placeholder="Ingrese solo valores numéricos"
                  hint="Ejemplo: 123000"                      
              ></v-text-field>
              <v-text-field
                  v-model="desc"
                  required
                  :counter="120"
                  :rules="contraRules"
                  maxlength="120"
                  label="Descripción"
                  variant="underlined"
                  placeholder="Ingresa una descripción"
                  hint="Máximo 120 caracteres"                      
              ></v-text-field>
              <v-text-field
                  v-model="date"
                  :rules="contraRules"
                  label="Fecha"
                  variant="underlined"
                  required
                  placeholder="Selecciona fecha desde el calendario"
                  hint="Ejemplo: 123000"                      
                  ></v-text-field>
              <v-autocomplete
                  v-model="categoria"
                  label="Categoría"
                  :items="lista_categorias"
                  variant="underlined"
                  placeholder="Selecciona el tipo de gasto"
              ></v-autocomplete>
              <v-card-actions class="justify-center">
                            <div class="btn-container">
                                <v-btn
                                    class="rounded-lg text-white px-5"
                                    :loading="loading"
                                    
                                    size="large"
                                    @click="sendEgresoForm"
                                    variant="tonal"
                                >
                                    <div class="text-h font-weight-medium text-white">
                                    Emitir Egreso
                                    </div>
                                </v-btn>
                            </div>
                        </v-card-actions>

            </v-form>
        </v-card>
        </v-col>
        <v-divider color="warning" vertical></v-divider>
        <v-col cols="12" md="6">
          <!-- Contenido del segundo bloque -->
          <v-card
            class="rounded-lg px-10 py-5 align-center text-center size-card-title"
            title="Selecciona Fecha"
            color="#90a955"
            :elevation="3"
          > 
            <v-date-picker class="selector-fecha"
                v-model="date"
                required
                title="Seleccione fecha de emisión"
                show-adjacent-months
                :max="today"
                bg-color="#0d2c24"
                position="relative"
                
                >
            </v-date-picker>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
  </v-app>
</template>

<style scoped>
    #Titulo{
    font-size: 40px;
    font-weight: bold;
    color: #0d2c24;
    margin-top: 10px;
    margin-left: 70px;
    }

    .my-main{
        background-color: #f8f8f8;
        margin: 0px;
    }
    .selector-fecha{
        margin-top: 20px;
        margin-left: 70px;
        margin-right: 20px;
        margin-bottom: 20px;
    }

    #Marco_logo{
    display: flex;
    align-items: right;
    margin-right: 100px;
    margin-top: 10px;
    width: 142px;
    }
    header{
        background-color: #90a955;
        height: 150px;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .soge-logo {
    background-color: rgb(226, 235, 171);
    height: 140px;
}

    .v-main{
        margin-top: 50px;
        background-color: #f8f8f8;
    }

    .size-card-title{
        font-size: 100px;
    }



</style>

<script>
    import axios from 'axios';
    import {validarRUT,formatoNumerico} from '../validationUtils.js';

    export default {
        data: () => ({
            rut: '',
            today: new Date().toISOString(),
            date: null,
            saludo: 'SALUDO A INGRESAR',
            monto: '',
            desc: '',
            categoria: '',
            lista_categorias: ['Reparacion', 'Egresos', 'Insumos', 'Sueldos', 'Servicios Básicos', 'Bonos','Otros'],
            contrasena: '', //borrar
            loading: false,
            form: false,
            montoRules: [
                value => {
                    if (value) return true

                    return 'Monto es requerido.'
                },
                value => {
                    if (formatoNumerico(value)) return true
                    
                    return 'No ingrese caracteres especiales !"#$%&.,;:/()="'
                },
            ],
            contraRules: [
                value => {
                    if (value) return true

                    return 'Rellenar campo.'
                },
            ],
        }),

        methods: {
            initFetch() {
                // Recuperar la cadena JSON del localStorage
                console.log('initFetch_egreso');
                const storedUserData = localStorage.getItem('userData');
                const userData = JSON.parse(storedUserData);
                this.saludo = 'Hola '+ userData.nombre + '! Ingresa los datos del egreso.';
                

                // Verificar si storedUserData no es nulo
                if (storedUserData) {
                    console.log('storedUserData_Egreso');
                    // Convertir la cadena JSON a un objeto JavaScript
                    
                    if (userData.tipoUsuario === '0') {
                        this.$router.push({name: 'EmitirEgreso'});
                    }
                    if (userData.tipoUsuario === '1') {
                        this.$router.push({name: 'GastosComunes'});
                    }
                    else {
                        console.log('Navegando a login');
                        this.$router.push({name: 'EmitirEgreso'}); //login

                    }
                }
            },
            async sendEgresoForm() {
                try {
                    console.log('sendEgresoForm');
                    //const userData = JSON.parse(storedUserData);
                    //console.log(userData.rut);
                    // Obtener la cadena JSON del localStorage
                    const storedUserData = localStorage.getItem('userData');

                    // Convertir la cadena JSON a un objeto JavaScript
                    const userData = JSON.parse(storedUserData);

                    // Acceder a la propiedad 'rut' del objeto
                    const userRut = userData.rut;
                    console.log(this.desc);
                    console.log(this.date);
                    console.log(this.monto);
                    
                    const response = await axios.post(
                        'http://localhost:8080/api/v1/registroGastoComun',
                        {
                            descripcion_gasto: this.desc,
                            monto_gasto: parseInt(this.monto),
                            fecha_emision: this.date,
                            id_usuario: userRut.replace('-',''),
                            id_edificio: 1,
                            nombre_categoria: this.categoria
                            
                        }
                    );
                } catch (error) {
                    console.log('B');
                    
                }
            },
            checkUserTypeAndRedirect() {
            // Obtener el objeto de usuario de localStorage
                const storedUserDataJSON = localStorage.getItem('userData');
                if (storedUserDataJSON) {
                  // Convertir la cadena JSON a un objeto
                    const storedUserData = JSON.parse(storedUserDataJSON);
                
                  // Verificar si tipoUsuario existe
                    if (storedUserData.hasOwnProperty('tipoUsuario')) {
                        // Redirigir basado en el valor de tipoUsuario
                        if (storedUserData.tipoUsuario === 0) {
                            this.$router.push('/EmitirEgreso');
                        } else {
                        this.$router.push('/GastoComun');
                        }
                    } else {
                        // tipoUsuario no existe, redirigir a login
                        this.$router.push('/login');
                    }
                } else {
                  // userData no existe en localStorage, redirigir a login
                  this.$router.push('/login');
                }
            },  
            
        },
        mounted() {
                this.checkUserTypeAndRedirect();
                //this.initFetch();
                console.log('mounted_egreso');
            },
    };
</script>