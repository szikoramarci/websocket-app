import '@/assets/main.css';

import { createApp } from 'vue'
import PrimeVue from 'primevue/config';
import App from './App.vue'
import Aura from '@primeuix/themes/aura';
import router from '@/router/index'
import ToastService from 'primevue/toastservice';

const app = createApp(App)
app.use(ToastService);
app.use(router)
app.use(PrimeVue, {
    theme: {
        preset: Aura
    }
});
app.mount('#app')
