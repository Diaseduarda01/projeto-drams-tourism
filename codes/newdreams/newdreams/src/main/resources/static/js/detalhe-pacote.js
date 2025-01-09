// detalhe-pacote.js

const detalhesPacote = document.getElementById('detalhes-pacote');

fetch('/api/pacotes')
    .then(response => response.json())
    .then(pacote => {
        document.getElementById('nome-pacote').textContent = pacote.nome_pacote;
        document.getElementById('image-card1').src = 'data:image/png;base64,' + pacote.image_card1;
        document.getElementById('image-card2').src = 'data:image/png;base64,' + pacote.image_card2;
        document.getElementById('image-card3').src = 'data:image/png;base64,' + pacote.image_card3;
        document.getElementById('image-card4').src = 'data:image/png;base64,' + pacote.image_card4;
        document.getElementById('descricao').textContent = pacote.descricao;
        document.getElementById('preco').textContent = 'Preço: ' + pacote.preco;
        document.getElementById('duracao').textContent = 'Duração: ' + pacote.duracao;
        document.getElementById('titulo-card-centro').textContent = pacote.titulo_card_centro;
        document.getElementById('descricao-card-centro').textContent = pacote.descricao_card_centro;
        document.getElementById('titulo-card-direito').textContent = pacote.titulo_card_direito;
        document.getElementById('descricao-card-direito').textContent = pacote.descricao_card_direito;
        document.getElementById('titulo-card-esquerdo').textContent = pacote.titulo_card_esquerdo;
        document.getElementById('descricao-card-esquerdo').textContent = pacote.descricao_card_esquerdo;
    })
    .catch(error => {
        console.error('Erro ao buscar detalhes do pacote de viagem:', error);
    });
