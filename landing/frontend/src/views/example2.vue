<template>
  <v-app class="rounded rounded-md">
      <v-app-bar
          flat
          class="pa-md-3"
          color="#17a499"
      >
          <appBar />
      </v-app-bar>
      <v-main
      class="d-flex align-center justify-center"
      >
          <v-card
          color="#17a499"
          :elevation="1"
          class="pa-10 text-white"
          style="border-radius: 10px"
          min-width="600"
          >
              <v-row class="py-1">
                  <v-col cols="12" align="center">
                      <h2 class="pb-3 text-h3 font-weight-bold text-white">Añadir Ticket</h2>
                  </v-col>
              </v-row>
              <v-row >
                  <v-col cols="4">
                      <v-autocomplete
                      label="Motivo" 
                      variant="outlined"
                      v-model="category"
                      :items="['Solicitud', 'Incidencia']"
                      :rules="[v => !!v || 'El motivo es obligatorio']"
                      >
                      </v-autocomplete>
                  </v-col>
                  <v-col align="center">
                      <v-select
                      v-if = "academicUnits.length > 0"
                      label="Unidad Académica"
                      variant="outlined"
                      v-model="idAcademicUnit"
                      :items="academicUnits"
                      item-title="name"
                      item-value="idAcademicUnit"
                      :rules="[v => !!v || 'La unidad académica es obligatoria']"
                      >
                      </v-select>
                      <v-autocomplete
                      v-else
                      label="Unidad Académica"
                      variant="outlined"
                      disabled
                      >
                      </v-autocomplete>
                  </v-col>
              </v-row>
              <v-row>
                  <v-col align="center">
                      <v-text-field
                      label="Descripción" 
                      variant="outlined"
                      v-model="description"
                      :rules="[v => !!v || 'La descripción es obligatoria']"
                      :class="{'invalid-input': descriptionInvalid}"
                      >
                      </v-text-field>
                  </v-col>
              </v-row>
              <v-row>
                  <v-col align="center">
                      <v-file-input
                      label="Adjuntar Archivo"
                      variant="outlined"
                      >
                      </v-file-input>
                  </v-col>
              </v-row>
              <v-row align-center class="pt-7 text-white">
                  <v-col align-center>
                      <v-btn
                      class="rounded-lg text-white"
                      block=""
                      size="large"
                      type="submit"
                      variant="outlined"
                      @click="sendData"
                      :disabled="!formIsValid"
                      >
                          <div class="font-weight-medium text-white">
                              Enviar
                          </div>
                      </v-btn>
                  </v-col>
              </v-row>
              <v-dialog v-model="showPopup" max-width="500" persistent>
                  <v-card class="pa-5 rounded-lg align-center" color="#eb7704">
                      <v-card-text class="text-center d-flex flex-column align-center">
                          <!-- Contenido del mensaje de éxito aquí -->
                          <v-icon color="white" size="200">mdi-check-circle</v-icon>
                          <p class="text-white font-weight-bold text-center my-3">
                              El ticket se ha enviado con éxito.
                          </p>
                      </v-card-text>
                      <v-card-actions>
                          <v-btn variant="outlined" class="font-weight-bold text-white" @click="closePopup()">
                              Cerrar
                          </v-btn>
                      </v-card-actions>
                  </v-card>
              </v-dialog>
          </v-card>
      </v-main>
  </v-app>
</template>

<script>
    import axios from 'axios'
    import appBar from '../appBar.vue'
    import {id} from "vuetify/locale";
    import {addBusinessDays} from "../dateUtils.js";

    export default {
        name: 'AddTicket',
        computed: {
            id() {
                return id
            },
            formIsValid() {
                return this.category && this.idAcademicUnit && this.description;
            },
        },
        data: () => ({
            category: null,
            academicUnits: [],
            description: null,
            idAcademicUnit: null,
            idLeadership: null,
            creation: null,
            successMessage: "",
            showPopup: false,
            descriptionInvalid: false,
        }),
        components: {
            appBar
        },
        methods: {
            initFetch() {
                // Método inicial, se ejecuta al cargar la página
                // Si hay un usuario logueado, se obtienen las unidades académicas
                // Sino, se redirige a la página de login
                localStorage.getItem('userId')?
                this.getUnits():
                this.$router.push('/login')

            },
            async sendData() {
                // Método para enviar los datos del ticket

                    try {
                        this.descriptionInvalid = false;

                        this.clientId = localStorage.getItem('userId')
                        console.log(this.idAcademicUnit)
                        await this.getIdLeadership()

                        this.creation = new Date()
                        const numBusinessDays = 20

                        const ticketData = {
                            description: this.description,
                            category: this.category,
                            state: "Sin asignar",
                            creation: this.creation,
                            responseLimit: addBusinessDays(this.creation, numBusinessDays),
                            fkIdClient: Number(this.clientId),
                            fkIdAcademicUnit: this.idAcademicUnit,
                            fkIdLeadership: this.idLeadership
                        };

                        const response = await axios.post(
                            `http://localhost:8081/tickets/add-ticket/${this.clientId}/`,
                            ticketData)

                        const idTicket = response.data.idTicket

                        await axios.put(`http://localhost:8081/units/leaderships/${this.idLeadership}/tickets/${idTicket}`)

                        this.showPopup = true

                        this.category = null
                        this.idAcademicUnit = null
                        this.description = ""

                        this.descriptionInvalid = false
                        this.description = ""

                    } catch (error) {
                        console.error(error)
                    }
            },
            async getUnits(){
                try {
                    const response = await axios.get(`http://localhost:8081/units/`)
                    this.academicUnits = response.data
                } catch (error){
                    console.error(error)
                    this.descriptionInvalid = true
                }
            },
            async getIdLeadership(){
                try {
                const response = await axios.get(`http://localhost:8081/units/${this.idAcademicUnit}/leadership`)
                this.idLeadership = response.data
                } catch(error){
                console.error(error)
                }
            },
            closePopup() {
                this.showPopup = false
                window.location.reload()
            }

        },
        mounted() {
            this.initFetch()
        }
    }
</script>