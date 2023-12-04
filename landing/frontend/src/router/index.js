import { createRouter, createWebHistory } from 'vue-router'
import LoginFormView from "../views/LoginFormView.vue";
import GastosComunesView from "../views/GastosComunesView.vue";
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
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
