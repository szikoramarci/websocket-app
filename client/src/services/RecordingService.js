import socketService from './SocketService'

const subscribeForRecordings = (callback) => {
  socketService.subscribe('/queue/topic/recordings', callback)
}

const subscribeForUpdateEvent = (callback) => {
  socketService.subscribe('/queue/topic/recording/updated', callback)
}

const refreshRecordingList = () => {
  socketService.send('/app/recording/list', {}, {})
}

const updateRecording = (recordingPublicId, updateRequest) => {
  socketService.send('/app/recording/update/' + recordingPublicId, {}, updateRequest)
}

export default {
  subscribeForRecordings,
  subscribeForUpdateEvent,
  refreshRecordingList,
  updateRecording,
}
