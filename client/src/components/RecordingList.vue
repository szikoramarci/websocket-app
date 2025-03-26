<script setup>    
    import Panel from "primevue/panel"
    import RecordingElement from './RecordingElement.vue';   
    
    defineProps({
        recordings: Array
    });
</script>
<template>
    <Panel header="Recordings">
        <p class="m-0">
            <RecordingElement v-for="recording in recordings" :key="recording.id" :recording="recording"/>
        </p>
    </Panel>
</template>