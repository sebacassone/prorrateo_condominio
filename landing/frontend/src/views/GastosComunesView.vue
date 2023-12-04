<template>
    <v-app>
       

        
        
        <v-app-bar class="Head"  color="rgb(226, 235, 171)" :elevation="16" height="130">
            <img class="soge-logo" src="../components/images/soge_logo2.png" alt="Logo">
        </v-app-bar>

        <v-navigation-drawer color="rgb(226, 235, 171)" rail permanent></v-navigation-drawer>

        <v-main>
            <v-container>
                <v-row>
                    <v-col cols="12" md="6">
                        <v-card>
                            <v-card-title>
                                <h1> Gastos Comunes </h1>
                            </v-card-title>
                            <v-card-text>
                                <p> Aquí se podrá visualizar los gastos comunes de cada mes, además de poder pagarlos. </p>
                            </v-card-text>
                        </v-card>
                    </v-col>

                    <v-col cols="32" md="6">
                        <v-container>
                            <v-combobox
                                
                                clearable
                                label="Combobox"
                                :items="['Depto #1', 'Depto #2', 'Depto #3', 'Depto #4', 'Depto #5', 'Depto #6']"
                                variant="outlined"
                                append-icon="mdi-map-marker"
                            ></v-combobox>
                        </v-container>
                    </v-col>
                    <v-col cols="12" md="6">
                        <v-card>
                            <v-card-title>
                                <h1> Gastos Comunes </h1>
                            </v-card-title>
                            <v-card-text>
                                <p> Aquí se podrá visualizar los gastos comunes de cada mes, además de poder pagarlos. </p>
                            </v-card-text>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>

            <v-container id="Derecha"> Hola </v-container>
        </v-main>
        
        
    </v-app>
</template>

<style scoped>
    


.soge-logo {
    background-color: rgb(226, 235, 171);

    height: 100%;
}

.v-main {
background-color: rgb(226, 235, 171);
display: grid;
grid-template-columns: 1fr 1fr;
}

#Derecha{
    

}


</style>