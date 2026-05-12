# Projeto_Poo

# MineSweeper

Projeto da cadeira de Programação Orientada a Objetos (POO), do 1º ano da LEI na EST Setúbal. Versão em Java do MineSweeper, com interface JavaFX.


## Como pôr a correr

É necessário o IntelliJ IDEA. O Maven já vem incluído no IntelliJ, não tens de o instalar à parte.

1. Clona o repositório:
   ```bash
   git clone https://github.com/guimmestre-maker/MineSweeper.git
   ```
2. Abre a pasta no IntelliJ (`File → Open`)
3. Quando o Maven acabar de fazer download das dependências, abre `src/main/java/org/example/MineSweeper.java`
4. Carrega no triângulo verde ao lado do `main`

Pela linha de comandos basta `mvn clean javafx:run` dentro da pasta do projeto.

Outra alternativa é pelo download do ficheiro zip:

1. Faz download do repositório, botão verde que diz `Code` e `Download Zip`
2. No Intellij vai até `File → Open` e abre a pasta 
3. Carrega no triângulo verde ao lado do `main`



## Como se joga

Clicar com o botão esquerdo do rato revela uma célula, clicar como o botão direito do rato põe uma bandeira. As células com bombas à volta mostram quantas estão num raio 3x3. As células sem bombas vizinhas abrem em cascata.

Ganhas quando as únicas células por revelar são as que têm bombas.
Perdes se clicares numa: o tabuleiro fica todo visível e o jogo bloqueia.

## Como está organizado o código

```
src/main/java/org/example/
├── MineSweeper.java        # ponto de entrada, controlador
├── Tabuleiro.java          # grelha, gere células e vizinhanças
├── Celula.java             # classe abstrata
├── CelulaBomba.java        # Célula com bomba
├── CelulaVazia.java        # Célula que não tem bombas no raio 3x3
├── CelulaNr.java           # célula com número de bombas adjacentes
├── Dificuldade.java        # enum
└── SituacaoJogo.java       # enum
```

`Celula` é abstrata, e as três subclasses reagem ao clique de forma diferente (polimorfismo). 

O `Tabuleiro` é dono das células: sem `Tabuleiro` não existem células (composição). 

O `MineSweeper` tem um único `Tabuleiro`.