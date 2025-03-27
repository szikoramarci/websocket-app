<script setup>
import { Tag } from 'primevue'
import RecordingStatusEnum from '@/enums/RecordingStatusEnum'

defineProps({
  status: String,
})

const generateSeverity = (status) => {
  if (status === RecordingStatusEnum.RECORDED) {
    return 'danger'
  } else if (status === RecordingStatusEnum.SCHEDULED) {
    return 'success'
  } else {
    return 'warn'
  }
}
</script>

<template>
  <Tag :severity="generateSeverity(status)" :value="status"></Tag>
</template>
