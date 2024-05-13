drop trigger if exists HashSenhas;

delimiter //
create trigger HashSenhas before insert on Usuario
for each row
begin 
	set new.SenhaUsuario = md5(new.SenhaUsuario);
end //
delimiter ;