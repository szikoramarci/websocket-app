import socketService from "./SocketService";

const subscribeForRecordings = (callback) => {
    socketService.subscribe('/topic/recordings', callback)
}

const subscribeForSingleRecording = (callback) => {
    socketService.subscribe('/topic/recording/single', callback)
}

const refreshRecordingList = () => {
    socketService.send('/app/recording/list', {}, {});
};

const getSingleRecording = (recordingPublicId) => {
    socketService.send('/app/recording/get/' + recordingPublicId, {}, {});
};

export default {
    subscribeForRecordings,
    subscribeForSingleRecording,
    refreshRecordingList,
    getSingleRecording
};