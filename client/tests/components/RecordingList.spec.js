import { mount } from '@vue/test-utils'
import RecordingList from '@/components/RecordingList.vue'

vi.mock('@/components/RecordingElement.vue', () => ({
  default: {
    name: 'RecordingElement',
    template: '<div class="mock-recording-element" />'
  }
}))

describe('RecordingList.vue', () => {
  const sampleRecordings = [
    { id: 1, title: 'Recording 1' },
    { id: 2, title: 'Recording 2' },
  ]

  it('renders a PrimeVue Panel with correct header', () => {
    const wrapper = mount(RecordingList, {
      props: { recordings: sampleRecordings }
    })
    const header = wrapper.find('.p-panel-header')
    expect(header.exists()).toBe(true)
    expect(header.text()).toContain('Recordings')
  })

  it('renders one RecordingElement per recording', () => {
    const wrapper = mount(RecordingList, {
      props: { recordings: sampleRecordings }
    })
    const elements = wrapper.findAll('.mock-recording-element')
    expect(elements).toHaveLength(2)
  })
})