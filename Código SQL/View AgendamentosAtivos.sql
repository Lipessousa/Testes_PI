 -- View de Agendamentos Ativos

create or replace view VwAgendamentosAtivos as 
select * from Agendamento
where DataAtendimento > now()-1;