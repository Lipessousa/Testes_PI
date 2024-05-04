drop database dbsalao;
create database dbsalao;
use dbsalao;



create table Servico (
	 IdServico int primary key auto_increment
	,NomeServico varchar(60) not null
	,PrecoLocal float(10) not null
	,PrecoVisita float(10)
);

create table Cliente (
	 IdCliente int primary key auto_increment
	,Nome varchar(30) not null
	,Sobrenome varchar(30) not null
	,Telefone varchar(13)
	,TipoAtendimento varchar(15) default 'Local'
);

create table Agendamento (
	 IdCliente int not null
	,NomeCliente varchar(30)
	,IdServico int not null
	,NomeServico varchar(60)
	,TipoAtendimento varchar(15)
	,DataAtendimento datetime not null
	,PrecoAtendimento float(10)
	,foreign key (IdCliente) references Cliente(IdCliente)
	,foreign key (IdServico) references Servico(IdServico)
);

create table Usuario (
	 IdUsuario int not null primary key auto_increment
    ,LoginUsuario varchar(50) not null
    ,SenhaUsuario varchar(255) not null
	,Administrador boolean not null
);


select * from Usuario;