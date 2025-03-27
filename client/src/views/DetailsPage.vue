<script setup>
import RecordingElement from '@/components/RecordingElement.vue'
import { useRecordingStore } from '@/stores/RecordingStore'
import { useRoute } from 'vue-router'

const route = useRoute()
const publicId = route.params.publicId
const recordingStore = useRecordingStore()
const recording = recordingStore.getByPublicId(publicId)
</script>

<template>
  <RecordingElement :recording="recording" :showDetails="true"></RecordingElement>
</template>
