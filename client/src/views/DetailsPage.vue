<script setup>    
    import { ref, onMounted } from 'vue';
    import recordingService from '@/services/RecordingService';
    import websocketService from '@/services/SocketService';
    import RecordingElement from '@/components/RecordingElement.vue';
    import { useRoute } from 'vue-router'

    const route = useRoute()
    const publicId = route.params.publicId

    const recording = ref();

    onMounted(() => {
        refreshRecordingItem();
    })
    
    websocketService.addConnectionCallback(() => {        
        refreshRecordingItem();
    });

    recordingService.subscribeForSingleRecording((data) => {
        recording.value = data;    
    });

    const refreshRecordingItem = () => {
        recordingService.getSingleRecording(publicId);
    };
</script>

<template>
    <RecordingElement :recording="recording" :showDetails="true" ></RecordingElement>
</template>