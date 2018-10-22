Домашняя работа №5. В базе данных @localhost есть схема home в ней таблица persons с 14 полями: id int(50)(auto increment)(primery key),
sname varchar(50), secondname varchar(50), patronymic varchar(50), Age int(1), Sex char(1), BirtDay varchar(50), INN char(12),Ind char(6),
Counrty varchar(50), region varchar(50), city varchar(50), street varchar(50), house int(12), flat int (12)
Подключение к базе данных осуществляется по ссыле jdbc:mysql://localhost:3306/home?autoReconnect=true&useSSL=false
Ссылка в коде встречается 3 раза 2 раза в методе fillData и один раз в fillDatafromDB, соответственно для подключения к другой базе данных 
её нужн обудет изменить.
