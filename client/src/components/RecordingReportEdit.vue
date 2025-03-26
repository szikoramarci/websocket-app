<script setup>
import { useToast } from 'primevue/usetoast'
import { Form } from '@primevue/forms'
import { InputText, Button } from 'primevue'
import recordingService from '@/services/RecordingService'

const toast = useToast()

const { recording } = defineProps({
  recording: Object,
})

recordingService.subscribeForUpdateEvent((data) => {
  toast.add({
    severity: 'success',
    summary: 'Saved',
    detail: 'Recording updated successfully!',
  })
})

const onFormSubmit = (originalEvent) => {
  recordingService.updateRecording(recording.publicId, originalEvent.values)
}
</script>
<template>
  <Form
    v-slot="$form"
    :initialValues="recording"
    @submit="onFormSubmit"
    class="flex flex-col gap-4 w-full sm:w-56"
  >
    <div class="flex flex-col gap-1">
      <InputText name="sedation" type="text" placeholder="Sedation" fluid />
    </div>

    <div class="flex flex-col gap-1">
      <InputText name="activation" type="text" placeholder="Activation" fluid />
    </div>

    <div class="flex flex-col gap-1">
      <InputText name="medication" type="text" placeholder="Medication" fluid />
    </div>

    <Button type="submit" severity="secondary" label="Submit" />
  </Form>
</template>
