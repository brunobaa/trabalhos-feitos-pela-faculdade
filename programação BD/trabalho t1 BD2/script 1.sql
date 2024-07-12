

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