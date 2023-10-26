create database Pizza3769;
use Pizza3769;
CREATE TABLE pais(
IdPais VARCHAR(3) NOT NULL,
Nombre varchar(30) NOT NULL,
CONSTRAINT PK_pais
PRIMARY KEY(IdPais)
);
create table Usuario(
IdUsuario	VARCHAR(20) not null,
nombre		VARCHAR(30) not null,
contrasena 	VARCHAR(30) not null,
email		VARCHAR(50) default null unique,
IdPais		VARCHAR(3) NOT NULL,
estatus		tinyint,
intentos	smallint default null,
CONSTRAINT PK_Usuario
primary key (IdUsuario),

CONSTRAINT FK_Usuario_Pais_IdPais
FOREIGN KEY(IdPais)
REFERENCES Pais(IdPais)
);
CREATE TABLE Cliente(
IdCliente 	int auto_increment not null,
Nombre 		VARCHAR(30) NOT NULL,
Telefono 	INT default null,
Email 		VARCHAR(50) default null unique,
estatus		tinyint,
CONSTRAINT PK_Cliente
PRIMARY KEY(IdCliente)
);
CREATE TABLE MetodoPago(
IdMetodoPago int,
Nombre Varchar(20),

CONSTRAINT PK_MetodoPago
PRIMARY KEY(IdMetodoPago)
);
CREATE TABLE Tipo(
IdTipo INT,
Nombre VARCHAR(20),
CONSTRAINT PK_Tipo
PRIMARY KEY(IdTipo)
);
CREATE TABLE Producto(
IdProducto INT,
Nombre VARCHAR(30),
IdTipo INT,
estatus	tinyint,
CONSTRAINT PK_Producto
PRIMARY KEY(IdProducto),

CONSTRAINT FK_Producto_Tipo_IdTipo
FOREIGN KEY(IdTipo)
REFERENCES Tipo(IdTipo)
);
CREATE TABLE DetallesFactura(
IdDetallesFactura INT,
IdProducto INT,
PrecioUnitario DECIMAL(5,2),
Cantidad INT,
CONSTRAINT PK_DetallesFactura
PRIMARY KEY(IdDetallesFactura),

CONSTRAINT FK_DetallesFactura_Producto_IdProducto
FOREIGN KEY(IdProducto)
REFERENCES Producto(IdProducto)
);
CREATE TABLE Factura(
IdFactura INT,
FechaEmision DATETIME,
IdUsuario VARCHAR(20),
IdCliente INT,
IdDetallesFactura INT,
IdMetodoPago INT,
Descuento INT,
SubTotal DECIMAL(8,2),
ISV DECIMAL(5,2),
Total DECIMAL(10,2), 
CONSTRAINT PK_IdFactura
PRIMARY KEY(IdFactura),

CONSTRAINT FK_Factura_Usuario_IdUsuario
FOREIGN KEY(IdUsuario)
REFERENCES Usuario(IdUsuario),

CONSTRAINT FK_Factura_Cliente_IdCliente
FOREIGN KEY(IdCliente)
REFERENCES Cliente(IdCliente),

CONSTRAINT FK_Factura_DetallesFactura_IdDetallesFactura
FOREIGN KEY(IdDetallesFactura)
REFERENCES DetallesFactura(IdDetallesFactura),

CONSTRAINT FK_Factura_MetodoPago_IdMetodoPago
FOREIGN KEY(IdMetodoPago)
REFERENCES MetodoPago(IdMetodoPago)
);
Insert into pais(IdPais, nombre) 
values('HND','Honduras'),
('BLZ', 'Belice'),
('CRI', 'Costa Rica'),
('SLV', 'El Salvador'),
('GTM', 'Guatemala'),
('NIC', 'Nicaragua'),
('PAN', 'Panamá');
select * from pais;

Insert into usuario (IdUsuario, nombre, contrasena, email, IdPais, estatus, Intentos)
values('HELEOZ', 'Leonardo Zavala', 'leoUN@H2022', 'HELEOZ@gmail.com' ,'HND', 1, 0),
('jpineda', 'JORGE PINEDA', 'UN@H2022', 'jpineda@gmail.com' ,'HND', 0, 0);
select * from usuario;

insert into metodopago (IdMetodoPago, Nombre)
value(1,'Efectivo'),
(2, 'Tarjeta');
select * from metodopago;

insert into tipo(IdTipo, Nombre)
value(1, 'Pizza'),
(2, 'Bebida');

