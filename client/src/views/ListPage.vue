<script setup>
    import RecordingList from '@/components/RecordingList.vue';
    import { ref, onMounted } from 'vue';
    import recordingService from '@/services/RecordingService';
    import websocketService from '@/services/SocketService';

    const recordings = ref([]);

    onMounted(() => {
        refreshList();
    })

    websocketService.addConnectionCallback(() => {
        refreshList();
    });

    recordingService.subscribeForRecordings((data) => {
        recordings.value = data;
        console.log(data)
    });

    const refreshList = () => {
        recordingService.refreshRecordingList();
    };
</script>

<template>
    <RecordingList :recordings="recordings" />
</template>