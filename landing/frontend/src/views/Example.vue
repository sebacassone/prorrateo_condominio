<template>
    <v-app>
        <v-app-bar
            flat
            class="pa-md-3"
            color="#17a499"
        >
        <appBar />
        </v-app-bar>
        <v-main class="d-flex align-center justify-center">
                <v-card
                    color="#17a499"
                    class="rounded-lg px-10 py-5 align-center text-center"
                    width="500"
                    :elevation="7"
                >
                    <div class="py-4 text-h4 text-white font-weight-bold">
                        Iniciar Sesión
                    </div>
                    <v-text-field 
                        class="pt-5 text-white"
                        v-model="email"
                        placeholder="example@usach.cl"
                        size="large"
                        label="Correo"
                        variant="outlined"
                    >
                    </v-text-field>
                    <v-text-field
                        class="text-white"
                        :type="visible ? 'text' : 'password'"
                        v-model="password"
                        label="Contraseña"
                        variant="outlined"
                    >
                    </v-text-field>
                    <v-card-actions class="justify-center">
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
                    </v-card-actions>
                </v-card>
        </v-main>
    </v-app>
</template>

<script>
    import appBar from '../appBar.vue'
    import axios from 'axios';

    export default {
        data: () => ({
            email: '',
            password: '',
            visible: false,
            loading: false,
            form: false,
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
                        'http://localhost:8081/api/login',
                        {
                            email: this.email,
                            password: this.password
                        }
                    );

                    const responseStatus = response.data.substring(0, 7);
                    const userId = response.data.substring(7);

                    localStorage.setItem('userId', userId)

                    if (responseStatus === 'loggedC') {
                        localStorage.setItem('userType', 'client')
                        this.$router.push({name: 'add-ticket'})
                    }else if (responseStatus === 'loggedL'){
                        localStorage.setItem('userType', 'leadership')
                        this.$router.push({name: 'assign-ticket'});
                    }else{
                        null
                    }

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