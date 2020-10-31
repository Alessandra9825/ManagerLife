USE [master]
GO

CREATE DATABASE [ManagerLife]
GO

USE [ManagerLife]
go


                            --Tabelas

CREATE TABLE [Usuario](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cel VARCHAR(16) NOT NULL,
    email VARCHAR(30) UNIQUE NOT NULL,
    senha VARCHAR(20) NOT NULL,
    imagem VARBINARY(MAX),
    fonte INT NOT NULL,
    cor VARCHAR(20),
    dataNascimento DATETIME,
    recuperarSenha BIT
)
GO

CREATE TABLE [SenhasAntigas](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    usuario_id INT NOT NULL,
    senha VARCHAR(20) NOT NULL    
)
GO

CREATE TABLE [Eventos](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    usuario_id INT NOT NULL,
    data DATETIME NOT NULL,
    tipo_id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,    
    descricao VARCHAR(1000),
    notificacao INT,
    cor VARCHAR(20)
)
GO

CREATE TABLE [EventoTipo](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,   
    descricao VARCHAR(50) NOT NULL
)
GO

CREATE TABLE [Financas](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,   
    usuario_id INT NOT NULL
)
GO

CREATE TABLE [ContaCredito](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,   
    financas_id INT NOT NULL,
    valorAtual MONEY NOT NULL,
    creditoMax MONEY,
    vencimento INT,
    fechamento INT
)
GO

CREATE TABLE [HistoricoCredito](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,   
    contaCredito_id INT NOT NULL,
    valor MONEY NOT NULL,
    data DATETIME NOT NULL,
    descricao VARCHAR(100),    
    parcelas INT NOT NULL    
)
GO

CREATE TABLE [ContaCorrente](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    valorAtual MONEY NOT NULL,
    financas_id INT NOT NULL
)
GO

CREATE TABLE [HistoricoCorrente](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    valor MONEY NOT NULL,
    data DATETIME NOT NULL,
    descricao VARCHAR(100),    
    contaCorrente_id INT NOT NULL,
    mensal BIT
)
GO

CREATE TABLE [Post_it](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    usuario_id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,    
    descricao VARCHAR(1000),
    situacaoPostit_id INT NOT NULL,
    tempoEstimado INT
)
GO

CREATE TABLE [SituacaoPostit](
    id INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    descricao VARCHAR(50) NOT NULL,
)
GO


                                    --Constraints
ALTER TABLE SenhasAntigas
ADD CONSTRAINT FK_SenhasAntigas_Usuario
FOREIGN KEY (usuario_id) REFERENCES Usuario (id)
GO

ALTER TABLE Eventos
ADD CONSTRAINT FK_Eventos_Usuario
FOREIGN KEY (usuario_id) REFERENCES Usuario (id)
GO

ALTER TABLE Eventos
ADD CONSTRAINT FK_Eventos_EventoTipo
FOREIGN KEY (tipo_id) REFERENCES EventoTipo (id)
GO

ALTER TABLE Financas
ADD CONSTRAINT FK_Financas_Usuario
FOREIGN KEY (usuario_id) REFERENCES Usuario (id)
GO

ALTER TABLE Post_it
ADD CONSTRAINT FK_Post_it_Usuario
FOREIGN KEY (usuario_id) REFERENCES Usuario (id)
GO

ALTER TABLE Post_it
ADD CONSTRAINT FK_Post_it_SituacaoPostit
FOREIGN KEY (situacaoPostit_id) REFERENCES SituacaoPostit (id)
GO

ALTER TABLE ContaCorrente
ADD CONSTRAINT FK_ContaCorrente_Financas
FOREIGN KEY (financas_id) REFERENCES Financas (id)
GO

ALTER TABLE ContaCredito
ADD CONSTRAINT FK_ContaCredito_Financas
FOREIGN KEY (financas_id) REFERENCES Financas (id)
GO

ALTER TABLE HistoricoCorrente
ADD CONSTRAINT FK_HistoricoCorrente_ContaCorrente
FOREIGN KEY (contaCorrente_id) REFERENCES ContaCorrente (id)
GO

ALTER TABLE HistoricoCredito
ADD CONSTRAINT FK_HistoricoCredito_ContaCredito
FOREIGN KEY (contaCredito_id) REFERENCES ContaCredito (id)
GO


INSERT INTO Usuario (nome, cel, email, senha, imagem, fonte, cor, dataNascimento, recuperarSenha) 
	Values('Lucas', '123456789', 'lucas@russo', '123456', null, 15, 'preto', '1992-09-07', 0)
GO