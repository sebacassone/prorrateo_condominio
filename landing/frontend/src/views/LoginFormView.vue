<template>
    <v-app>
        <header>
            <img
                class="soge-logo-login"
                height="200"
                src="../components/images/soge_logo3.png"
            ><img/>
        </header>
        <v-main class="d-flex justify-center bg-color-black v-main-custom-margin"
                background-color= "#f5f0bb"
                >
            
            <div class="container-v-card">
                
            <v-card
                
                color="#1a1a1a"
                class="rounded-lg px-10 py-5 align-center text-center v-card-custom"
                width="450"
                :height="sizevcard"
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
                        <v-divider></v-divider>
                        <v-alert class="alert-custom"
                            margin-top="10px"
                            v-model="loginError"
                            closable
                            :text="textoAlerta"
                            type="error"
                            variant="outlined"
                            height="70px"
                            width="auto"
                            @click:close="setCardSize"
                        ></v-alert>
                </v-form>
            </v-card>
            
        </div>
        </v-main>
    
    </v-app>
</template>

<style scoped>
    header{
        position: relative;
        background-color:#1a1a1a;
        height: 270px;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        border-bottom: 3px solid #a4d719;
        
    }

    .container-v-card{
        margin-top: 100px;
        display: grid;
        grid-template-columns: 1fr; /* Una sola columna */
        row-gap: 0; /* Sin espacio entre filas */
    }

    .alert-custom{
        background-color: #1a1a1a;
        margin-top: 20px;
        color: #f6f6f6;
        border: 1px solid #f6f6f6;
        border-radius: 5px;
        font-size: 14px;
        font-weight: bold;
        text-align: center;
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
    .soge-logo-login{
        margin-top: auto;
        position: relative;
        z-index: 2;
    }
    .v-main{
        background-color: #fff9ef;
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
            loginError: false,
            textoAlerta: 'Usuario no registrado o contraseña incorrecta',
            sizevcard: 320,
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
                    //console.log(response.data);
                    response.data.rut = this.rut.replace('-','');
                    console.log(response.data);
                    //console.log(response.data.tipoUsuario);
                    //console.log(response.data.idEdificio);


                    // Convertir el objeto de datos a una cadena JSON
                    const userData = JSON.stringify(response.data);

                    // Almacenar en localStorage
                    localStorage.setItem('userData', userData);
                    
                    const storedUserDataJSON = localStorage.getItem('userData');
                    if (storedUserDataJSON) {
                      // Convertir la cadena JSON a un objeto
                        const storedUserData = JSON.parse(storedUserDataJSON);
                    
                      // Verificar si tipoUsuario existe
                        if (storedUserData.hasOwnProperty('tipoUsuario')) {
                            // Redirigir basado en el valor de tipoUsuario
                            if (storedUserData.tipoUsuario === 0) {
                                console.log('login(): navegando a EmitirEgreso');
                                this.$router.push('/EmitirEgreso');
                            } else {
                                console.log('login(): navegando a GastoComun');
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

                } catch (error) {
                    this.sizevcard = 400;
                    this.loginError = true;
                    console.error('Usuario no registrado o contraseña incorrecta');
                }
            },
            setCardSize(){
                this.sizevcard = 320;
            },
        },
        mounted() {
                this.initFetch();
                console.log('mounted');
            },
    };
</script>