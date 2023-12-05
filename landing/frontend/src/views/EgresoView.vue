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
        <v-col cols="12" md="4">
          <!-- Contenido del primer bloque -->
          <v-card
            title="Emitir Egreso"
            subtitle="..."
            text="..."
            variant="tonal"
          >
          <v-form >
                            <v-text-field
                                v-model="rut"
                                :rules="rutRules"
                                :counter="10"
                                label="RUT"
                                variant="underlined"
                                required
                                placeholder="Sin puntos con guión"
                                hint="Ejemplo: 12345678-k"
                                
                                
                        
                            ></v-text-field>
                    
                            <v-text-field
                                v-model="contrasena"
                                :rules="contraRules"
                                label="Contraseña"
                                variant="underlined"
                                
                                :type="show1 ? 'text' : 'password'"
                                placeholder=""
                                hint="Ingrese su contraseña para acceder al sistema"
                                
                            ></v-text-field>


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
                                    Ingresar
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
    import {validarRUT} from '../validationUtils.js';

    export default {
        data: () => ({
            rut: '',
            contrasena: '',
            visible: false,
            show1: true,
            loading: false,
            form: false,
            rutRules: [
                value => {
                    if (value) return true

                    return 'Falta ingresar el RUT.'
                },
                value => {
                    if (validarRUT(value)) return true
                    
                    return 'RUT inválido.'
                },
            ],
            contraRules: [
                value => {
                    if (value) return true

                    return 'Falta ingresar la contraseña.'
                },
            ],
        }),

        methods: {
            initFetch() {
                // Recuperar la cadena JSON del localStorage
                console.log('initFetch');
                const storedUserData = localStorage.getItem('userData');

                // Verificar si storedUserData no es nulo
                if (storedUserData) {
                    console.log('storedUserData');
                    // Convertir la cadena JSON a un objeto JavaScript
                    const userData = JSON.parse(storedUserData);
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