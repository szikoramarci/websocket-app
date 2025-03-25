<script setup>
    import { ref, onMounted } from 'vue';
    import recordingService from '@/services/RecordingService';

    const recordings = ref([]);

    onMounted(() => {

        recordingService.subscribeForRecordings((data) => {
            recordings.value = data;
            console.log(data)
            refresh();
        });                
    });

    const refresh = () => {
        recordingService.refreshRecordingList();
    };
</script>
<template>

</template>