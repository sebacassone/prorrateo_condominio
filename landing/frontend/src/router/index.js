import { createRouter, createWebHistory } from 'vue-router'
import LoginFormView from "../views/LoginFormView.vue";
import EgresoView from "../views/EgresoView.vue";

const routes = [
  {
      path: '/',
      redirect: '/EmitirEgreso',
  },
  {
      path: '/login',
      name: 'login',
      component: LoginFormView,
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
