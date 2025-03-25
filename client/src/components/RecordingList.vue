<script setup>
    import { ref, onMounted } from 'vue';
    import recordingService from '@/services/RecordingService';

    const recordings = ref([]);

    recordingService.subscribeForRecordings((data) => {
        recordings.value = data;
        console.log(data)            
    });      

    onMounted(() => {

        
             
        setTimeout(() => {
            refresh(); 
        }, 1000);
    });
    

    const refresh = () => {
        recordingService.refreshRecordingList();
    };
</script>
<template>

</template>