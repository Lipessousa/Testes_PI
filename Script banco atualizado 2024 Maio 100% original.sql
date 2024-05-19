drop database if exists dbsalao;
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
    ,NomeCompleto varchar(90) null
	,Telefone varchar(13)
	,TipoAtendimento varchar(15) default 'Local'
);


create table Agendamento (
	 IdAgendamento int primary key auto_increment
	,IdCliente int not null
	,NomeCliente varchar(90)
	,IdServico int not null
	,NomeServico varchar(60)
	,TipoAtendimento varchar(15)
	,DataAtendimento datetime not null
	,PrecoAtendimento float(10,2)
	,foreign key (IdCliente) references Cliente(IdCliente)
	,foreign key (IdServico) references Servico(IdServico)
);
 
create table Usuario (
	 IdUsuario int auto_increment not null primary key
    ,LoginUsuario varchar(50) not null
    ,SenhaUsuario varchar(255) not null
	,Administrador int not null default 0
);


-- View de agendamentos ativos
drop view if exists VwAgendamentosAtivos;
create view VwAgendamentosAtivos as 
select *
from agendamento
where DataAtendimento > concat(substr(now(),1,4),substr(now(),5,4),substr(now(),9,2)-1, ' ', substr(now(),11,9));


-- View de agendamentos inativos
drop view if exists VwAgendamentosInativos;
create view VwAgendamentosInativos as 
select *
from agendamento
where DataAtendimento < concat(substr(now(),1,4),substr(now(),5,4),substr(now(),9,2)-1, ' ', substr(now(),11,9));


-- procedure que atualiza o banco com os campos em destaque.
drop procedure if exists EditaServico;
delimiter //

create procedure EditaServico(
    in nomeparametro varchar(50),
    in novonome varchar(50),
    in novoprecolocal float,
    in novoprecovisita float
)
begin
    update Servico 
    set PrecoLocal = novoprecolocal 
    where NomeServico = nomeparametro;
    
    update Servico 
    set PrecoVisita = novoprecovisita 
    where NomeServico = nomeparametro;
    
    update Servico 
    set NomeServico = novonome 
    where NomeServico = nomeparametro;
end //

delimiter ;


-- Procedure que realiza a atualização dos dados do agendamento.
drop procedure if exists EditaAgendamento;

delimiter //
create procedure EditaAgendamento(
in identificaAgendamento int,
in novoServico varchar(50),
in novaData varchar(50)
)
begin

declare servico int;


update Agendamento 
set NomeServico = novoServico where IdAgendamento = identificaAgendamento;

update Agendamento
set DataAtendimento = novaData where IdAgendamento = identificaAgendamento;

end //
delimiter ;


-- Trigger que omite no banco as senhas dos usuários caso venham a ser acessadas.
drop trigger if exists HashSenhas;
delimiter //
create trigger HashSenhas before insert on Usuario
for each row
begin 
	set new.SenhaUsuario = md5(new.SenhaUsuario);
end //
delimiter ;


