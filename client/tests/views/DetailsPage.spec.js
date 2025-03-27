import { mount, flushPromises } from '@vue/test-utils'
import DetailsPage from '@/views/DetailsPage.vue'

vi.mock('@/services/RecordingService', () => ({
  default: {
    subscribeForSingleRecording: vi.fn(),
    getSingleRecording: vi.fn(),
  }
}))

vi.mock('@/services/SocketService', () => ({
  default: {
    addConnectionCallback: vi.fn(),
  }
}))

vi.mock('vue-router', () => ({
  useRoute: () => ({
    params: { publicId: 'abc123' }
  })
}))

// Stub gyerek komponens, hogy ne a RecordingElement valódi működését teszteljük
vi.mock('@/components/RecordingElement.vue', () => ({
  default: {
    name: 'RecordingElement',
    props: ['recording', 'showDetails'],
    template: '<div class="recording-element">{{ recording?.title }}</div>'
  }
}))

import recordingService from '@/services/RecordingService'
import websocketService from '@/services/SocketService'

describe('DetailsPage.vue', () => {
  beforeEach(() => {
    vi.clearAllMocks()
  })

  it('calls getSingleRecording on mount and on WebSocket connection', async () => {
    const getSingleMock = recordingService.getSingleRecording
    const addConnectionMock = websocketService.addConnectionCallback

    mount(DetailsPage)

    expect(getSingleMock).toHaveBeenCalledWith('abc123')
    expect(addConnectionMock).toHaveBeenCalled()
  })

  it('updates recording ref when data received from subscription', async () => {
    let callback = () => {}
    const subscribeMock = recordingService.subscribeForSingleRecording
    subscribeMock.mockImplementation((cb) => {
      callback = cb
    })

    const wrapper = mount(DetailsPage)
    const sampleData = { title: 'Test Recording' }

    callback(sampleData)
    await flushPromises()

    expect(wrapper.text()).toContain('Test Recording')
  })
})
