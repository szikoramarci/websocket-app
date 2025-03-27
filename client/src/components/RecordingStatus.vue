<script setup>
import { Tag } from 'primevue'

defineProps({
  uiConfig: Object,
})
</script>

<template>
  <Tag :severity="uiConfig.severity" :value="uiConfig.label"></Tag>
</template>
