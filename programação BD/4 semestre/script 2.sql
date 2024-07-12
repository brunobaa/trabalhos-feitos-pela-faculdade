
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
    CPF CHAR(11) PRIMARY KEY,
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
VALUES ('90123762966', 'Guilherme Guerra', 'gguerra@email.com', '1234567890', 'Centro', 1, 4);

INSERT INTO Passageiro (CPF, Nome, Email, Telefone, Bairro, Meio_Pagamento, Avaliacao)
VALUES ('41242132414', 'Joao Pedro', 'jpshadow@email.com', '9876543210', 'Bairro A', 2, 5);

INSERT INTO Passageiro (CPF, Nome, Email, Telefone, Bairro, Meio_Pagamento, Avaliacao)
VALUES ('21456647781', 'Arthur Passos', 'Arthurpassos@email.com', '5556667777', 'Bairro B', 3, 4);

INSERT INTO Passageiro (CPF, Nome, Email, Telefone, Bairro, Meio_Pagamento, Avaliacao)
VALUES ('12343657821', 'Gabriel Valls', 'gabrielvalls@email.com', '1112223333', 'Bairro C', 1, 3);

INSERT INTO Passageiro (CPF, Nome, Email, Telefone, Bairro, Meio_Pagamento, Avaliacao)
VALUES ('12453466547', 'Guilherme Cassol', 'guilhermecassol@email.com', '9998887777', 'Bairro D', 2, 5);

----------------------------------------------------------------------------------------------------------

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG001', 10.5, 25.0, 'Centro', 2.0, 'Bairro A', 1, 'CAR001');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG002', 8.2, 20.0, 'Bairro A', 1.5, 'Bairro B', 2, 'CAR002');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG003', 12.3, 30.0, 'Bairro B', 2.5, 'Bairro C', 3, 'CAR003');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG004', 7.8, 18.0, 'Bairro C', 1.0, 'Bairro D', 1, 'CAR001');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG005', 6.2, 15.0, 'Bairro A', 1.2, 'Bairro B', 2, 'CAR002');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG006', 9.5, 22.0, 'Bairro D', 2.0, 'Centro', 1, 'CAR003');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG007', 5.3, 14.0, 'Bairro B', 0.8, 'Bairro A', 3, 'CAR001');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG008', 11.1, 28.0, 'Centro', 3.0, 'Bairro C', 2, 'CAR002');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG009', 13.4, 32.0, 'Bairro C', 2.2, 'Bairro D', 1, 'CAR003');

INSERT INTO Viagem (Id, Kms, Valor, Bairro_Partida, Taxa_Adicional, Cidade_Destino, Viagem_TIPO, fk_Carro_Codigo)
VALUES ('VIAG010', 4.7, 12.0, 'Bairro D', 0.5, 'Bairro A', 3, 'CAR001');
