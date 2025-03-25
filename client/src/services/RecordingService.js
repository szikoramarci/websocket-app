import socketService from "./SocketService";

const subscribeForRecordings = (callback) => {
    socketService.subscribe('/topic/recordings', callback)
}

const refreshRecordingList = () => {
    socketService.send('/app/recording/list', {}, {});
};

export default {
    subscribeForRecordings,
    refreshRecordingList
};