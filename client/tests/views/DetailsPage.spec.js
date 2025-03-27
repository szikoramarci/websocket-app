import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mount } from '@vue/test-utils'
import DetailsPage from '@/views/DetailsPage.vue'

vi.mock('@/components/RecordingElement.vue', () => ({
  default: {
    name: 'RecordingElement',
    template: '<div class="mock-recording-element">{{ recording.title }}</div>',
    props: ['recording', 'showDetails'],
  },
}))

vi.mock('vue-router', () => ({
  useRoute: () => ({
    params: {
      publicId: 'abc123',
    },
  }),
}))

vi.mock('@/stores/RecordingStore', () => {
  return {
    useRecordingStore: () => ({
      getByPublicId: (id) => ({ publicId: id, title: 'Teszt cím' }),
    }),
  }
})

describe('DetailsPage.vue', () => {
  it('megfelelő recordingot ad át a RecordingElement komponensnek', () => {
    const wrapper = mount(DetailsPage)

    const element = wrapper.findComponent({ name: 'RecordingElement' })
    expect(element.exists()).toBe(true)
    expect(element.props('recording')).toEqual({
      publicId: 'abc123',
      title: 'Teszt cím',
    })
    expect(element.props('showDetails')).toBe(true)
  })
})