## Planeacion Proyecto Final

````mermaid
%%{init: { 'theme':'dark', 'themeVariables':{'background':'#1d1634','sectionBkgColor' : '#d39aff', 'sectionBkgColor2':'#ff665a', 'gridColor':'#00ffe7', 'textColor':'#00ffe7'} ,"gantt":{"barGap": 10,"fontSize":15, "sectionFontSize":15, "numberSectionStyles":3}} }%%
gantt

    title Planificacion Proyecto Final
    axisFormat %d-%m-%y
    dateFormat  YYYY-MM-DD
    todayMarker stroke-width:5px,stroke:#69d30f,opacity:0.5


    section Análisis
    Levantamiento de Informacion :active, a1, 2022-11-26, 7d
    Descripcion del problema :crit, active, a2, 2022-11-26, 7d
    Definicion de objetivos :active, a3, 2022-11-26, 7d
    Formulacion del Proyecto :active, a4, 2022-11-26, 7d
    Definicion de StakeHolder's :active, a5, 2022-11-26, 7d

    section Diseño
    Diagramas de Casos de uso UML :crit, d1, 2022-11-30, 3d
    Casos de Uso En Formato Expandido : d2, after d1,3d
    Modelo Entidad Relacion (MER) :crit, d3, 2022-11-30, 3d
    Modelo Relacional (MR) :crit, d4, after d3,3d
    Diagrama de Clases UML : d5, 2022-12-3, 5d
    Especificacion de Clases : d6, after d5, 3d
    Diagrama de Componentes UML : d7, after d6, 2d
    Diagrama de Despliegue UML : d8, after d6, 2d

    section Codificacion
    Desarrollo de la Capa de datos (Base de Datos) :crit, c1, after d3, 2d
    Validacion de Campos(Numéricos,texto,vacio,contraseñas,etc) : c2, after c1, 7d
    Seguridad y Perfiles :crit, c3, after cl2, 1d
    Diseño gráfico : c4, after cl1, 5d

    section Codificacion Logica Servicios
    Consultas :crit, cl1, after c1, 1d
    Otros servicios :crit, cl2, after cl1, 5d

    Servicios en funcionamiento :milestone,ml, 2022-12-10,4h
    
    section Codificacion UI Servicios
    Consultas : cui1, after cl1, 1d
    Otros servicios : cui2, after cui1, 4d

    section Pruebas
    Verificacion base de datos (CRUD en SQL) : ts1, after c1, 1d
    Verificacion de la capa de Logíca :crit ts2, after c1, 6d
    Verificacion de la capa de Presentacion : ts3, after cl1, 5d
    Verificacion de Seguridad :crit, ts4, after c3, 1d

    Entrega : milestone, m2, 2022-12-12,2m
````