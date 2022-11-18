import Vue from 'vue'
import VueRouter from 'vue-router'
import AppMain from '@/views/AppMain'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: AppMain
  },
  {
    path: '/search',
    name: 'search',
    component: () => import('@/views/AppSearch')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
