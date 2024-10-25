-- Tabla para almacenar información de los libros
CREATE TABLE Libros (
    LibroID INT IDENTITY(1,1) PRIMARY KEY,
    Titulo NVARCHAR(100) NOT NULL,
    Autor NVARCHAR(100) NOT NULL,
    ISBN NVARCHAR(20) UNIQUE NOT NULL,
    AnioPublicacion INT,
    Editorial NVARCHAR(100),
    CopiasDisponibles INT NOT NULL CHECK (CopiasDisponibles >= 0)
);
GO

-- Tabla para almacenar información de los usuarios de la biblioteca
CREATE TABLE Usuarios (
    UsuarioID INT IDENTITY(1,1) PRIMARY KEY,
    Nombre NVARCHAR(50) NOT NULL,
    Apellido NVARCHAR(50) NOT NULL,
    DNI NVARCHAR(15) UNIQUE NOT NULL,
    Telefono NVARCHAR(15),
    Correo NVARCHAR(100)
);
GO

-- Tabla para registrar los préstamos
CREATE TABLE Prestamos (
    PrestamoID INT IDENTITY(1,1) PRIMARY KEY,
    UsuarioID INT NOT NULL,
    FechaPrestamo DATE NOT NULL DEFAULT GETDATE(),
    FechaDevolucionEsperada DATE NOT NULL,
    FechaDevolucion DATE,
    FOREIGN KEY (UsuarioID) REFERENCES Usuarios(UsuarioID)
);
GO

-- Tabla para registrar el detalle de los libros prestados en cada préstamo
CREATE TABLE DetallePrestamo (
    DetalleID INT IDENTITY(1,1) PRIMARY KEY,
    PrestamoID INT NOT NULL,
    LibroID INT NOT NULL,
    Cantidad INT NOT NULL CHECK (Cantidad > 0),
    FOREIGN KEY (PrestamoID) REFERENCES Prestamos(PrestamoID),
    FOREIGN KEY (LibroID) REFERENCES Libros(LibroID)
);
GO

-- Ejemplo de inserción de datos en las tablas
-- Insertar algunos libros
INSERT INTO Libros (Titulo, Autor, ISBN, AnioPublicacion, Editorial, CopiasDisponibles)
VALUES 
    ('El Principito', 'Antoine de Saint-Exupéry', '9781234567890', 1943, 'Reynal & Hitchcock', 5),
    ('Cien Años de Soledad', 'Gabriel García Márquez', '9789876543210', 1967, 'Sudamericana', 3);

-- Insertar algunos usuarios
INSERT INTO Usuarios (Nombre, Apellido, DNI, Telefono, Correo)
VALUES 
    ('Juan', 'Pérez', '12345678', '987654321', 'juan.perez@mail.com'),
    ('Ana', 'García', '87654321', '912345678', 'ana.garcia@mail.com');

-- Insertar un préstamo y su detalle
INSERT INTO Prestamos (UsuarioID, FechaDevolucionEsperada)
VALUES (1, DATEADD(DAY, 15, GETDATE()));

INSERT INTO DetallePrestamo (PrestamoID, LibroID, Cantidad)
VALUES (1, 1, 1); -- Prestamo del libro "El Principito"




SELECT * FROM Libros
SELECT * FROM Usuarios
SELECT * FROM Prestamos
SELECT * FROM DetallePrestamo