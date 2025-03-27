import { describe, it, expect, beforeEach } from 'vitest'
import { setActivePinia, createPinia } from 'pinia'
import { useRecordingStore } from '@/stores/RecordingStore'

describe('RecordingStore', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
  })

  it('setAll() beállítja a recordings értéket', () => {
    const store = useRecordingStore()
    const dummyData = [
      { publicId: 'abc123', title: 'Első' },
      { publicId: 'xyz456', title: 'Második' }
    ]

    store.setAll(dummyData)

    expect(store.getAll).toEqual(dummyData)
  })

  it('getByPublicId visszaadja a megfelelő recordingot', () => {
    const store = useRecordingStore()
    const dummyData = [
      { publicId: 'abc123', title: 'Első' },
      { publicId: 'xyz456', title: 'Második' }
    ]
    store.setAll(dummyData)

    const found = store.getByPublicId('xyz456')

    expect(found.value).toEqual({ publicId: 'xyz456', title: 'Második' })
  })

  it('getByPublicId reaktívan változik, ha frissítjük a listát', () => {
    const store = useRecordingStore()
    const result = store.getByPublicId('abc123')
    
    expect(result.value).toBeUndefined()

    store.setAll([{ publicId: 'abc123', title: 'Teszt cím' }])


    expect(result.value).toEqual({ publicId: 'abc123', title: 'Teszt cím' })
  })
})