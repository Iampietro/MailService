create table usuarios ( id int unsigned auto_increment,
						nombre_usuario varchar(60) not null,
                        pasword varchar(60) not null,
                        mail varchar(60) not null, 
                        nombre_real varchar (60) not null,
                        apellido varchar (60) not null,
                        direccion varchar (60) not null,
                        telefono int unsigned not null,
                        ciudad varchar (60) not null,
                        provincia varchar (60) not null,
                        pais varchar (60) not null,
                        constraint pk_usuarios primary key (id)
);

create table mensajes ( id int unsigned auto_increment,
						cuerpo varchar (330) not null,
                        remitente int unsigned not null,
                        receptor int unsigned not null,
                        asunto varchar (60) not null,
                        borrado boolean not null,
                        leido boolean not null,
                        constraint pk_mensaje primary key (id),
                        constraint fk_mensaje_remitente foreign key (remitente) references usuarios (id) on delete cascade,
                        constraint fk_mensaje_receptor foreign key (receptor) references usuarios (id) on delete cascade
);