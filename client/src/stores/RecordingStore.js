import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useRecordingStore = defineStore('recording', () => {
  const recordings = ref([])

  const setAll = (newRecordings) => {
    recordings.value = newRecordings
  }

  const getAll = computed(() => recordings.value)

  const getByPublicId = (publicId) =>
    computed(() => recordings.value.find((r) => r.publicId === publicId))

  return {
    setAll,
    getAll,
    getByPublicId,
  }
})
