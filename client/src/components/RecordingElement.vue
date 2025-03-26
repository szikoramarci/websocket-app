<script setup>
    import { Card, Button } from 'primevue';
    import RecordingStatus from './RecordingStatus.vue';
    import RecordingReportEdit from './RecordingReportEdit.vue';
    import RecordingReportInfo from './RecordingReportInfo.vue';
    import router from '@/router/index'

    defineProps({
        recording: Object,
        showDetails: Boolean
    });    

    const goToDetails = (publicId) => {
        router.push(`/recording/${publicId}`)
    }

    const isEditableRecord = (recording) => {
        return recording.status == 'RECORDED';
    }
</script>
<template>
    <Card v-if="recording">
        <template #title>{{ recording.title }}</template>
        <template #subtitle>{{ recording.duration  }} seconds</template>
        <template #content>
            <RecordingStatus :status="recording.status"></RecordingStatus>
            <div v-if="showDetails">
                <RecordingReportEdit v-if="isEditableRecord(recording)" :recording="recording"></RecordingReportEdit>
                <RecordingReportInfo v-else :sedation="recording.sedation" :activation="recording.activation" :medication="recording.medication"></RecordingReportInfo>
            </div>            
        </template>        
        <template #footer v-if="!showDetails">
            <Button label="Details" severity="secondary" outlined @click="goToDetails(recording.publicId)" />
        </template>
    </Card>    
</template>