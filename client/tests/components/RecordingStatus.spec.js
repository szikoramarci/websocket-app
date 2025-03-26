import { mount } from '@vue/test-utils'
import RecordingStatus from '@/components/RecordingStatus.vue'

describe('RecordingStatus.vue', () => {
  it('renders danger tag when status is RECORDED', () => {
    const wrapper = mount(RecordingStatus, {
      props: { status: 'RECORDED' }
    })
    const tag = wrapper.get('.p-tag')
    expect(tag.text()).toBe('RECORDED')
    expect(tag.classes()).toContain('p-tag-danger')
  })

  it('renders success tag when status is SCHEDULED', () => {
    const wrapper = mount(RecordingStatus, {
      props: { status: 'SCHEDULED' }
    })
    const tag = wrapper.get('.p-tag')
    expect(tag.classes()).toContain('p-tag-success')
  })

  it('renders warn tag when status is something else', () => {
    const wrapper = mount(RecordingStatus, {
      props: { status: 'PENDING' }
    })
    const tag = wrapper.get('.p-tag')
    expect(tag.classes()).toContain('p-tag-warn')
  })
})
