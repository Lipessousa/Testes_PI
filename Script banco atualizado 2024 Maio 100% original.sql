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
    ,Atendente varchar(50)
	,DataAtendimento datetime not null
	,PrecoAtendimento float(10,2)
	,foreign key (IdCliente) references Cliente(IdCliente) on delete cascade
	,foreign key (IdServico) references Servico(IdServico)
);

create table HistoricoAgendamento (
	 IdHistorico int primary key auto_increment
	,IdAgendamentoOrigem int 
	,IdCliente int not null
	,NomeCliente varchar(90)
	,IdServico int not null
	,NomeServico varchar(60)
	,TipoAtendimento varchar(15)
    ,Atendente varchar(50)
	,DataAtendimento datetime not null
	,PrecoAtendimento float(10,2)
);
 
create table Usuario (
	 IdUsuario int auto_increment not null primary key
    ,LoginUsuario varchar(50) not null
    ,SenhaUsuario varchar(255) not null
	,Administrador int not null default 0
);

-- Triger de manutenção de deleção de clientes.
drop trigger if exists deletesClientes;
delimiter //
create trigger deletesClientes before delete on Cliente
for each row 
begin
	insert into HistoricoAgendamento(IdAgendamentoOrigem, IdCliente, NomeCliente, IdServico, NomeServico, TipoAtendimento, Atendente, DataAtendimento, PrecoAtendimento)
    select IdAgendamento, old.IdCliente, NomeCliente, IdServico, NomeServico, TipoAtendimento, Atendente, DataAtendimento, PrecoAtendimento
    from Agendamento
    where IdCliente = old.IdCliente;
    
    delete from agendamento where IdCliente = old.Idcliente;
end //
delimiter ;

-- Procedure de higienização da base de agendamentos, mantendo somemente os agendamentos ativos, sempre que chamada.
drop procedure if exists AtualizaHistorico;
delimiter //
create procedure AtualizaHistorico()
begin
	insert into HistoricoAgendamento(IdAgendamentoOrigem, IdCliente, NomeCliente, IdServico, NomeServico, TipoAtendimento, Atendente, DataAtendimento, PrecoAtendimento)
    select IdAgendamento, old.IdCliente, NomeCliente, IdServico, NomeServico, TipoAtendimento, Atendente DataAtendimento, PrecoAtendimento
    from Agendamento
    where DataAtendimento < concat(substr(now(),1,4),substr(now(),5,4),substr(now(),9,2)-1, ' ', substr(now(),11,9));
    
    delete from agendamento where DataAtendimento < concat(substr(now(),1,4),substr(now(),5,4),substr(now(),9,2)-1, ' ', substr(now(),11,9));
end //
delimiter ;

-- View de agendamentos ativos
drop view if exists VwAgendamentosAtivos;
create view VwAgendamentosAtivos as 
select *
from agendamento
where DataAtendimento > concat(substr(now(),1,4),substr(now(),5,4),substr(now(),9,2)-1, ' ', substr(now(),11,9));


-- View de agendamentos inativos
drop view if exists VwAgendamentosInativos;
create view VwAgendamentosInativos as 
select a.IdAgendamento, a.IdCliente, a.NomeCliente, a.IdServico, a.NomeServico,  a.TipoAtendimento, a.Atendente, a.DataAtendimento, a.PrecoAtendimento
from agendamento a
where a.DataAtendimento < concat(substr(now(),1,4),substr(now(),5,4),substr(now(),9,2)-1, ' ', substr(now(),11,9))
union all
select h.IdAgendamentoOrigem, h.IdCliente, h.NomeCliente, h.IdServico, h.NomeServico,  h.TipoAtendimento,h.Atendente, h.DataAtendimento, h.PrecoAtendimento
from HistoricoAgendamento h ;

select * from VwAgendamentosInativos;
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
