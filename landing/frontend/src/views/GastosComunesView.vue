<template>
    <v-app>        
        <v-app-bar class="Head"  color="rgb(226, 235, 171)" :elevation="5" height="130">
            <h1 class="text-center" id="Titulo">Gastos Comunes</h1>

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

        <v-main>
            <v-container>
                <v-container id="Fecha">
                    <v-card id="Boxperiodo">
                        <p class="text-center" id="Periodo">Periodo:</p>
                    </v-card>

                    <v-autocomplete id="Mes"
                        max-width="35px"
                        clearable
                        label="Mes"
                        :items="['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto','Septiembre', 'Octubre', 'Noviembre', 'Diciembre']"
                        placeholder="Mes"
                    
                    ></v-autocomplete>

                    <v-autocomplete id="Año"
                        max-width="1px"
                        clearable
                        label="Año"
                        :items="[2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028]"
                        placeholder="Año"
                    
                    ></v-autocomplete>


                </v-container>

                <v-container id="Info"> 
                    <v-card id="BoxpeDeptos">
                        <p class="text-center" id="Periodo">Departamento N° :</p>
                    </v-card>

                    <v-autocomplete id="Deptos_list"
                        clearable
                        label="Numero"
                        :items="['1', '2', '3', '4', '5', '6']"
                        placeholder="#"
                    
                    ></v-autocomplete>
                </v-container>

                <v-container id="Mensaje">
                    <v-alert icon="$vuetify" text="" type="warning" color="#98d00e">
                        <p id="Mensaje_msg"> Hola Nombre de Logueado (*En negrilla*) ya esta disponible el Gasto Comun, pagar antes de Fecha(*en rojo y más grande*)</p>
                    </v-alert>
                </v-container>
            </v-container>
            
            <v-divider vertical></v-divider>

            <v-container>

                <v-container id="vencimiento"> 
                    <p id="Fecha_vencimiento_text" class="text-center"> Fecha de vencimiento</p>
                    <v-container id="fecha">
                        <p id="fecha_msg" class="text-center"> 10/10/2021 </p>
                    
                    </v-container>

                </v-container>

                
                <v-container id="monto_BOX"> 
                    <p id="Monto_a_pagar_texto" class="text-center">MONTO A PAGAR:</p>
                    <v-container id="monto">
                        <p id="monto_texto" class="text-center"> $ 100.000 </p>
                    </v-container>
                    
    
                </v-container>
                <v-container id="datos"> 
                     
                
                <v-card class="mx-auto my-8" max-width="500" elevation="9" color="#98d00e">
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
    color: #557153;
    margin-top: 10px;
    margin-left: 10px;
}

#Marco_logo{
    display: flex;
    align-items: right;
    margin-left: 10px;
    margin-top: 10px;
    width: 142px;
}
.soge-logo {
    background-color: rgb(226, 235, 171);

    height: 140px;
}

.v-main {
background-color: rgb(226, 235, 171);
display: flex;
grid-template-columns: 1fr 1fr;
padding-top: 140px;
padding-bottom: 70px;
height: 100%;
}

#Fecha{
    height: 33%;
    margin-bottom: 20px;
}

#Info{
    height: 45%;
  
}

#Mensaje{

    height: 22%;
}

#Boxperiodo{
    align-items: center;
    max-width: 20%;
}

#Periodo{
    font-size: 16px;
    font-weight: bold;
    color: #557153;
}

#Boxperiodo .text-center{
    background-color: rgb(245, 245, 245);
}

#BoxpeDeptos{
    align-items: center;
    max-width: 40%;
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
    padding-top: 30px;

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