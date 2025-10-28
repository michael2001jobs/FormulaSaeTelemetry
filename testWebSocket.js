const WebSocket = require('ws');

const socket = new WebSocket('ws://localhost:8081');

socket.on('open', () => {
    console.log('Conectado ao WebSocket! Aguardando mensagens...');
});

socket.on('message', (data) => {
    console.log('Mensagem recebida:', data.toString());
});

socket.on('error', (err) => {
    console.error('Erro na conexão WebSocket:', err);
});

socket.on('close', () => {
    console.log('Conexão WebSocket fechada pelo servidor.');
});
