<script setup>
    import { Form } from '@primevue/forms';
    import { InputText, Message, Button } from 'primevue';

    defineProps({
        recording: Object
    });  

    const onFormSubmit = () => {
        console.log('11')
    }

</script>
<template>
    <Form v-slot="$form" @submit="onFormSubmit" class="flex flex-col gap-4 w-full sm:w-56">

        <div class="flex flex-col gap-1">
            <InputText name="sedation" type="text" placeholder="Sedation" fluid :value="recording.sedation" />            
        </div>

        <div class="flex flex-col gap-1">
            <InputText name="activation" type="text" placeholder="Activation" fluid :value="recording.activation" />
        </div>

        <div class="flex flex-col gap-1">
            <InputText name="medication" type="text" placeholder="Medication" fluid :value="recording.medication" />            
        </div>

        <Button type="submit" severity="secondary" label="Submit" />
    </Form>
</template>