import { mount } from '@vue/test-utils'
import RecordingStatus from '@/components/RecordingStatus.vue'
import RecordingStatusEnum from '@/enums/RecordingStatusEnum'

describe('RecordingStatus.vue', () => {
  it('renders danger tag when status is RECORDED', () => {
    const wrapper = mount(RecordingStatus, {
      props: { status: RecordingStatusEnum.RECORDED }
    })
    const tag = wrapper.get('.p-tag')
    expect(tag.text()).toBe(RecordingStatusEnum.RECORDED)
    expect(tag.classes()).toContain('p-tag-danger')
  })

  it('renders success tag when status is SCHEDULED', () => {
    const wrapper = mount(RecordingStatus, {
      props: { status: RecordingStatusEnum.SCHEDULED }
    })
    const tag = wrapper.get('.p-tag')
    expect(tag.text()).toBe(RecordingStatusEnum.SCHEDULED)
    expect(tag.classes()).toContain('p-tag-success')
  })

  it('renders warn tag when status is something else', () => {
    const wrapper = mount(RecordingStatus, {
      props: { status: RecordingStatusEnum.REPORTED }
    })
    const tag = wrapper.get('.p-tag')
    expect(tag.text()).toBe(RecordingStatusEnum.REPORTED)
    expect(tag.classes()).toContain('p-tag-warn')
  })
})
