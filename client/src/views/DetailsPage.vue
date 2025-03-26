<script setup>    
    import { ref } from 'vue';
    import recordingService from '@/services/RecordingService';
    import websocketService from '@/services/SocketService';
    import { useRoute } from 'vue-router'

    const route = useRoute()
    const publicId = route.params.publicId

    const recording = ref();
    
    websocketService.addConnectionCallback(() => {        
        refresh();
    });

    recordingService.subscribeForSingleRecording((data) => {
        recording.value = data;
        console.log('232')
        console.log(data)
    });

    const refresh = () => {
        recordingService.getSingleRecording(publicId);
    };
</script>

<template>

</template>