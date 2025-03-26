<script setup>
    import { Panel } from 'primevue';

    defineProps({
        sedation: String,
        activation: String,
        medication: String,
    });
</script>
<template>
    <Panel header="Sedation">
        <p class="m-0">{{ sedation }}</p>
    </Panel>
    <Panel header="Activation">
        <p class="m-0">{{ activation }}</p>
    </Panel>
    <Panel header="Medication">
        <p class="m-0">{{ medication }}</p>
    </Panel>
</template>