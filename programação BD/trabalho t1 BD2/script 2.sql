
DROP TABLE Usa;
DROP TABLE Viagem;
DROP TABLE Passageiro;
DROP TABLE Carro;


CREATE TABLE Carro (
    Codigo CHAR(8) PRIMARY KEY,
    Placa CHAR(7),
    Marca CHAR(16),
    Modelo CHAR(32),
    Ano INT,
    Nome_Motorista CHAR(32)
);
---------------------------------------------------------------
CREATE TABLE Passageiro (
    CPF CHAR(10) PRIMARY KEY,
    Nome CHAR(32),
    Email CHAR(32),
    Telefone CHAR(11),
    Bairro CHAR(16),
    Meio_Pagamento INT,
    Avaliacao INT
);
-----------------------------------------------------------------
CREATE TABLE Viagem (
    Id CHAR(8) PRIMARY KEY,
    Kms NUMBER(10, 2),
    Valor NUMBER(10, 2),
    Bairro_Partida CHAR(16),
    Taxa_Adicional NUMBER(10, 2),
    Cidade_Destino CHAR(16),
    Viagem_TIPO INT,
    fk_Carro_Codigo CHAR(8),
    CONSTRAINT fk_Viagem_2 FOREIGN KEY (fk_Carro_Codigo) REFERENCES Carro (Codigo) ON DELETE CASCADE
);
--------------------------------------------------------------------
CREATE TABLE Usa (
    fk_Passageiro_CPF CHAR(10),
    fk_Viagem_Id CHAR(8),
    CONSTRAINT fk_Usa_1 FOREIGN KEY (fk_Passageiro_CPF) REFERENCES Passageiro (CPF),
    CONSTRAINT fk_Usa_2 FOREIGN KEY (fk_Viagem_Id) REFERENCES Viagem (Id)
);
---------------------------------------------------------------------

INSERT INTO Carro (Codigo, Placa, Marca, Modelo, Ano, Nome_Motorista)
VALUES ('CAR001', 'ABC123', 'Toyota', 'Corolla', 2020, 'João da Silva');

INSERT INTO Carro (Codigo, Placa, Marca, Modelo, Ano, Nome_Motorista)
VALUES ('CAR002', 'XYZ789', 'Honda', 'Civic', 2019, 'Maria Santos');

INSERT INTO Carro (Codigo, Placa, Marca, Modelo, Ano, Nome_Motorista)
VALUES ('CAR003', 'DEF456', 'Ford', 'Fiat', 2021, 'Pedro Oliveira');
-----------------------------------------------------------------------------------------
INSERT INTO Passageiro (CPF, Nome, Email, Telefone, Bairro, Meio_Pagamento, Avaliacao)
VALUES ('9012376966', 'Guilherme Guerra', 'gguerra@email.com', '1234567890', 'Centro', 1, 4);

INSERT INTO Passageiro (CPF, Nome, Email, Telefone, Bairro, Meio_Pagamento, Avaliacao)
VALUES ('4124212414', 'Joao Pedro', 'jpshadow@email.com', '9876543210', 'Moinhos de Vento', 2, 5);

INSERT INTO Passageiro (CPF, Nome, Email, Telefone, Bairro, Meio_Pagamento, Avaliacao)
VALUES ('2145667781', 'Arthur Passos', 'Arthurpassos@email.com', '5556667777', 'Partenon', 3, 4);

INSERT INTO Passageiro (CPF, Nome, Email, Telefone, Bairro, Meio_Pagamento, Avaliacao)
VALUES ('1234367821', 'Gabriel Valls', 'gabrielvalls@email.com', '1112223333', 'Bela vista', 1, 3);

INSERT INTO Passageiro (CPF, Nome, Email, Telefone, Bairro, Meio_Pagamento, Avaliacao)
VALUES ('1245346547', 'Guilherme Cassol', 'guilhermecassol@email.com', '9998887777', 'Cidade Baixa', 2, 5);
----------------------------------------------------------------------------------------------------------
INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG001', 10.5, 25.0, 'Centro', 2.0, 'Moinhos de Vento', 1, 'CAR001');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG002', 8.2, 20.0, 'Moinhos de Vento', 1.5, 'Partenon', 2, 'CAR002');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG003', 12.3, 30.0, 'Partenon', 2.5, 'Floresta', 3, 'CAR003');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG004', 7.8, 18.0, 'Floresta', 1.0, 'Cidade Baixa', 1, 'CAR001');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG005', 6.2, 15.0, 'Moinhos de Vento', 1.2, 'Partenon', 2, 'CAR002');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG006', 9.5, 22.0, 'Cidade Baixa', 2.0, 'Centro', 1, 'CAR003');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG007', 5.3, 14.0, 'Partenon', 0.8, 'Moinhos de Vento', 3, 'CAR001');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG008', 11.1, 28.0, 'Centro', 3.0, 'Floresta', 2, 'CAR002');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG009', 13.4, 32.0, 'Floresta', 2.2, 'Cidade Baixa', 1, 'CAR003');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG010', 4.7, 12.0, 'Cidade Baixa', 0.5, 'Moinhos de Vento', 3, 'CAR001');
