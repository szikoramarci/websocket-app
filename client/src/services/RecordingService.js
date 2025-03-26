import socketService from './SocketService'

const subscribeForRecordings = (callback) => {
  socketService.subscribe('/queue/topic/recordings', callback)
}

const subscribeForSingleRecording = (callback) => {
  socketService.subscribe('/queue/topic/recording/single', callback)
}

const subscribeForUpdateEvent = (callback) => {
  socketService.subscribe('/queue/topic/recording/updated', callback)
}

const refreshRecordingList = () => {
  socketService.send('/app/recording/list', {}, {})
}

const getSingleRecording = (recordingPublicId) => {
  socketService.send('/app/recording/get/' + recordingPublicId, {}, {})
}

const updateRecording = (recordingPublicId, updateRequest) => {
  socketService.send('/app/recording/update/' + recordingPublicId, {}, updateRequest)
}

export default {
  subscribeForRecordings,
  subscribeForSingleRecording,
  subscribeForUpdateEvent,
  refreshRecordingList,
  getSingleRecording,
  updateRecording,
}
