<script setup>
import Toast from 'primevue/toast'
import subscriptionService from '@/services/SubscriptionService'

subscriptionService.initSubsriptions()
</script>
<template>
  <div id="app">
    <Toast />
    <router-view />
  </div>
</template>
