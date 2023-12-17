<template>
    <v-app>   
        <!-- Barra de navegación -->     
        <v-app-bar  color="#1a1a1a" :elevation="5" height="150">
            <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <h1 class="text-center" id="Titulo">Gastos Comunes</h1>
            
            <div id="Marco_logo">
                <img class="soge-logo" src="../components/images/soge_logo3.png" alt="Logo">
            </div>
        </v-app-bar>

        <!-- Barra lateral de navegación -->
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



        <v-main>
            <v-container>
                <v-alert
                v-model="alert_periodo"
                variant="tonal"
                closable
                close-label="Close Alert"
                color="green"
                title="Como Funciona:"
                >
                
                Selecciona el Mes como numero (1-12) y el Año (2021-2023), luego seleccióna el numero del departamento y presiona el boton "Solicitar" para ver el gasto común.
                </v-alert>
                <v-container id="Fecha">
                    <v-card id="Boxperiodo">
                        <p class="text-center" id="Periodo">
                            <v-btn  @click="alert_periodo = true" density="compact" size="small" icon="$info"></v-btn>
                            Periodo:</p>
                    </v-card>
                    
                    <v-autocomplete id="Mes"
                        v-model="mes"
                        max-width="35px"
                        clearable
                        label="Mes"
                        :items="[1,2,3,4,5,6,7,8,9,10,11,12]"
                        placeholder="Mes"
                    
                    ></v-autocomplete>

                    <v-autocomplete id="Año"
                        v-model="año"
                        max-width="1px"
                        clearable
                        label="Año"
                        :items="[2021, 2022, 2023]"
                        placeholder="Año"
                    
                    ></v-autocomplete>


                </v-container>

                <v-container id="Info"> 
                    <v-card id="BoxpeDeptos">
                        <p class="text-center" id="Periodo">Departamento N° :</p>
                    </v-card>

                    <v-autocomplete id="Deptos_list"
                        v-model ="depto"
                        clearable
                        label="Numero"
                        :items="lista_deptos"

                        placeholder="#"
                    ></v-autocomplete>

                    <v-card-actions class="justify-center">
                            <div class="btn-container">
                                <v-btn
                                    class="rounded-lg text-white px-5"
                                    color="#98d00e"
                                    elevation="10"
                                    :loading="loading"
                                    size="large"
                                    @click="solicitar_gasto_comun"
                                    variant="flat"
                                >
                                    <div class="text-h font-weight-medium text-white">
                                    Solicitar
                                    </div>
                                </v-btn>
                            </div>
                        </v-card-actions>


                </v-container>

                <v-container id="Mensaje">
                    <v-alert icon="$vuetify" text="" type="warning" color="#98d00e" elevation="1">
                        <p id="Mensaje_msg"> Hola "{{nombre_user}}" ya esta disponible el Gasto Comun, pagar antes de {{Fecha_vencimiento}}</p>
                    </v-alert>
                </v-container>
            </v-container>
            
            <v-divider vertical></v-divider>

            <v-container>

                <v-container id="vencimiento"> 
                    <p id="Fecha_vencimiento_text" class="text-center"> Fecha de vencimiento:</p>
                    <!-- Alerta en caso en que no exista el gasto comun -->
                    <v-alert class="alert-custom"
                            margin-top="10px"
                            v-model="alert_error"
                            type="error"
                            variant="outlined"
                            height="70px"
                            width="auto"
                           
                        >Gasto Comun no existente</v-alert>

                    <v-container id="fecha">
                        <p id="fecha_msg" class="text-center"> {{Fecha_vencimiento}} </p>
                    
                    </v-container>

                </v-container>

                
                <v-container id="monto_BOX"> 
                    <p id="Monto_a_pagar_texto" class="text-center">MONTO A PAGAR:</p>

                    <v-btn block rounded="xl" size="x-large" color="#98d00e" elevation="6">
                        <div class="text-h font-weight-medium text-white">
                                    $ {{ monto }}
                        </div>
                    </v-btn>
                    
    
                </v-container>
                <v-container id="datos"> 
                     
                
                <v-card class="mx-auto my-7" max-width="480" elevation="9" color="#98d00e">
                    <v-card-item>
                    <v-card-title id="Titulo_tarjeta"> Datos de Transferencia: </v-card-title>
                    <v-card-subtitle id="sub_tarjeta"> Solo transferencia, Pago por Web No disponible </v-card-subtitle>
                    </v-card-item>

                    <v-card-text>
                        <li style="list-style-type: circle;"> Rut: 21.165.824-k</li>
                        <li style="list-style-type: circle;"> Banco Estado</li>
                        <li style="list-style-type: circle;">
                            <a href="mailto:soeg@gmail.com">Correo:soeg@gmail.com</a>
                        </li>
                    </v-card-text>
                </v-card>

                </v-container>

            </v-container>
        </v-main>
        
        
    </v-app>
