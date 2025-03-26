import { mount } from '@vue/test-utils'
import RecordingReportInfo from '@/components/RecordingReportInfo.vue'

describe('RecordingReportInfo.vue', () => {
  it('renders sedation, activation, and medication props correctly', () => {
    const wrapper = mount(RecordingReportInfo, {
      props: {
        sedation: 'SED',
        activation: 'ACT',
        medication: 'MED'
      }
    })

    const texts = wrapper.findAll('p')
    expect(texts[0].text()).toBe('SED')
    expect(texts[1].text()).toBe('ACT')
    expect(texts[2].text()).toBe('MED')
  })

  it('renders all three panels with correct headers', () => {
    const wrapper = mount(RecordingReportInfo, {
      props: {
        sedation: 'S',
        activation: 'A',
        medication: 'M'
      }
    })

    const panelHeaders = wrapper.findAll('.p-panel-header')
    expect(panelHeaders).toHaveLength(3)
    expect(panelHeaders[0].text()).toContain('Sedation')
    expect(panelHeaders[1].text()).toContain('Activation')
    expect(panelHeaders[2].text()).toContain('Medication')
  })
})