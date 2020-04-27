/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  estudiante
 * Created: 27-feb-2020
 */

delete from RegistroEntity;
delete from VehiculoEntity;
delete from ProfesorEntity;
delete from PracticaEntity;
delete from UniversidadEntity;
delete from FranjaHorariaSemanalEntity;
delete from TipoVehiculoEntity;
delete from ReservaEntity;
delete from EstudianteEntity;
delete from EncuestaEntity;
delete from ESTUDIANTEENTITY_PROFESORENTITY;
delete from RESERVAENTITY_ESTUDIANTEENTITY;
delete from AgendaEntity;
delete from ConductorEntity;

INSERT INTO REGISTROENTITY (ID,PRSC,PRSE,RTM,SOAT,VIGENTE)
VALUES(216,'2038-01-19 00:00:00','2038-01-19 00:00:00','2038-01-19 00:00:00','2038-01-19 00:00:00',1);

INSERT INTO REGISTROENTITY (ID,PRSC,PRSE,RTM,SOAT,VIGENTE)
VALUES(217,'2038-01-19 00:00:00','2038-01-19 00:00:00','2038-01-19 00:00:00','2038-01-19 00:00:00',1);

INSERT INTO VEHICULOENTITY(id,capacidad,marca,modelo,placa,registro_id)
values(216,4,'Renault','logan','KIS412',216);

INSERT INTO VEHICULOENTITY(id,capacidad,marca,modelo,placa,registro_id)
values(217,4,'Renault','Kwid','KIS420',217);

INSERT INTO PROFESORENTITY(ID,nombre,identificacion)
values(18,'Nestor Plata',1005);

INSERT INTO PROFESORENTITY(ID,nombre,identificacion)
values(1822,'Daniel Plata',100225);

INSERT INTO PRACTICAENTITY(ID,destino,descripcion,tiempoDeDesplazamiento,duracion,profesor_id)
values(2020,'Mi casa','Rumba en mi casa',2,12,18);

INSERT INTO CONDUCTORENTITY(ID,idConductor, name)
VALUES(101,12345,'Daniel');

INSERT INTO CONDUCTORENTITY(ID,idConductor, name)
VALUES(102,54321,'Nestor');

INSERT INTO CONDUCTORENTITY(ID,idConductor, name)
VALUES(103,678910,'Pablo');

INSERT INTO AGENDAENTITY(ID, fecha, reservada, conductor_id)
VALUES(202,'2020-01-19 00:00:00', 1, 101);

INSERT INTO AGENDAENTITY(ID, fecha, reservada, conductor_id)
VALUES(203,'2020-02-02 00:00:00', 1, 101);

INSERT INTO AGENDAENTITY(ID, fecha, reservada, conductor_id)
VALUES(205,'2020-01-29 00:00:00', 1, 101);

INSERT INTO AGENDAENTITY(ID, fecha, reservada, conductor_id)
VALUES(206,'2020-01-23 00:00:00', 1, 101);

INSERT INTO AGENDAENTITY(ID, fecha, reservada, conductor_id)
VALUES(204,'2021-01-19 00:00:00', 1, 102);

INSERT INTO FRANJAHORARIASEMANALENTITY(ID,FINISHHOUR,STARTHOUR,WEEKDAY,CONDUCTOR_ID)
VALUES(333,'2021-01-19 00:00:00','2021-01-18 00:00:00',5,101);

INSERT INTO FRANJAHORARIASEMANALENTITY(ID,FINISHHOUR,STARTHOUR,WEEKDAY,CONDUCTOR_ID)
VALUES(334,'2021-01-26 00:00:00','2021-01-25 00:00:00',4,101);
-- INSERT INTO RESERVAENTITY(ID, fechaReserva, fechaServicio, estadoValidacion, reservaRecurrente, cancelada, motivoCancelacion, profesor, encuesta, estudiantes, practica, vehiculo, agenda, conductor)
-- VALUES(222,'2020-01-19 00:00:00', '2021-01-19 00:00:00', TRUE, TRUE, FALSE,'',NULL,NULL,NULL,NULL,NULL,101);