</template>



<style>
    

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
.soge-logo {
    
    padding-bottom: 10px;
    height: 140px;
}

.v-main {
background-color: #fff9ef;
display: flex;
grid-template-columns: 1fr 1fr;
padding-top: 140px;
padding-bottom: 70px;
height: 100%;}

#Fecha{
    height: 33%;
    margin-bottom: 20px;
}

#Info{
    height: 35%;
  
}

#Mensaje{

    height: 22%;
}

#Boxperiodo{
    align-items: center;
    max-width: 30%;
}

#Periodo{
    font-size: 17px;
    font-weight: bold;
    color: #557153;
}

#Boxperiodo .text-center{
    background-color: rgb(245, 245, 245);
}

#BoxpeDeptos{
    align-items: center;
    max-width: 50%;
}

#Mes{
    max-width: 30%;
    margin-left: 10px;
}

#vencimiento{
    height: 33%;
    width: 100%;
    border-radius:10% 0% 0% 10%  ; 
}

#Fecha_vencimiento_text{
    font-size: 20px;
    font-weight: bold;
    color: #557153;
    margin-top: 10px;
    margin-left: 10px;

}

#fecha_msg{
    font-size: 20px;
    font-weight: bold;
    color: #557153;
    margin-top: 10px;
    margin-left: 10px;

}

#monto_BOX{
    height: 33%;
    width: 100%;


}

#monto{
    width: 45%;
    height: 60%;
    background-color: #98d00e;
    border: solid 1px;
    border-radius: 50%/100%;
    

}

#monto_texto{
    font-size: 30px;
    font-weight: bold;
    color: white;
    margin-top: 0px;
    margin-left: 10px;
}
#Monto_a_pagar_texto{
    font-size: 20px;
    font-weight: bold;
    color: #557153;
    margin-top: 10px;
    margin-left: 10px;
}
#datos{
    height: 33%;
    width: 100%;
    padding-top: 15px;

}

a, li, #Titulo_tarjeta, #sub_tarjeta, #Mensaje_msg{
    color:#fff;
    text-decoration: none;
    transition: color 0.3s ease;
}

a:hover{
    color: cornflowerblue;
}

</style>


<script>
    import axios from 'axios';
    import appBar from '../appBar.vue';
    import {formatDate} from '../validationUtils.js';

    export default {
        data: () => ({
            alert_error: false,
            alert_periodo: true,
            drawer: false,
            loading: false,
            lista_deptos: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15'],
            depto: '',
            nombre_user: "Nombre User",
            monto: '',
            mes:'',
            año:'',
            Fecha_vencimiento: '',
        }),

        methods:{
    
            initFetch() {
            // Obtener el objeto de usuario de localStorage
                const storedUserDataJSON = localStorage.getItem('userData');
                if (storedUserDataJSON) {
                  // Convertir la cadena JSON a un objeto
                    const storedUserData = JSON.parse(storedUserDataJSON);
                    this.nombre_user = storedUserData.nombre;
                    this.lista_deptos = storedUserData.idPropiedades;
                
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

            
            logout() {
                console.log('logout()');
                // Eliminar el objeto de usuario de localStorage
                localStorage.removeItem('userData');
                // Redirigir a la página de inicio de sesión
                this.$router.push('/login');
            },
            async solicitar_gasto_comun(){
                try{
                    const response = await axios.get(
                        'http://localhost:8080/api/v1/prorrateo/1/'+this.depto + '/' + this.año + '-' + this.mes);
                    console.log(response.data);
                    this.alert_error = false;
                    this.monto = response.data.montoProrrateo;

                    this.Fecha_vencimiento = formatDate(response.data.fecha);
                    console.log(this.Fecha_vencimiento)

                }
                catch(error){
                    this.alert_error = true;
                    this.Fecha_vencimiento = '';
                    this.monto = '';
                    console.log(error);
                }

            }
        },
        mounted() {
            console.log('mounted_gasto');
            //this.checkUserTypeAndRedirect();
            this.initFetch();
        }
    }






</script>