import RecordingService from '@/services/RecordingService'


vi.mock('@/services/SocketService', () => ({
  default: {
    subscribe: vi.fn(),
    send: vi.fn(),
  }
}))

import socketService from '@/services/SocketService'

describe('RecordingService', () => {
  afterEach(() => {
    vi.clearAllMocks()
  })

  it('should subscribe to /queue/topic/recordings', () => {
    const cb = vi.fn()
    RecordingService.subscribeForRecordings(cb)

    expect(socketService.subscribe).toHaveBeenCalledWith('/queue/topic/recordings', cb)
  })

  it('should call send with the correct path for refreshRecordingList', () => {
    RecordingService.refreshRecordingList()

    expect(socketService.send).toHaveBeenCalledWith('/app/recording/list', {}, {})
  })

  it('should send update request to correct path', () => {
    const update = { sedation: 1, activation: 2 }
    RecordingService.updateRecording('abc123', update)

    expect(socketService.send).toHaveBeenCalledWith('/app/recording/update/abc123', {}, update)
  })

})