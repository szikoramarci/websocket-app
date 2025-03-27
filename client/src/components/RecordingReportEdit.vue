<script setup>
import { Form } from '@primevue/forms'
import { InputText, Button, Panel } from 'primevue'
import recordingService from '@/services/RecordingService'

const { recording } = defineProps({
  recording: Object,
})

const onFormSubmit = (originalEvent) => {
  recordingService.updateRecording(recording.publicId, originalEvent.values)
}
</script>
<template>
  <Panel header="Update the recording">
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
  </Panel>
</template>
