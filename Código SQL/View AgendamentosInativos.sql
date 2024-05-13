 -- View Agendamentos inativos

create or replace view VwagendamentosInativos as
select * from Agendamento
where DataAtendimento < now()-1;