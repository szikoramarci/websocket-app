import '@/assets/main.css';

import { createApp } from 'vue'
import PrimeVue from 'primevue/config';
import App from './App.vue'
import Aura from '@primeuix/themes/aura';
import router from '@/router/index'

const app = createApp(App)
app.use(router)
app.use(PrimeVue, {
    theme: {
        preset: Aura
    }
});
app.mount('#app')
