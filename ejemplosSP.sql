CREATE PROCEDURE ObtenerUsuariosConSalarioAjustado
AS
BEGIN
  SET NOCOUNT ON;

  SELECT 
    Id,
    Nombre,
    Apellido,
    FechaIngreso,
    Salario,
    CASE 
      WHEN DAY(FechaIngreso) BETWEEN 1 AND 15 THEN Salario * 1.05
      WHEN DAY(FechaIngreso) BETWEEN 16 AND 30 THEN Salario * 1.10
      ELSE Salario
    END AS SalarioAjustado
  FROM Usuarios;
END;



UPDATE Usuarios
SET Salario = CASE 
  WHEN DAY(FechaIngreso) BETWEEN 1 AND 15 THEN Salario * 1.05
  WHEN DAY(FechaIngreso) BETWEEN 16 AND 30 THEN Salario * 1.10
  ELSE Salario
END;



CREATE PROCEDURE AjustarSalariosConCursor
AS
BEGIN
  DECLARE @Id INT,
          @FechaIngreso DATE,
          @Salario DECIMAL(10,2),
          @NuevoSalario DECIMAL(10,2);

  DECLARE cursorUsuarios CURSOR FOR
    SELECT Id, FechaIngreso, Salario
    FROM Usuarios;

  OPEN cursorUsuarios;
  FETCH NEXT FROM cursorUsuarios INTO @Id, @FechaIngreso, @Salario;

  WHILE @@FETCH_STATUS = 0
  BEGIN
    IF DAY(@FechaIngreso) BETWEEN 1 AND 15
      SET @NuevoSalario = @Salario * 1.05;
    ELSE IF DAY(@FechaIngreso) BETWEEN 16 AND 30
      SET @NuevoSalario = @Salario * 1.10;
    ELSE
      SET @NuevoSalario = @Salario;

    UPDATE Usuarios
    SET Salario = @NuevoSalario
    WHERE Id = @Id;

    FETCH NEXT FROM cursorUsuarios INTO @Id, @FechaIngreso, @Salario;
  END;

  CLOSE cursorUsuarios;
  DEALLOCATE cursorUsuarios;
END;