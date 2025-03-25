import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const socket = new SockJS('http://localhost:8080/ws');
const stompClient = new Client({
  webSocketFactory: () => socket,
  reconnectDelay: 5000,
  debug: str => console.log('[STOMP]', str),
});

/* INIT CONNECTION */
let isConnected = false;
let pendingSubscriptions = [];

stompClient.onConnect = () => {    

  pendingSubscriptions.forEach(fn => fn());
  pendingSubscriptions.length = 0;

  isConnected = true;
  console.log('STOMP connected');     
};

stompClient.onStompError = (frame) => {
  console.error('STOMP error', frame);
};

stompClient.activate();

const checkConnection = () => {
  if (!isConnected) {
    throw new Error('There is no live connection yet!');
  }
}

const subscribe = (destination, callback) => {  
  const doSubscribe = () => {
    stompClient.subscribe(destination, message => {
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
  checkConnection()
  stompClient.send(destination, headers, data);
}

export default {  
  subscribe,
  send
};