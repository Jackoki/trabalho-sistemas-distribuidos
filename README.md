<h1 align="center">Busca de Primos em Intervalos Grandes</h1>

###

<p align="left">Com certeza você já ouviu falar da criptografia em alguma vez na sua vida, ou pelo sabe uma parte dela, já que na computação utilizamos um problema matemático para a nossa segurança, o uso de números primos.<br><br>A definição dos números primos são números que só são divisíveis por eles mesmos ou por 1, e isso é uma propriedade que não é possível prever com fórmulas matemáticas, apenas por testes brutos de divisão por divisão, e isso que os tornam interessantes, pois como na criptografia utilizamos a cifra a multiplicação de 2 números primos grandes, isso faz com que leve muito tempo para decifrar uma simples informação.</p>

###

<div align="center">
  <img height="200" src="https://raw.githubusercontent.com/Jackoki/trabalho-sistemas-distribuidos/refs/heads/main/images/data.gif"  />
</div>

###

<p align="left">Nesse trabalho realizamos então um conjunto de códigos (de forma Sequencial, Paralela e Distribuída) em Java, que realiza a busca de primos em 2 intervalos passados pelo usuário em uma variável long que vai de 0 até 9 quintilhões.<br>Como matematicamente não existe uma fórmula (ou pílula mágica) universal, é necessário realizar as verificações de divisões de 1 em 1, mas nesse caso, realizamos de forma mais efetiva, por meio de 2 análises principais:</p>

###

<div align="center">
  <img height="500" src="https://raw.githubusercontent.com/Jackoki/trabalho-sistemas-distribuidos/refs/heads/main/images/function.png"  />
</div>


<p align="left">A lógica é simples, caso um número seja par ou divisível por 3, o número não é primo, caso ele não entre na condição anterior fazemos então uma estrutura de repetição que analisará até a raiz quadrada do número passado, já que é o maior número possível a dividir sem sobra. <br>No primeiro loop analisamos se o número passado é divisível por 5 ou 5 + 2, que seria 7, caso não seja divisível, continuamos o loop, sendo o próximo valor 5 + 6, e 5 + 6 + 2, ou seja, 11, essa forma se mostra muito mais eficiente, já que ao invés de realizamos a comparação de 1 em 1 dos divisores, analisamos os números ímpares que não são divisíveis por 3, tendo então o seguinte uso computacional de ciclos:</p>

###

<div align="center">
  <img height="200" src="https://raw.githubusercontent.com/Jackoki/trabalho-sistemas-distribuidos/refs/heads/main/images/f%C3%B3rmula.png"  />
</div>

###

###

<div align="center">
  <img height="200" src="https://raw.githubusercontent.com/Jackoki/trabalho-sistemas-distribuidos/refs/heads/main/images/sequences.png"  />
</div>

###

###

<p align="left">Ao realizarmos a geração em intervalos pequenos como na casa de milhões ou bilhões, o código buscará quase que instantaneamente os números, contudo se formos pegar uma pequena diferença porém em números grandes, como 100 quatrilhões até 100 quatrilhões + 1000, ele demorará relativamente bastante tempo, já que seguindo a fórmula teremos o seguinte:</p>

###

<div align="center">
  <img height="200" src="https://raw.githubusercontent.com/Jackoki/trabalho-sistemas-distribuidos/refs/heads/main/images/anota%C3%A7%C3%B5es.png"  />
</div>

###

<p align="left">Evidentemente essa quantidade de tempo não se aplica na aplicação distribuída ou paralela, já que ocorre a divisão das tarefas com as Threads e tarefas, veja abaixo a comparação entre elas:</p>

###

<div align="center">
  <img height="200" src="https://i.imgflip.com/65efzo.gif"  />
</div>

###

<div align="center">
  <img height="200" src="https://i.imgflip.com/65efzo.gif"  />
</div>

###

<div align="center">
  <img height="200" src="https://i.imgflip.com/65efzo.gif"  />
</div>

###
