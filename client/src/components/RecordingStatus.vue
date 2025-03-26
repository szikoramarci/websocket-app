<script setup>
    import { Tag } from 'primevue';

    defineProps({
        status: String
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
    <Tag :severity="generateSeverity(status)" :value="status"></Tag>
</template>