<script setup>
    import RecordingList from '@/components/RecordingList.vue';
    import { ref } from 'vue';
    import recordingService from '@/services/RecordingService';
    import websocketService from '@/services/SocketService';

    const recordings = ref([]);

    websocketService.addConnectionCallback(() => {
        refresh();
    });

    recordingService.subscribeForRecordings((data) => {
        recordings.value = data;
        console.log(data)
    });

    const refresh = () => {
        recordingService.refreshRecordingList();
    };
</script>

<template>
    <RecordingList :recordings="recordings" />
</template>