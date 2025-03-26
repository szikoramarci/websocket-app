import { createRouter, createWebHistory } from 'vue-router'
import ListPage from '@/views/ListPage.vue'
import DetailsPage from '@/views/DetailsPage.vue'

const routes = [
    {
        name: 'List',
        path: '/',
        component: ListPage
    },
    { 
        name: 'Details',
        path: '/recording/:publicId', 
        component: DetailsPage 
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router