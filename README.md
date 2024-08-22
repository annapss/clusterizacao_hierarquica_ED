# clusterizacao_hierarquica_ED

Trabalho Final da disciplina de Estrutura de Dados do curso de Sistemas de Informação da Universidade Federal do Estado do Rio de Janeiro (UNIRIO).

Alunos que realizaram esse trabalho
- Anna Paula Siqueira da Silva
- Thiago Lelles
- Daniel de Morais Martins

## Classes utilizadas para ambas soluções
- Nós criamos a Classe Ponto para representar não só um ponto como um cluster também.
- Criamos a classe GeraPontos para criar um vetor de árvores binárias (Classe Arvbin) em que cada árvore possui somente um nó
- A Classe Arvbin representa uma árvore binária

## Solução Naive
- A ideia consiste em representar o problema com um array de árvores.
- Calculamos as distâncias entre um cluster e outro e vemos qual é a menor distância
- Agrupamos o cluster que tem a menor distância e calculamos as distâncias novamente, verificando a menor distância.
- Isso é feito até que se tenha uma única árvore, ou seja, um único item válido no array

## Solução Fila de Prioridade
- Criar uma classe Distancia para representar a distancia entre dois clusters
- Na fila de prioridade, iremos inserir objetos de Distancia e iremos considerar que as menores distancias terão prioridade.
- Quando pegamos a menor distância, precisamos recalcular as distâncias do cluster que criamos para os clusters que estavam relacionados aos clusters que juntamos (Qual a forma eficiente de fazer isso ???)