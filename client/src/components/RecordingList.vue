<script setup>
    import { ref, onMounted } from 'vue';
    import recordingService from '@/services/RecordingService';
    import websocketService from '@/services/SocketService';
    import Panel from "primevue/panel"
    import RecordingElement from './RecordingElement.vue';

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
    <Panel header="Recordings">
        <p class="m-0">
            <RecordingElement v-for="recording in recordings" :key="recording.id" :recording="recording" />
        </p>
    </Panel>
</template>