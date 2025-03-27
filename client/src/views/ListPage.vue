<script setup>
import RecordingList from '@/components/RecordingList.vue'
import { useRecordingStore } from '@/stores/RecordingStore'

const recordingStore = useRecordingStore()
</script>

<template>
  <RecordingList :recordings="recordingStore.getAll" />
</template>
