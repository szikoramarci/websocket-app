<script setup>
import Toast from 'primevue/toast'
import { useToast } from 'primevue/usetoast'
import websocketService from '@/services/SocketService'

const toast = useToast()

websocketService.subscribe('/queue/topic/errors', (errorMessage) => {
  toast.add({
    severity: 'error',
    summary: errorMessage.message,
    detail: errorMessage.details,
  })
})

</script>
<template>
  <div id="app">
    <Toast />
    <router-view />
  </div>
</template>
