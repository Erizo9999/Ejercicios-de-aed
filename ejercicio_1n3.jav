//Ejercicio 1
//La información de los equipos se encuentra en una secuencia de carácter, con el siguiente formato:
Argentina#Integrantes(2caracteres)EsCabezaDeSerie(1caracter)Brasil#34SCanada#33N … # Colombia#FDS

//Integrantes indica la cantidad de personas que conforman el equipo (jugadores y cuerpo técnico) EsCabezaDeSerie indica S o N si la selección fue considerada como cabeza de serie en el sorteo.

//Y la informacion de la composicion del plantel en una secuencia de caracteres con el siguiente formato
Posicion(2caracter)NombreYApellidoJugador#Dorsal(2caracter)Edad(2caracter)|FDS

Posición: AR (Arquero), DF (Defensa), ME (Mediocampista), DE (Delantero), CT (Cuerpo Técnico)

//Se le solicita:
//Generar una secuencia de salida con la información de todos los Jugadores (no conforman el cuerpo técnico) (Nombre y apellido) que forman parte de equipos que solo son cabeza de serie. Separar la información de los Jugador con el carácter “%”
//Indicar por equipo, cuál es la edad del jugador más joven.

Accion ejercicio_1 es
    Ambiente
        sec_e, sec_p, sec_sal: secuencia de caracter
        e, p: caracter
        jov, integrantes, edad: entero

        Funcion caracter_entero (n:caracter): entero
            n2:entero
            segun n hacer
                = "0" : n2:= 0
                = "1" : n2:= 1
                = "2" : n2:= 2
                = "3" : n2:= 3
                = "4" : n2:= 4
                = "5" : n2:= 5
                = "6" : n2:= 6
                = "7" : n2:= 7
                = "8" : n2:= 8
                = "9" : n2:= 9
            fin segun
            caracter_entero:= n2
        fin Funcion

        Procedimiento convertir_num(c:caracter)
            Ambiente
                c1: caracter
            Procedimiento
                resguardo:= 0
                Para i:=2 hasta 1, -1 hacer
                    c1:= c
                    resguardo:= resguardo + (caracter_entero(c) * (10**(i-1)))
                    Si c1 = e entonces
                        AVZ(sec_e, e)
                    Si No
                        Si c1 = p entonces 
                            AVZ(sec_p, p)
                        Fin Si
                    Fin Si
                Fin Para
        Fin Procedimiento

    Proceso
        ARR(sec_e), ARR(sec_p) ARR(sec_sal)
        AVZ(sec_e, e), AVZ(sec_p, p)

        Mientras NFDS (sec_e) hacer
            jov:= 100
            Esc("para el equipo:")
            Mientras e <> "#" hacer
                Esc(e)
                AVZ(sec_e, e)
            fin Mientras
            AVZ(sec_e, e)
            
            convertir_num(e)
            AVZ(sec_e, e)

            integrantes:= resguardo

            Para i:= 1 hasta integrantes hacer
                Si e = "S" entonces
                    AVZ(sec_p, p)
                    Si p = "T" entonces 
                        Repetir
                            AVZ(sec_p, p)
                        Hasta que p = "#" 
                        Para i:= 1 hasta 4 hacer //la consigna pide que muestre la edad del "jugador" y el cuerpo tecnico no son jugadores asi que no los trado la edad
                            AVz(sec_p, p)
                        Fin Para
                    Si No 
                        AVZ(sec_p, p)
                        Mientras p <> "#"
                            Esc(sec_sal, p)  //grabo en la salida el nombre del jugador que no integra el cuerpo tecnico
                            AVZ(sec_p, p)
                        Fin Mientras
                        Para i:=1 hasta 3 hacer
                            AVZ(sec_p, p)
                        Fin Para
                        convertir_num(p) // analizo su edad
                    Fin Si
                SI No //analizo el jugador mas joven de los equipos que no son cabeza de lista
                    Mientras p <> "#" hacer
                        AVZ(sec_p, p)
                    Fin Mientras
                    Para i:=1 hasta 3 hacer
                        AVZ(sec_p, p)
                    Fin Para
                    convertir_num(p) // analizo su edad
                Fin Si
                Si edad < jov entonces // compruevo si es el mas joven
                    jov:= edad
                Fin Si
            Fin Para
            AVZ(sec_e, e)
            Esc("la edad del jugador mas joven es:", jov) // muestro al mas joven dentro del plantel del equipo
        Fin Mientras
        Cerrar(sec_e)
        Cerrar(sec_p)
        Cerrar(sec_sal)
Fin Accion        