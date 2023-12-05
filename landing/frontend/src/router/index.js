import { createRouter, createWebHistory } from 'vue-router'
import LoginFormView from "../views/LoginFormView.vue";
import GastosComunesView from "../views/GastosComunesView.vue";
import EgresoView from "../views/EgresoView.vue";

const routes = [
  {
      path: '/',
      redirect: '/login',
  },
  {
      path: '/login',
      name: 'login',
      component: LoginFormView,
  },
  {
    path: '/GastoComun',
    name: 'GastoComun',
    component: GastosComunesView,
  },
  {
    path: '/EmitirEgreso',
    name: 'EmitirEgreso',
    component: EgresoView,
},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
