import { Client } from '@stomp/stompjs'

const socket = new WebSocket('ws://localhost:8080/ws')
const socketClient = new Client({
  webSocketFactory: () => socket,
  reconnectDelay: 5000,
  debug: (str) => console.log('[SOCKET]', str),
})

let isConnected = false
let pendingSubscriptions = []
let connectionCallbacks = []

socketClient.onConnect = () => {
  pendingSubscriptions.forEach((fn) => fn())
  pendingSubscriptions.length = 0

  isConnected = true
  console.log('STOMP connected')

  connectionCallbacks.forEach((fn) => fn())
}

socketClient.onStompError = (frame) => {
  console.error('STOMP error', frame)
}

socketClient.activate()

const subscribe = (destination, callback) => {
  const doSubscribe = () => {
    socketClient.subscribe(destination, (message) => {
      const data = JSON.parse(message.body)
      callback(data)
    })
  }

  if (isConnected) {
    doSubscribe()
  } else {
    pendingSubscriptions.push(doSubscribe)
  }
}

const send = (destination, headers, data) => {
  if (!isConnected) return

  const body = JSON.stringify(data)
  socketClient.publish({ destination, headers, body })
}

const addConnectionCallback = (callback) => {
  connectionCallbacks.push(callback)
}

export default {
  addConnectionCallback,
  subscribe,
  send,
}
