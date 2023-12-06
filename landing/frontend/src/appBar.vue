<template>
    <v-app-bar-title>
        <v-row>
            <v-col
            v-if="logged()"
            class="d-flex align-center"
            >
                <v-btn variant="text" class="text-white" @click="this.logout">Cerrar Sesión</v-btn>
            </v-col>
            <v-col>
                <div class="mr-10 text-h2 text-white text-end font-weight-bold" color="#eb7804">
                    MaiTickets
                </div>
            </v-col>
        </v-row>
        
    </v-app-bar-title>
</template>

<script>
export default {
    name: 'appBar',
    data: () => ({
        }),
    methods: {
        logged() {
            return localStorage.getItem('userId')
        },
        logout() {
            localStorage.clear()
            this.$router.push('/login')
        }
    },
}

</script>
<style>

</style>