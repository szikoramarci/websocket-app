<script setup>
    import { Card, Tag, Button } from 'primevue';


    defineProps({
        recording: Object
    });

    const generateSeverity = (status) => {
        if (status === 'RECORDED') {
            return 'danger';
        } else if (status === 'SCHEDULED') {
            return 'success';
        } else {
            return 'warn';
        }
    };
</script>
<template>
    <Card>
        <template #title>{{ recording.title }}</template>
        <template #subtitle>{{ recording.duration  }} seconds</template>
        <template #content>
            <Tag :severity="generateSeverity(recording.status)" :value="recording.status"></Tag>
        </template>
        <template #footer>
            <Button label="Details" severity="secondary" outlined class="w-full" />
        </template>
    </Card>    
</template>