import { createRouter, createWebHistory } from 'vue-router'
import LoginFormView from "../views/LoginFormView.vue";

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
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
