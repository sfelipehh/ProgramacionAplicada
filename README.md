## Participantes
- ### Samuel Hernández
- ### Ana Londoño

## [Formatos](https://docs.google.com/spreadsheets/d/1UWm8Wyd6_T9sMyD8EEZ08AhpKJ9wbOb9we1-EXGffIk/edit?usp=sharing)

## Planeación Proyecto Final

<div style="background:#0D1117">

````mermaid
%%{init: { 'theme':'dark', 'securityLevel':'loose', 'themeVariables':{'darkMode':true,   'background':'#1d1634','sectionBkgColor' : '#d39aff', 'sectionBkgColor2':'#ff665a', 'gridColor':'#00ffe7', 'textColor':'#00ffe7'} ,"gantt":{ "barGap": 10,"fontSize":15, "sectionFontSize":15, "numberSectionStyles":3}} }%%
gantt

    title Planificación Proyecto Final
    axisFormat %d-%m-%y
    dateFormat  YYYY-MM-DD
    todayMarker stroke-width:5px,stroke:#69d30f,opacity:0.5


    section Análisis
    Levantamiento de Informacion :active, a1, 2022-11-26, 7d
    Descripcion del problema :crit, active, a2, 2022-11-26, 7d
    Definicion de objetivos :active, a3, 2022-11-26, 7d
    Formulación del Proyecto :active, a4, 2022-11-26, 7d
    Definición de StakeHolder's :active, a5, 2022-11-26, 7d

    section Diseño
    Diagramas de Casos de uso UML :crit, d1, 2022-11-30, 3d
    Casos de Uso En Formato Expandido : d2, after d1,2d
    Modelo Entidad Relación (MER) :crit, d3, 2022-11-30, 3d
    Modelo Relacional (MR) :crit, d4, after d3,3d
    Diagrama de Secuencia :crit, d9, after d1, 3d
    Diagrama de Clases UML : d5, 2022-12-03, 5d
    Especificación de Clases : d6, after d5, 3d
    Diagrama de Componentes UML : d7, after d6, 2d
    Diagrama de Despliegue UML : d8, after d6, 2d

    section Codificación
    Desarrollo de la Capa de datos (Base de Datos) :crit, c1, after d3, 2d
    Validación de Campos(Numéricos,texto,vacio,contraseñas,etc) : c2, after c1, 7d
    Seguridad y Perfiles :crit, c3, after cl2, 1d
    Diseño gráfico : c4, after cl1, 5d

    section Codificación Lógica Servicios
    Consultas :crit, cl1, after c1, 1d
    Otros servicios :crit, cl2, after cl1, 5d

    Servicios en funcionamiento :milestone,ml, 2022-12-10,4h
    
    section Codificación UI Servicios
    Consultas : cui1, after cl1 d9, 1d
    Otros servicios : cui2, after cui1, 4d

    section Pruebas
    Verificación base de datos (CRUD en SQL) : ts1, after c1, 1d
    Verificación de la capa de Logíca :crit ts2, after c1, 6d
    Verificación de la capa de Presentación : ts3, after cl1, 5d
    Verificacion de Seguridad :crit, ts4, after c3, 1d

    Entrega : milestone, m2, 2022-12-14,2m
````
</div>

## Modelo Entidad - Relación Simple
<div style="background:#0D1117">

````mermaid
%%{init: {'theme':'dark', 'themeCSS':'defs marker path {stroke:#edf1e4;}', 'themeVariables':{'primaryBorderColor':'#ffe9e9','lineColor':'#f0e1eb', 'textColor': '#db5f09','primaryColor':'#579cbc','attributeBackgroundColorOdd':'#cfc1d7','tertiaryColor':'#2d313b', 'attributeBackgroundColorEven':'#efc5d4'}}}%%
erDiagram
    Sede ||--|{ Empleado : emplea
    Sede ||--|| Administrador-Sede__debil : administra
    Sede ||--|{ Localidad : cubre
    
    Cuadrilla }|--|| Sede : pertenece
    Cuadrilla ||--|{ Localidad : "trabaja en"
    Cuadrilla ||--|{ Empleado : contiene
    
    Empleado ||--|{ Evento-de-Gasto : registra  
    
    Evento-de-Gasto ||--|| Localidad : "registrado en"
    Evento-de-Gasto ||--|{ Cuadrilla : "registrado por"
    
    Usuario ||--|| Empleado : accede
    Usuario ||--|| Perfil : posee
    
    Administrador-Sede__debil ||--|| Empleado : administra
    
    Supervisor-Cuadrilla__debil ||--|| Cuadrilla : supervisa
    Supervisor-Cuadrilla__debil ||--|| Empleado : supervisa
````
</div>

## Modelo Relacional
<div style="background:#0D1117">

````mermaid
%%{init: {'theme':'dark', 'themeCSS':'defs marker path {stroke:#edf1e4;}', 'themeVariables':{'primaryBorderColor':'#ffe9e9','lineColor':'#f0e1eb', 'textColor': '#db5f09','primaryColor':'#579cbc','attributeBackgroundColorOdd':'#cfc1d7','tertiaryColor':'#2d313b', 'attributeBackgroundColorEven':'#efc5d4'}}}%%
erDiagram
    Sede ||--|{ Empleado : emplea
    Sede ||--|{ Localidad : cubre
    Sede ||--|| Administrador-Sede : administra
    
    Cuadrilla }|--|| Sede : pertenece
    Cuadrilla ||--|{ Localidad : "trabaja en"
    Cuadrilla ||--|{ Empleado : contiene
    
    Empleado ||--|{ Evento-de-Gasto : registra  
    
    Evento-de-Gasto ||--|| Localidad : "registrado en"
    Evento-de-Gasto ||--|{ Cuadrilla : "registrado por"
    
    Usuario ||--|| Empleado : accede
    Usuario ||--|| Perfil : posee
        
    Administrador-Sede ||--|| Empleado : administra
    
    Supervisor-Cuadrilla ||--|| Cuadrilla : supervisa
    Supervisor-Cuadrilla ||--|| Empleado : supervisa
    
    Usuario {
        numero Id PK
        numero IdEmpleado FK
        numero IdPerfil FK
        texto NombreUsuario
        texto Contrasenia
    }
    
    Perfil {
        numero Id PK
        booleano Consulta
        booleano Revision
        booleano Registro
    }
    
    Sede {
        numero Id PK
        text Nombre
        text Direccion
        
    }
    
    Localidad {
        numero Id PK
        texto Nombre
        text CalleInicio
        text CalleFin
        text CarreraInicio
        text CarreraFin
        numero IdSede FK
        numero IdCuadrilla FK
    }
    
    Administrador-Sede {
        number Id PK
        number IdSede FK
        number IdEmpleado FK
    }
    
    Supervisor-Cuadrilla {
        number Id Pk
        number IdCuadrilla FK
        number IdEmpleado FK
    }
    
    Cuadrilla {
        numero Id PK
        texto Nombre
        numero IdSede FK
        numero CantidadEmpleados
        numero CupoAsignado
        numero CupoRestante
    }
    
    Empleado {
        numero Id PK
        numero DNI
        texto Nombres
        texto Apellidos
        texto Celular
        texto Email
        texto FechaNacimiento
        numero IdCuadrilla FK
        numero IdSede FK
        numero CupoAsignado
        numero CupoRestante
    }
    
    Evento-de-Gasto {
        numero Id PK
        texto Fecha
        texto Hora
        numero IdLocalidad FK
        numero IdCuadrilla FK
        texto Descripcion
        numero Valor
        numero IdEmpleado FK
        booleano Aprobado
    }
    
````
</div>
 
