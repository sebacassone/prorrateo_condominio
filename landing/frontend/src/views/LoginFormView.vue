<template>
    <v-app>
        <header>
            <img
                class="soge-logo"
                height="250"
                background-color="primary"
                src="../components/images/soge_logo2.png"
            ><img/>
        </header>
        <v-main class="d-flex align-center justify-center bg-color-black"
                background-color= "#f5f0bb">
            <v-card
                
                color="#0d2c24"
                class="rounded-lg px-10 py-5 align-center text-center"
                width="450"
                :elevation="3"
                
            >
                <div class="py-4 text-h5 text-white font-weight-bold">
                    Iniciar Sesión
                </div>
                
                <v-form >
                            <v-text-field
                                v-model="rut"
                                :rules="rutRules"
                                :counter="10"
                                maxlength="10"
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
        </v-main>
    
    </v-app>
</template>

<style scoped>
    header{
        position: relative;
        background-color:rgb(226, 235, 171);
        height: 123px;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        border-bottom: 3px solid #A9AF7E;
        
    }

    .header::before {
        content: ''; /* Esto crea un pseudo-elemento */
        position: absolute;
        top: 50%; /* Posiciona la línea en el medio de .header */
        width: 100%; /* La línea se extiende a lo largo del ancho completo */
        height: 2px; /* Altura de la línea */
        background-color: #a2a2a2; /* Color de la línea */
        z-index: 1; /* Asegura que la línea esté debajo del logo */
    }
    .soge-logo{
        margin-top: auto;
        position: relative;
        z-index: 2;
    }
    .v-main{
        background-color: rgb(226, 235, 171);
    }
</style>

<script>
    import appBar from '../appBar.vue'
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
        components: {
            appBar
        },
        methods: {
            initFetch() {
                // Recuperar la cadena JSON del localStorage
                console.log('initFetch');
                const storedUserData = localStorage.getItem('userData');
                // Convertir la cadena JSON a un objeto JavaScript
                const userData = JSON.parse(storedUserData);

                // Verificar si storedUserData no es nulo
                if (storedUserData) {
                    console.log('storedUserData');
                    
                    if (userData.tipoUsuario === '0') {
                        this.$router.push({name: 'EmitirEgreso'});
                    }
                    if (userData.tipoUsuario === '1') {
                        this.$router.push({name: 'GastosComunes'});
                    }
                    else {
                        this.$router.push({name: 'login'});
                    }
                } 
            },
            async login() {
                try {
                    const response = await axios.post(
                        'http://localhost:8080/api/v1/login',
                        {
                            rut: this.rut.replace('-',''),
                            password: this.contrasena
                        }
                    );
                    console.log(response.data);
                    response.data.rut = this.rut.replace('-','');
                    console.log(response.data);
                    console.log(response.data.tipoUsuario);
                    console.log(response.data.idEdificio);


                    // Convertir el objeto de datos a una cadena JSON
                    const userData = JSON.stringify(response.data);

                    // Almacenar en localStorage
                    localStorage.setItem('userData', userData);
                    
                    console.log(response.data.tipoUsuario); // Debería imprimir el valor de tipoUsuario
                    console.log(typeof response.data.tipoUsuario); // Debería imprimir el tipo de tipoUsuario

                    if (response.data.tipoUsuario === 0) {
                        console.log('Navegando a EmitirEgreso2');
                        this.$router.push({ name: 'GastoComun' });
                    } else {
                        console.log('Navegando a EmitirEgreso');
                        this.$router.push({ name: 'EmitirEgreso' });
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