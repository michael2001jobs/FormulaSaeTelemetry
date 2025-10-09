const WebSocket = require('ws');

// Conecta no seu servidor WebSocket Spring (porta 8081)
const socket = new WebSocket('ws://localhost:8081');

// Evento quando a conexão é aberta
socket.on('open', () => {
    console.log('Conectado ao WebSocket! Aguardando mensagens...');
});

// Evento para receber mensagens do servidor
socket.on('message', (data) => {
    console.log('Mensagem recebida:', data.toString());
});

// Evento de erro
socket.on('error', (err) => {
    console.error('Erro na conexão WebSocket:', err);
});

// Evento de fechamento
socket.on('close', () => {
    console.log('Conexão WebSocket fechada pelo servidor.');
});
