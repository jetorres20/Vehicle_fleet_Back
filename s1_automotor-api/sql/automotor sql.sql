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
delete from ConductorEntity;

INSERT INTO REGISTROENTITY (ID,PRSC,PRSE,RTM,SOAT,VIGENTE)
VALUES(216,'2038-01-19 00:00:00','2038-01-19 00:00:00','2038-01-19 00:00:00','2038-01-19 00:00:00',1);

INSERT INTO VEHICULOENTITY(id,capacidad,marca,modelo,placa,registro_id)
values(216,4,'Renault','logan','KIS412',216);

INSERT INTO VEHICULOENTITY(id,capacidad,marca,modelo,placa,registro_id)
values(216,4,'Renault','logan','KIS412',216);

INSERT INTO PROFESORENTITY(ID,nombre,identificacion)
values(18,'Nestor Plata',1005);

INSERT INTO PROFESORENTITY(ID,nombre,identificacion)
values(1822,'Daniel Plata',100225);

INSERT INTO PRACTICAENTITY(ID,destino,descripcion,tiempoDeDesplazamiento,duracion,profesor_id)
values(2020,'Mi casa','Rumba en mi casa',2,12,18);
