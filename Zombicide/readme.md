# 🧟 Zumbicídio

**Zumbicídio** é um jogo de tabuleiro digital inspirado no clássico *Zombicide*, onde o jogador deve sobreviver em uma cidade infestada de zumbis, coletando equipamentos e enfrentando diferentes tipos de inimigos. O jogo é implementado em **Java** com uma **interface gráfica** e segue princípios de **Programação Orientada a Objetos (POO)**.

## 🎮 Descrição do Jogo

O jogador assume o papel de um sobrevivente em uma cidade tomada por zumbis. Seu objetivo é **encontrar armas e suprimentos** para eliminar os inimigos e garantir sua sobrevivência. Durante a jornada, o jogador pode encontrar **itens escondidos em baús**, incluindo armas e suprimentos médicos, que podem ajudar ou trazer surpresas desagradáveis. 

Os zumbis se movem pelo mapa e tentam atacar o jogador, cada um com características específicas:

- **Zumbi Rastejante** 🧟‍♂️ – Pequeno e fraco, mas pode pegar o jogador de surpresa.
- **Zumbi Comum** 🧟 – Apenas um cadáver reanimado, resistente a ataques desarmados.
- **Zumbi Corredor** 🏃‍♂️ – Rápido e difícil de acertar com armas de fogo.
- **Zumbi Gigante** 🏋️ – Extremamente resistente, não pode ser derrotado sem armas.

## 🗺️ Mecânica do Jogo

- **Tabuleiro**: O jogo se passa em uma matriz **10x10**, onde os zumbis e os baús são distribuídos aleatoriamente.
- **Visibilidade**: O jogador só consegue ver **horizontal e verticalmente até o primeiro obstáculo** (zumbi, baú ou parede), exceto no modo *DEBUG*, onde tudo fica visível.
- **Combate**:
  - O jogador pode atacar com as **mãos** (chance de acerto crítico) ou **armas** (como revólver e taco de beisebol).
  - Após o ataque do jogador, o zumbi pode revidar.
  - Alguns zumbis exigem armas específicas para serem derrotados.

## 📜 Funcionalidades Implementadas

### ✅ Atividade 1 – Tela Inicial
- O jogo inicia com uma tela de **boas-vindas**, onde o jogador escolhe a dificuldade:
  - **Fácil**: 3 de percepção
  - **Médio**: 2 de percepção
  - **Difícil**: 1 de percepção
- Opções disponíveis:
  1. **Jogar** – Inicia o jogo com um mapa gerado aleatoriamente.
  2. **DEBUG** – Exibe todo o mapa desde o início.
  3. **Sair** – Fecha o jogo.

### ✅ Atividade 2 – Interface Gráfica e Jogabilidade
- Exibição do **tabuleiro 10x10** com o personagem, inimigos e baús.
- O jogador pode executar as seguintes ações:
  - **Mover-se** em até uma casa na horizontal ou vertical.
  - **Lutar** contra zumbis ao entrar na mesma célula.
  - **Curar-se** caso tenha encontrado suprimentos médicos.
  - **Sair** do jogo, com opções de reiniciar ou iniciar uma nova partida.

- Movimentação dos zumbis:
  - Após cada turno do jogador, os zumbis avançam em sua direção.
  - O **Zumbi Corredor** se move **duas posições** por rodada.
  - O **Zumbi Gigante** permanece fixo no local.

### ✅ Atividade 3 – Tela de Fim de Jogo
- O jogo exibe uma **tela de vitória** (se todos os zumbis forem derrotados) ou **tela de derrota** (se o jogador perder toda a vida).
- Opções finais:
  - **Reiniciar Jogo** – Mantém o mesmo mapa e dificuldade.
  - **Novo Jogo** – Retorna à tela inicial para escolher um novo mapa e dificuldade.

## 🚀 Como Jogar
1. Escolha a dificuldade e inicie o jogo.
2. Utilize os controles para **mover-se**, **coletar itens** e **lutar contra os zumbis**.
3. Sobreviva, elimine todos os inimigos e vença o jogo!

## 🛠️ Tecnologias Utilizadas
- **Java** – Linguagem principal do projeto.
- **Swing** – Para a construção da interface gráfica.
- **Programação Orientada a Objetos** – Aplicação de herança, polimorfismo e encapsulamento.

## 📌 Status do Projeto
✅ Implementação das funcionalidades básicas e regras de jogo.  
🔄 Melhorias contínuas na interface e jogabilidade.  

---
📍 *Projeto desenvolvido para a disciplina de Programação Orientada a Objetos - UFPEL (2024/2).*  
