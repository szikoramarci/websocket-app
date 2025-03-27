<script setup>
import { Card, Button } from 'primevue'
import RecordingStatus from './RecordingStatus.vue'
import RecordingReportEdit from './RecordingReportEdit.vue'
import RecordingReportInfo from './RecordingReportInfo.vue'
import router from '@/router/index'

defineProps({
  recording: Object,
  showDetails: Boolean,
})

const goToDetails = (publicId) => {
  router.push(`/recording/${publicId}`)
}

const backToTheList = () => {
  router.push(`/`)
}
</script>
<template>
  <Card v-if="recording">
    <template #title>{{ recording.title }}</template>
    <template #subtitle>{{ recording.duration }} seconds</template>
    <template #content>
      <RecordingStatus :uiConfig="recording.uiConfig"></RecordingStatus>
      <div v-if="showDetails">
        <RecordingReportEdit
          v-if="recording.uiConfig.editable"
          :recording="recording"
        ></RecordingReportEdit>
        <RecordingReportInfo
          v-else
          :sedation="recording.sedation"
          :activation="recording.activation"
          :medication="recording.medication"
        ></RecordingReportInfo>
      </div>
    </template>
    <template #footer v-if="!showDetails">
      <Button
        label="Details"
        severity="secondary"
        outlined
        @click="goToDetails(recording.publicId)"
      />
    </template>
    <template #footer v-else>
      <Button label="Back to the list" severity="secondary" outlined @click="backToTheList()" />
    </template>
  </Card>
</template>
