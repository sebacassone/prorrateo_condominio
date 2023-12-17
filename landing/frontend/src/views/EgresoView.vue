
<template>
    <v-app>
        <v-app-bar  color="#1a1a1a" :elevation="5" height="150">
            <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <h1 class="text-center" id="Titulo">Emitir Egreso</h1>
            
            <div id="Marco_logo">
                <img class="soge-logo" src="../components/images/soge_logo3.png" alt="Logo">
            </div>
        </v-app-bar>


        <v-navigation-drawer  v-model="drawer" temporary>
        <v-list-item
          prepend-avatar="https://randomuser.me/api/portraits/lego/2.jpg"
          
        >{{nombre_user}}</v-list-item>

        <v-divider></v-divider>
        <!-- Boton De -->
        <v-list density="compact" nav>
          <v-list-item
            @click="logout"
            prepend-icon="mdi-logout"
            title="Logout"
          ></v-list-item>
         
        </v-list>
      </v-navigation-drawer>
    
    <v-main class="my-main">
    <v-container id="main">
      <v-row>
        <v-col cols="12" md="6">
          <!-- Contenido del primer bloque -->
          <v-card
            class="rounded-lg px-10 py-5 align-center text-center"
            :title="saludo"
            font-weight-bold
            color="#1da368"
            :elevation="3"

          
          >
          <v-form >
              <v-text-field
                  v-model="monto"
                  :rules="montoRules"
                  label="Monto"
                  variant="solo-filled"
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
                  variant="solo-filled"
                  placeholder="Ingresa una descripción"
                  hint="Máximo 120 caracteres"                      
              ></v-text-field>
              <v-text-field
                  v-model="date"
                  :rules="contraRules"
                  label="Fecha"
                  variant="solo-filled"
                  readonly
                  placeholder="Selecciona fecha desde el calendario"
                                      
                  ></v-text-field>
              <v-autocomplete
                  v-model="categoria"
                  required
                  label="Categoría"
                  :items="lista_categorias"
                  variant="solo-filled"
                  placeholder="Selecciona el tipo de gasto"
              ></v-autocomplete>
              <v-card-actions class="justify-center">
                            <div class="btn-container">
                                <v-btn
                                    class="rounded-lg text-white px-5"
                                    :loading="loading"
                                    prepend-icon="mdi-check-circle"
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
                        <v-alert class="alert-custom"
                            margin-top="10px"
                            v-model="notifEgreso"
                            closable
                            :text="textoAlerta"
                            type="success"
                            variant="outlined"
                            height="70px"
                            width="auto"
                            
                        ></v-alert>
            </v-form>
        </v-card>
        
        </v-col>
        <v-divider vertical></v-divider>
        <v-col cols="12" md="6">
          <!-- Contenido del segundo bloque -->
          <v-card
            class="rounded-lg px-10 py-5 align-center text-center size-card-title"
            title="Selecciona Fecha"
            color="#1da368"
            :elevation="3"
          > 
            <v-date-picker class="selector-fecha"
                v-model="date"
                required
                title="Seleccione fecha de emisión"
                show-adjacent-months
                :max="today"
                bg-color="#1a1a1a"
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
        color: #ffff;
        margin-top: 10px;
        margin-left: 10px;
    }

    #Marco_logo{
        align-self: right;
        display: flex;
        align-items: right;
        margin-left: 10px;
        margin-top: 10px;
        width: 142px;
    }
    .my-main{
        background-color: #fff9ef;
        margin: 0px;
    }
    .selector-fecha{
        margin-top: 20px;
        margin-left: 70px;
        margin-right: 20px;
        margin-bottom: 20px;
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
    
    padding-bottom: 10px;
    height: 140px;
}
    .v-main{
        margin-top: 50px;
        background-color: #fff9ef;
    }

    .size-card-title{
        font-size: 100px;
    }
    .alert-custom{
        background-color: #1a1a1a;
        margin-top: 20px;
        opacity: 0.6;
        color: #f6f6f6;
        border: 1px solid #f6f6f6;
        border-radius: 5px;
        font-size: 14px;
        font-weight: bold;
        text-align: center;
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
            nombre_user: 'Nombre Usuario',
            desc: '',
            drawer: false,
            textoAlerta: 'Egreso emitido con éxito',
            notifEgreso: false,
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
            
                // Obtener el objeto de usuario de localStorage
                const storedUserDataJSON = localStorage.getItem('userData');
                if (storedUserDataJSON) {
                  // Convertir la cadena JSON a un objeto
                    const storedUserData = JSON.parse(storedUserDataJSON);
                    this.nombre_user = storedUserData.nombre;
                
                  // Verificar si tipoUsuario existe
                    if (storedUserData.hasOwnProperty('tipoUsuario')) {
                        // Redirigir basado en el valor de tipoUsuario
                        if (storedUserData.tipoUsuario === 0) {
                            console.log('EGRESOFETCH: navegando a EmitirEgreso');
                            this.$router.push('/EmitirEgreso');
                        } else {
                            console.log('EGRESOFETCH: navegando a GastoComun');
                            this.$router.push('/GastoComun');
                        }
                    } else {
                        // tipoUsuario no existe, redirigir a login
                        console.log('EGRESOFETCH: navegando a login');
                        this.$router.push('/login');
                    }
                } else {
                  // userData no existe en localStorage, redirigir a login
                  this.$router.push('/login');
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
                    this.notifEgreso = true;
                } catch (error) {
                    console.log('B');
                    
                }
            },
            logout() {
                console.log('logout');
                localStorage.removeItem('userData');
                this.$router.push('/login');
            },
              
            
        },
        mounted() {
            console.log('mounted_egreso');
            //this.checkUserTypeAndRedirect();
            this.initFetch();
        },
    };
</script>