# JUnit
Aplicação de testes e boas práticas com JUnit usando Eclipse.
<br />
<br />
<i>Última atualização: 11 de setembro, 2016 </i>

## Index:
1. [JUnit Overview](#overview)
2. [JUnit Basics](#basics)
3. [Advanced JUnit](#advanced)
4. [JUnit Integration](#integration)

<hr>

### 1. <a name="overview"></a>JUnit Overview

#### Features:
- Asserts;
- Test setup and teardown - Create data and destroy it after test;
- Exception testing;
- Test suites - Test groups;
- Parameterized testing;
- Assumptions;
- Rules;
- Theories;
- Integration with popular build systems - Ant e maven;

#### Workflow:

- Write test code (Geralmente um projeto separado) que irá atuar sobre um SUT(Subject Under Test), que normalmente é uma classe java;
- Ativa o JUnit runner;

#### Unit tests:

- Testando classe por classe

<hr>

### 2. <a name="basics"></a>JUnit Basics

#### Anotations básicas
- `@Test` - Identifica um método de teste;
- `@Before` and `@After` - Execução de um comportamento antes/depois de cada método de uma classe;
    - `setUp();`
    - `tearDown();`
- `@BeforeClass` and `@AfterClass` - Execução de um comportamente antes de QUALQUER teste de uma classe e/ou após TODOS os métodos de uma classe;
    - Estes métodos **DEVEM** ser static;
    - Usados normalmente quando dados precisam ser criados para os testes e removidos depois;
- `@Ignore` - Ignora um método de teste;
- `@Test(expected = Exception.class)` - Aguarda que uma exceção seja lançada;
- `@Test(timeout = 100)` - Especifica um intervalo de tempo em que o teste deve ser executado. Caso contrário irá ser lançado um timeOut;

É necessário adicionar um link entre o projeto de teste e o projeto a ser testado:

- Clique com o botão direito no projeto de teste > Build Path > Configure Build Path > Projects > add > Selecione o projeto a ser testado;

#### Assertions (Basic)

- `assertArrayEquals()`;
- `assertEquals()`;
- `assertTrue()` - For booleans;
- `assertFalse()`;
- `assertNull()`;
- `assertNotNull()`;
- `assertSame()` - Check if objects are the same;
- `assertNotSame()`;
- `fail()` - Fails the test;

<hr>

### 3. <a name="advanced"></a>Advanced JUnit
#### Test Suits
* Agrupamento de várias classes de teste para serem executadas em grupo;

##### Como criar uma suite de testes:

- Criar uma classe vazia no projeto de testes;
- Utilizar as anotations:
    - `@Runwith(Suite.class)` - Para identificar que esta classe será executada com a classe Suite;
    - `@Suite.SuiteClasses({ClasseDeTesteUm.class, ClasseDeTesteDois.class});`

#### Categorias

- São como suites de testes, porém especificadas por categoira;
- Pode-se aplicar o conceito de hierarquia às categorias;
##### - Como criar uma categoria:
    - Criar uma interface vazia que irá representar a categoria;
    - `@Category(CategoryClassName.class)` - Antes de cada teste para especificar que ele faz parte da categoria;
    - Criar uma classe suite para executar os testes de uma categoria.
        - Nesta classe devemos usar as anotations:
            - `@RunWith(Categories.class);`
            - `@IncludeCategory({CategoryInterfaceClassOne.class, CategoryInterfaceClassTwo.class});`
            - `@Suite.SuiteClasses({ClasseDeTesteUm.class, ClasseDeTesteDois.class});`
- O que acontece aqui, lendo de baixo para cima é:
    - Execução das classes definidas na @Suite.SuiteClasses, filtradas pelos testes que estão definidos com as categorias definidas no `@IncludeCategory`;
- Pode-ser utilizar a anotation @ExcludeCategory. Estas categorias devem ser uma interface;
    - Se um teste pertencer à duas categorias e uma delas estiver especificada no `@ExcludeCategory`, este teste não será executado;
- Uma boa situação para dividir os testes em categoria seria dividi-los em testes demorados e testes rápidos, para que não se perca muito tempo em cada execução;

#### Testes parametrizados

- Um grande problema encontrado pelos desenvolvedores é ter vários testes similares que variam apenas pelo input e output;
- O funcionamento deste tipo de teste acontece da seguinte maneira:
    - São criados valores de entrada e valores esperados de saída. E os testes irão executar métodos sobre cada um desses pares;
##### - Como criar testes parametrizados:
    - Construa uma classe **ParameterizedTests.class** (qualquer nome é válido);
    - Incluir anotation `@RunWith(Parameterized.class)`;
    - Construa um método **data()** retornando os pares de entradas e saídas;
    - Construa um método construtor com os parâmetros input e expected;
        - Faça um `assertEquals()` nos valores de **input** e **expected**;

#### Assertions Avançados

- Assertion de valores de uma maneira mais legível, por exemplo: ao invês de usar `assertEquals(expected, input)`, usaremos o assert
- Métodos disponíveis:
    - `AllOf<T>;`
    - `AnyOf<T>;`
    - `DescribedAs<T>;`
    - `Is<T>;`
    - `IsAnything<T>;`
    - `IsEqual<T>;`
    - `IsInstanceOf;`
    - `IsNot<T>;`
    - `IsNull<T>;`
    - `IsSame<T>;`
- Adicione na sua classe o import:
    - `import static org.hamcrest.CoreMatchers.*;`
    - `import static org.junit.matchers.JUnitMatchers.*;`

#### Teste de exceções avançado

- Este tipo de teste pode ser melhor visualizado no seguinte exemplo: algumas excessões estão em um bloco `try-catc`h e queremos verificar se a mensagem retornada no bloco catch é a esperada;
##### - Como criar:
    - Na classe de teste crie uma variável sob a anotation `@Rule`;
    - `public ExpectedException thrown = ExpectedException.none()`;
    - No método de teste:
        - `thrown.expect(InvalidAmountException.class);`
        - `thrown.expectMessage(“Esta mensagem é esperada”); // Case sensitive`

### Theories

-	De forma similar ao teste parametrizado, as **Theories** também recebem um parâmetro *input* e tem uma saída esperada. A diferença é apenas os *inputs* variam, e a saída esperada permanece a mesma sempre.

##### Como criar uma theory:

- `import static org.junit.Assert.*;`;
- Criar uma classe sob a anotation `@RunWith(Theories.class)`;
- Criar um array de inputs sob a anotation `@DataPoints`;
- Criar um método de teste sob a anotation `@Theory`;
- Exemplo de assert no método: `assertTrue(acc.getBalance() > 0);`;

**Ps:** Pode-se também fazer uso do método `Assume.assumeTrue(value > 0);` para ignorar os valores que não atendam esta condição.

<hr />

### 4. <a name="integration"></a>JUnit Integration

- O **JUnit Runner** integrado à IDE é bastante útil para executar os testes de maneira rápida, porém não é a melhor opção para automação de testes com JUnit. Felizmente o **JUnit Framework** traz meios alternativos melhores para este fim;
- A primeira opção é fazer uso da classe **Runner**, integrada ao framework, e executar o JUnit através dela, diretamente de um código java. Desta maneira podemos criar uma aplicação e de dentro dela executar a classe de testes, além de manter uma aplicação *standalone**;
- Outra alternativa é invocar o **JUnit Runner** através da linha de comando;
	- Para fazer isso devemos fazer da seguinte forma:
		- `java RunnerClass.class;`

##### Como criar uma classe de testes automatizados:

###### Através de uma classe

-  Crie uma classe com ponto de entrada principal `public static void main(String args[]) {}`;
-  Declare uma variável do tipo `JUnitCore` e a inicialize;
-  Para visualizar os outputs no console, adicione um listener na variável criada `junit.addListener(TextListener(System.out))`;
-  Defina a execução dos testes através da variável criada `junit.run(TargetTestsClass.class);`;

###### Através da linha de comando
-  Baixar as duas libs necessárias [junit e hamcrest](https://github.com/junit-team/junit4/wiki/Download-and-Install); Ps: No site de cada uma, baixar as versões **jar**;
-  Criar um pacote de testes e mover as classes de teste;
	- **ps:** se você estiver com erros de import, crie no projeto que contém as classes a serem importadas um pacote diferente do default e mova as classes para dentro dele;
-  Selecione a raiz dos dois projetos (Target e Testes), e os exporte como **JAR FILE**;
	- A razão para termos exportado os dois projetos em um mesmo JAR é que na linha de comando teremos que especificar qual o path para os arquivos JAR do JUnit e para as nossas classes; No meu caso foi:
	`java -cp junit-4.12.jar:hamcrest-core-1.3.jar:BankAccount.jar org.junit.runner.JUnitCore com.augustovictor.bankaccount.tests.HelloJUnitTest`
	
```
JUnit version 4.12
Executed before class
I.Before
After (Used when rolling back created data)
.Before
After (Used when rolling back created data)
.Before
After (Used when rolling back created data)
.Before
After (Used when rolling back created data)
Executed after class

Time: 0.017

OK (4 tests)
```
###### Executando testes com ANT
- Basta selecionar os dois projetos e exporta-los como **ANT**;
- Para executa-los basta clicar com o botão direito sobre o **build.xml** no projeto de testes > Run as > External tools configuration > Desmarcar o teste **build[default]** e selecionar os testes que deseja executar; **Obs:** No meu não consegui executar junto ao build;
- Para gerar relatórios:
	- Baixar o [ant](https://ant.apache.org/bindownload.cgi) (para qualquer diretório);
	- Entre na pasta e no diretório bin;
	- Execute o comando `ant -buildfile PATH_TO_YOUR_TEST_PROJECT/build.xml junitreport`;
	- Verifique o arquivo gerado em `PATH_TO_YOUR_TEST_PROJECT/junit/index.html`;

###### Executando testes com MAVEN

- Exclua os arquivos `build.xml` gerados pelo ANT;
- Clique com o botão direito sobre o projeto > Configure > Convert project to maven project > Finish;
- O primeiro passo é definir no projeto de testes que há uma dependência do projeto principal para que este seja executado; Para isso abra o arquivo `pom.xml` do projeto de testes;
	- Clique na aba **Dependências** e clique em add (não o 'Dependency Management');
	- As informações para preencher os campos estão no `pom.xml` do projeto principal;
- Adicione outra dependência, desta vez para o junit;
	-  Valores: junit, junit, LATEST; (Estamos usando LATEST apenas para fins de estudo, mas é interessante que se especifique uma versão;
- Agora devemos instalar nosso projeto principal no nosso repositório Maven, para que possamos usa-lo no projeto de testes;
	- Clique com o botão direito sobre o projeto > Run as > Install Maven;
- Normalmente os projetos têm a pasta de testes dentro do próprio diretório raiz, mas como temos projetos separados devemos configurar no `pom.xml` do projeto de testes o diretório para os arquivos teste;
	- Abaixo da linha `<sourceDirectory>src</sourceDirectory>` insira `<testSourceDirectory>src</testSourceDirectory>`;
- Os arquivos de testes serão executados apenas se o nome deles terminar com `Test.java`;
- Para testar, clique com o botão direito no projeto > Run as > Maven test;

##### Code coverage com EclEmma
- No ide abra Help > Eclipse Marktplace;
- Procurar por EclEmma e instalar;
- Após o Eclipse reiniciar você irá ver um novo ícone na toolbar (Um ícone verde de 'play' sobre uma barra de progresso verde e vermelha. Oitavo ícone, no meu caso);
- Clique sobre o projeto de testes, para que fique selecionado, e então no dropdown do menu descrito acima > Coverage as > JUnit Test;
- No console você irá ver um relatório de % da cobertura das classes, e se abrir as classes irá ver um background verde no que está coberto por testes ou vermelho, caso determinado bloco não esteja sendo coberto;
