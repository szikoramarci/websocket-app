import { useToast } from 'primevue/usetoast'
import recordingService from '@/services/RecordingService'
import websocketService from '@/services/SocketService'
import { useRecordingStore } from '@/stores/RecordingStore'

let initialized = false

export const initSubsriptions = () => {
  if (initialized) return

  const toast = useToast()
  const recordingStore = useRecordingStore()

  recordingService.subscribeForRecordings((data) => {
    recordingStore.setAll(data)
  })

  recordingService.subscribeForUpdateEvent((data) => {
    recordingService.refreshRecordingList()
    toast.add({
      severity: 'success',
      summary: 'Saved',
      detail: 'Recording updated successfully!',
    })
  })

  websocketService.subscribe('/queue/topic/errors', (errorMessage) => {
    toast.add({
      severity: 'error',
      summary: errorMessage.message,
      detail: errorMessage.details,
    })
  })

  websocketService.addConnectionCallback(() => {
    recordingService.refreshRecordingList()
  })

  initialized = true
}

export default {
  initSubsriptions,
}
