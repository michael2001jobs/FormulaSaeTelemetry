const SockJS = require('sockjs-client');
const { Client } = require('@stomp/stompjs');

global.WebSocket = require('ws');

const socket = new SockJS('http://192.168.3.159:8080/ws-status'); // use seu IP local
const stompClient = new Client({
  webSocketFactory: () => socket,
  reconnectDelay: 5000,
  debug: (str) => console.log(str),
});

stompClient.onConnect = () => {
  console.log("Conectado ao WebSocket");

  stompClient.subscribe('/topic/brake', (message) => {
    const data = JSON.parse(message.body);
    console.log("Data received:");
    console.log(data);
  });
};

stompClient.onStompError = (frame) => {
  console.error("Erro STOMP:", frame.headers['message']);
  console.error("Detalhes:", frame.body);
};

stompClient.activate();
