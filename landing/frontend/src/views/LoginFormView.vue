<template>
    <v-app>
        <header>
            <img
                class="soge-logo"
                height="250"
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
                                :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                                :type="show1 ? 'text' : 'password'"
                                placeholder=""
                                hint="Ingrese su contraseña para acceder al sistema"
                                class="input-group--focused"
                                @click="show2 = !show2"
                                
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

<style>
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
</style>

<script>
    import appBar from '../appBar.vue'
    import axios from 'axios';
    import {validarRUT} from '../validationUtils.js';

    export default {
        data: () => ({
            rut: '',
            password: '',
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
                if (localStorage.getItem('userId')) {
                    if (localStorage.getItem('userType') === 'leadership') {
                        this.$router.push({name: 'assign-ticket'});
                    } else {
                    this.$router.push({name: 'add-ticket'});
                }
            }   
            },
            async login() {
                try {
                    const response = await axios.post(
                        'http://localhost:8080/login',
                        {
                            rut: this.rut,
                            contraseña: this.contrasena
                        }
                    );
                    console.log(response.data);
                    //const responseStatus = response.data.substring(0, 7);
                    //const userId = response.data.substring(7);
//
                    //localStorage.setItem('userId', userId)
//
                    //if (responseStatus === 'loggedC') {
                    //    localStorage.setItem('userType', 'client')
                    //    this.$router.push({name: 'add-ticket'})
                    //}else if (responseStatus === 'loggedL'){
                    //    localStorage.setItem('userType', 'leadership')
                    //    this.$router.push({name: 'assign-ticket'});
                    //}else{
                    //    null
                    //}

                } catch (error) {
                    console.error('A');
                }
            }
        },
        mounted() {
                this.initFetch();
            },
    };
</script>