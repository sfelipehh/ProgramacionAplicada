INSERT
INTO
  Sede
  (id, nombre, direccion)
VALUES
  (0, 'SedeRoot', 'calle 0 # 0-0');
INSERT
INTO
  Empleado
  (id, DNI, nombres, apellidos, celular, email, fechaNacimiento, cupoAsignado, cupoRestante, sede_id)
VALUES
  (0, 0, 'Root', 'Root', '333-333333', 'root@empresa.com', '2000-02-02', 9999, 0, 0);
