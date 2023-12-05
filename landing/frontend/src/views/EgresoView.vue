<template>
    <header>
      <img
          class="soge-logo"
          height="250"
          src="../components/images/soge_logo2.png"
      ><img/>
    </header>
    
    <v-container fluid>
      <v-row>
        <v-col cols="12" md="6">
          <!-- Contenido del primer bloque -->
          <v-card
            class="rounded-lg px-10 py-5 align-center text-center"
            title="Emitir Egreso"
            :text="saludo"
            color="#0d2c24"
            width="450"
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
                                    @click="login"
                                    variant="outlined"
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
            variant="tonal"
            color="#000000"
          > 
            <v-date-picker
                v-model="date"
                required
                title="Seleccione fecha de emisión"
                show-adjacent-months
                :max="today"
                color=red>
            </v-date-picker>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
</template>

<style scoped>
    header{
        background-color: #f5f0bb;
        height: 270px;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .soge-logo{
        margin-top: auto;
    }
    .v-main{
        background-color: #f5f0bb;
    }

    .v-card{
      margin: 20px;
      padding: 20px;
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
                console.log('initFetch');
                const storedUserData = localStorage.getItem('userData');
                const userData = JSON.parse(storedUserData);
                this.saludo = 'Hola '+storedUserData.nombre + '! Ingresa los datos del egreso.';

                // Verificar si storedUserData no es nulo
                if (storedUserData) {
                    console.log('storedUserData');
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
            async login() {
                try {
                    const response = await axios.post(
                        'http://localhost:8080/api/v1/login',
                        {
                            rut: this.rut,
                            password: this.contrasena
                        }
                    );
                    console.log(response.data);
                    response.data.rut = this.rut;
                    console.log(response.data);


                    // Convertir el objeto de datos a una cadena JSON
                    const userData = JSON.stringify(response.data);

                    // Almacenar en localStorage
                    localStorage.setItem('userData', userData);
                    
                    console.log(response.data.tipoUsuario); // Debería imprimir el valor de tipoUsuario
                    console.log(typeof response.data.tipoUsuario); // Debería imprimir el tipo de tipoUsuario

                    if (response.data.tipoUsuario === 1) {
                        console.log('Navegando a EmitirEgreso');
                        this.$router.push({ name: 'EmitirEgreso' });
                    } else if (response.data.tipoUsuario === 0) {
                        console.log('Navegando a EmitirEgreso2');
                        this.$router.push({ name: 'EmitirEgreso' });
                    } else {
                        console.log('Navegando a login');
                        this.$router.push({ name: 'login' });
                    }

                } catch (error) {
                    console.error('A');
                }
            }
        },
        mounted() {
                this.initFetch();
                console.log('mounted');
            },
    };
</script>