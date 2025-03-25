import { Client } from '@stomp/stompjs';

const socket = new WebSocket('ws://localhost:8080/ws');
const socketClient = new Client({
  webSocketFactory: () => socket,
  reconnectDelay: 5000,
  debug: str => console.log('[SOCKET]', str),
});

/* INIT CONNECTION */
let isConnected = false;
let pendingSubscriptions = [];
let connectionCallbacks = [];

socketClient.onConnect = () => {    
  pendingSubscriptions.forEach(fn => fn());
  pendingSubscriptions.length = 0;

  isConnected = true;
  console.log('STOMP connected');     

  connectionCallbacks.forEach(fn => fn());
};

socketClient.onStompError = (frame) => {
  console.error('STOMP error', frame);
};

socketClient.activate();

const checkConnection = () => {
  if (!isConnected) {
    throw new Error('There is no live connection yet!');
  }
}

const subscribe = (destination, callback) => {    
  const doSubscribe = () => {    
    socketClient.subscribe(destination, message => {         
      const data = JSON.parse(message.body);
      callback(data);
    });
  };

  if (isConnected) {
    doSubscribe();
  } else {
    pendingSubscriptions.push(doSubscribe);
  }
};

const send = (destination, headers, data) => {
  checkConnection();
  socketClient.publish({ destination, headers, data})
}

const addConnectionCallback = (callback) => {
  connectionCallbacks.push(callback);
}

export default {  
  addConnectionCallback,
  subscribe,
  send
};