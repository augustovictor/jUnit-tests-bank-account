# JUnit
<i>Última atualização: 10 de setembro, 2016 </i>

## Index:
1. [JUnit Overview](#overview)
2. [JUnit Basics](#basics)
3. [Advanced JUnit](#advanced)

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

### <a name="basics"></a>JUnit Basics

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

### <a name="advanced"></a>Advanced JUnit
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
