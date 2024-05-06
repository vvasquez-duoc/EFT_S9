package eft_s9_victor_vasquez;

import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EFT_S9_Victor_Vasquez {
    static String[] tipoEntradas = new String[3];
    static String[] precioEntradas = new String[3];

    static int edadTerceraEdad = 60;
    
    static ArrayList<Integer> descuentos = new ArrayList<>();
    static ArrayList<String> nombreDescuentos = new ArrayList<>();
    
    static HashMap<String, String[]> entradasPalco = new HashMap<>();
    static HashMap<String, String[]> entradasPA = new HashMap<>();
    static HashMap<String, String[]> entradasPB = new HashMap<>();
    
    static ArrayList<String> entradasVendidasPalco = new ArrayList<>();
    static ArrayList<String> entradasVendidasPA = new ArrayList<>();
    static ArrayList<String> entradasVendidasPB = new ArrayList<>();
    
    static String[] letrasColumnas = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
    static int cantFilas = 2;
    static int cantColumnas = 10;
    static String separador = "-------------------------------------------";
    
    static String asientoSeleccionado = "";
    static boolean asientoNoValido = false;
    static boolean asientoOcupado = false;
    static boolean hayAsientosVendidos = false;
    
    static String nombreCliente = "";
    static String rutCliente = "";
    
    public static void main(String[] args) {
        int[][] asientosPalco = new int[2][10];
        int[][] asientosPA = new int[5][12];
        int[][] asientosPB = new int[3][8];
        
        descuentos.add(10);
        descuentos.add(15);
        
        nombreDescuentos.add("ESTUDIANTE");
        nombreDescuentos.add("TERCERA EDAD");
        
        Utilitarios.limpiaPantalla();
        menuPrincipal(asientosPalco, asientosPA, asientosPB);
    }
    
    public static void menuPrincipal(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        int opcion;
        String[] itemsMenu = new String[8];
        
        opcion = 0;
        itemsMenu[0] = "VENTA DE ENTRADAS";
        itemsMenu[1] = "EDITAR / ELIMINAR ENTRADAS";
        itemsMenu[2] = "VER PROMOCIONES";
        itemsMenu[3] = "EDITAR PROMOCIONES";
        itemsMenu[4] = "IMPRIMIR BOLETA";
        itemsMenu[5] = "LISTADO DE VENTAS";
        itemsMenu[6] = "CALCULAR INGRESOS TOTALES";
        itemsMenu[7] = "SALIR";
        
        try{
            do{
                System.out.println("BIENVENIDO A LA ADMINISTRACION DEL TEATRO MORO");
                System.out.println("");
                System.out.println("SELECCIONE UNA OPCION");
                for(int i=0; i<itemsMenu.length; i++){
                    System.out.println((i + 1) + ".- " + itemsMenu[i]);
                }
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > itemsMenu.length){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > itemsMenu.length);
        }catch(Exception e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            menuPrincipal(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion == 1){
            Utilitarios.limpiaPantalla();
            tipoAsiento(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion == 2){
            Utilitarios.limpiaPantalla();
            menuEditarEntradas(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion == 3){
            Utilitarios.limpiaPantalla();
            muestraPromociones(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion == 4){
            Utilitarios.limpiaPantalla();
            editaPromociones(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion == 5){
            Utilitarios.limpiaPantalla();
            impresionBoleta(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion == 6){
            Utilitarios.limpiaPantalla();
            listadoVentas(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion == 7){
            Utilitarios.limpiaPantalla();
            calculaIngresosTotales(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion >= 8){
            Utilitarios.limpiaPantalla();
            Utilitarios.despedida();
        }
    }
    
    public static void tipoAsiento(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        int opcion;
        String[] itemsMenu = new String[3];
        String zonaTeatro = "";
        
        opcion = 0;
        itemsMenu[0] = "ZONA A - PALCO";
        itemsMenu[1] = "ZONA B - TRIBUNA BAJA";
        itemsMenu[2] = "ZONA C - TRIBUNA ALTA";
        
        try{
            do{
                System.out.println("-- SELECCIONE EL TIPO DE ENTRADA --");
                System.out.println("");
                System.out.println("SELECCIONE UNA OPCION:");
                for(int i=0; i<itemsMenu.length; i++){
                    String txtOpcion = (i + 1) + ".- " + itemsMenu[i];
                    if(i == 0) txtOpcion += " ($15.000)";
                    if(i == 1) txtOpcion += " ($10.000)";
                    if(i == 2) txtOpcion += " ($7.000)";
                    System.out.println(txtOpcion);
                }
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > itemsMenu.length){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > itemsMenu.length);
        }catch(Exception e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            tipoAsiento(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion >=1 && opcion <= itemsMenu.length){
            zonaTeatro = itemsMenu[(opcion - 1)];
            Utilitarios.limpiaPantalla();
            seleccionaAsiento(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB);
        }
    }
    
    public static void seleccionaAsiento(int opcion, String zonaTeatro, int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);

        imprimeMapaAsientos(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB);
        
        System.out.println("(*) ASIENTOS VENDIDOS");
        if(asientoNoValido){
            System.out.println("-- EL ASIENTO "+asientoSeleccionado.toUpperCase()+" NO ES VALIDO --");
        }
        if(asientoOcupado){
            System.out.println("-- EL ASIENTO "+asientoSeleccionado.toUpperCase()+" YA ESTA VENDIDO --");
        }
        System.out.println("SELECCIONE EL ASIENTO QUE QUIERE COMPRAR");
        System.out.println("POR EJEMPLO: A2");
        
        asientoSeleccionado = teclado.nextLine();
        asientoNoValido = false;
        asientoOcupado = false;
        
        if(asientoSeleccionado.isEmpty() || asientoSeleccionado == null){
            Utilitarios.limpiaPantalla();
            seleccionaAsiento(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB);
        }
        
        String columnaAux = asientoSeleccionado.substring(0,1);
        String filaAux = asientoSeleccionado.substring(1,2);
        int filaAsiento = Integer.parseInt(filaAux);
        filaAsiento -= 1;
        int columnaAsiento = Utilitarios.buscaColumnaDesdeLetra(columnaAux, letrasColumnas);
        
        if(columnaAsiento < 0 || columnaAsiento > cantColumnas || filaAsiento < 0 || filaAsiento > cantFilas) asientoNoValido = true;
        else{
            switch(opcion){
                case 1 -> {
                    if(asientosPalco[filaAsiento][columnaAsiento] == 1) asientoOcupado = true;
                    else asientosPalco[filaAsiento][columnaAsiento] = 1;
                }
                case 2 -> {
                    if(asientosPA[filaAsiento][columnaAsiento] == 1) asientoOcupado = true;
                    else asientosPA[filaAsiento][columnaAsiento] = 1;
                }
                case 3 -> {
                    if(asientosPB[filaAsiento][columnaAsiento] == 1) asientoOcupado = true;
                    else asientosPB[filaAsiento][columnaAsiento] = 1;
                }
                default -> {
                }
            }
        }
        
        if(asientoNoValido || asientoOcupado){
            Utilitarios.limpiaPantalla();
            seleccionaAsiento(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB);
        }else{
            Utilitarios.limpiaPantalla();
            otrosDatosCompra(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB, asientoSeleccionado);
        }
    }
    
    public static void imprimeMapaAsientos(int opcion, String zonaTeatro, int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        String filaLetras = "  | ";
        String filaNumeros = "";
        
        if(opcion == 1){
            cantFilas = 2;
            cantColumnas = 10;
            separador = "-------------------------------------------";
        }
        if(opcion == 2){
            cantFilas = 5;
            cantColumnas = 12;
            separador = "---------------------------------------------------";
        }else if(opcion == 3){
            cantFilas = 3;
            cantColumnas = 8;
            separador = "-----------------------------------";
        }
        
        for(int i=0; i<cantColumnas; i++){
            filaLetras += letrasColumnas[i]+" | ";
        }
        
        hayAsientosVendidos = false;
        
        System.out.println("-- "+zonaTeatro+" --");
        System.out.println("");
        System.out.println(filaLetras);
        System.out.println(separador);
        
        for(int i=0; i<cantFilas; i++){
            filaNumeros = (i + 1)+" |";
            for(int j=0; j<cantColumnas; j++){
                if(opcion == 1){
                    if(asientosPalco[i][j] == 1){
                        filaNumeros += " * |";
                        hayAsientosVendidos = true;
                    }
                    else filaNumeros += "   |";
                }else if(opcion == 2){
                    if(asientosPA[i][j] == 1){
                        filaNumeros += " * |";
                        hayAsientosVendidos = true;
                    }
                    else filaNumeros += "   |";
                }else if(opcion == 3){
                    if(asientosPB[i][j] == 1){
                        filaNumeros += " * |";
                        hayAsientosVendidos = true;
                    }
                    else filaNumeros += "   |";
                }
            }
            System.out.println(filaNumeros);
            System.out.println(separador);
        }
    }
    
    public static void otrosDatosCompra(int opcion, String zonaTeatro, int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB, String asientoSeleccionado){
        Scanner teclado = new Scanner(System.in);
        String edad;
        int edadNumerica = 0;
        int precioEntrada = 15000;
        int descuento = 0;
        String rut;
        String nombre;
        
        if(opcion == 2) precioEntrada = 10000;
        else if(opcion == 3) precioEntrada = 7000;
        
        System.out.println("UBICACION: "+ zonaTeatro);
        System.out.println("ASIENTO: "+asientoSeleccionado.toUpperCase());
        
        if(nombreCliente.equals("")){
            System.out.println("");
            System.out.println("INGRESE SU NOMBRE:");
            nombre = teclado.nextLine();
            if(nombre.isEmpty()){
                Utilitarios.limpiaPantalla();
                System.out.println("-- ERROR: EL NOMBRE NO PUEDE ESTAR EN BLANCO --");
                System.out.println("-- INGRESE SUS DATOS NUEVAMENTE --");
                System.out.println("");
                otrosDatosCompra(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB, asientoSeleccionado);
            }
            nombreCliente = nombre;
        }else nombre = nombreCliente;
        
        if(rutCliente.equals("")){
            System.out.println("");
            System.out.println("INGRESE SU RUT SIN PUNTOS NI GUION:");
            System.out.println("POR EJEMPLO: 12345678K");
            rut = teclado.nextLine();
            if(!Utilitarios.validaRut(rut)){
                Utilitarios.limpiaPantalla();
                System.out.println("-- ERROR: EL RUT INGRERSADO NO ES VALIDO --");
                System.out.println("-- INGRESE SUS DATOS NUEVAMENTE --");
                System.out.println("");
                otrosDatosCompra(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB, asientoSeleccionado);
            }
            rutCliente = rut;
        }else rut = rutCliente;
        
        System.out.println("");
        System.out.println("INGRESE LA EDAD DEL USUARIO DE LA ENTRADA PARA POSIBLES DESCUENTOS:");
        edad = teclado.nextLine();

        if(!Utilitarios.validaEdad(edad)){
            Utilitarios.limpiaPantalla();
            System.out.println("-- ERROR: LA EDAD NO PUEDE SER MENOR A CINCO AÑOS (5) --");
            System.out.println("-- INGRESE SUS DATOS NUEVAMENTE --");
            System.out.println("");
            otrosDatosCompra(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB, asientoSeleccionado);
        }

        edadNumerica = Integer.parseInt(edad);
        
        int precioFinal = precioEntrada;
        if(edadNumerica < 24){
            descuento = 10;
            precioFinal = precioEntrada - (precioEntrada * descuento / 100);
        }
        if(edadNumerica > 60){
            descuento = 15;
            precioFinal = precioEntrada - (precioEntrada * descuento / 100);
        }
        
        Utilitarios.limpiaPantalla();
        resumenCompra(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB, asientoSeleccionado, precioEntrada, descuento, precioFinal, nombre, rut);
    }
    
    public static void resumenCompra(int opcion, String zonaTeatro, int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB, String asientoSeleccionado, int precioEntrada, int descuento, int precioFinal, String nombre, String rut){
        Scanner teclado = new Scanner(System.in);
        String otraCompra;
        String txtDescuento;
        otraCompra = "";
        
        String[] datosEntrada = new String[7];
        
        do{
            System.out.println("-- RESUMEN DE COMPRA --");
            System.out.println("UBICACION: "+ zonaTeatro);
            System.out.println("ASIENTO: "+asientoSeleccionado.toUpperCase());
            System.out.println("PRECIO ENTRADA: "+precioEntrada);
            if(descuento == 0){
                txtDescuento = "SIN DESCUENTO";
            }else txtDescuento = (descuento == 10)? "ESTUDIANTE - " : "TERCERA EDAD - ";
            txtDescuento += (descuento == 0)? "" : descuento+"%";
            System.out.println("DESCUENTO: "+txtDescuento);
            System.out.println("PRECIO FINAL: "+precioFinal);
            System.out.println("NOMBRE COMPRADOR: "+nombre.toUpperCase());
            System.out.println("RUT COMPRADOR: "+rut.toUpperCase());
               
            System.out.println("");
            System.out.println("DESEA COMPRAR UNA NUEVA ENTRADA (S/N)");
            otraCompra = teclado.nextLine();
            if(!("N".equals(otraCompra.toUpperCase()) || "S".equals(otraCompra.toUpperCase()))){
                Utilitarios.limpiaPantalla();
                System.out.println("-- OPCION NO VALIDA. SOLO SE PERMITE 'S' O 'N' --");
                System.out.println("");
            }
        }while(!("N".equals(otraCompra.toUpperCase()) || "S".equals(otraCompra.toUpperCase())));
        
        datosEntrada[0] = zonaTeatro;
        datosEntrada[1] = asientoSeleccionado.toUpperCase();
        datosEntrada[2] = String.valueOf(precioEntrada);
        datosEntrada[3] = txtDescuento;
        datosEntrada[4] = String.valueOf(precioFinal);
        datosEntrada[5] = nombre.toUpperCase();
        datosEntrada[6] = rut.toUpperCase();
        
        switch(zonaTeatro){
            case "ZONA A - PALCO":
                entradasPalco.put(asientoSeleccionado.toUpperCase(), datosEntrada);
                if(!entradasVendidasPalco.contains(rut.toUpperCase()+","+nombre.toUpperCase())) entradasVendidasPalco.add(rut.toUpperCase()+","+nombre.toUpperCase());
                break;
            case "ZONA B - TRIBUNA BAJA":
                entradasPB.put(asientoSeleccionado.toUpperCase(), datosEntrada);
                if(!entradasVendidasPB.contains(rut.toUpperCase()+","+nombre.toUpperCase())) entradasVendidasPB.add(rut.toUpperCase()+","+nombre.toUpperCase());
                break;
            case "ZONA C - TRIBUNA ALTA":
                entradasPA.put(asientoSeleccionado.toUpperCase(), datosEntrada);
                if(!entradasVendidasPA.contains(rut.toUpperCase()+","+nombre.toUpperCase())) entradasVendidasPA.add(rut.toUpperCase()+","+nombre.toUpperCase());
                break;
        }
        
        if("N".equals(otraCompra.toUpperCase())){
            nombreCliente = "";
            rutCliente = "";
            
            Utilitarios.limpiaPantalla();
            menuPrincipal(asientosPalco, asientosPA, asientosPB);
        }
        
        if("S".equals(otraCompra.toUpperCase())){
            Utilitarios.limpiaPantalla();
            tipoAsiento(asientosPalco, asientosPA, asientosPB);
        }
    }
    
    public static void menuEditarEntradas(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        int opcion = 0;
        
        try{
            do{
                System.out.println("SELECCIONE UNA OPCION");
                System.out.println("1.- EDITAR ENTRADAS");
                System.out.println("2.- ELIMINAR ENTRADAS");
                System.out.println("3.- VOLVER");
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > 3){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > 3);
        }catch(Exception e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            menuEditarEntradas(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion == 1){
            Utilitarios.limpiaPantalla();
            editarEntradas(asientosPalco, asientosPA, asientosPB);
        }
        if(opcion == 2){
            Utilitarios.limpiaPantalla();
            eliminarEntradas(asientosPalco, asientosPA, asientosPB);
        }
        if(opcion == 3){
            Utilitarios.limpiaPantalla();
            menuPrincipal(asientosPalco, asientosPA, asientosPB);
        }
    }
    
    public static void eliminarEntradas(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        if(entradasVendidasPalco.isEmpty() && entradasVendidasPA.isEmpty() && entradasVendidasPB.isEmpty()){
            System.out.println("ERROR: NO HAY VENTAS AUN");
            System.out.println("Para eliminar entradas, primero debe haber ventas");
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine();
        
            Utilitarios.limpiaPantalla();
            menuPrincipal(asientosPalco, asientosPA, asientosPB);
        }else{
            Utilitarios.limpiaPantalla();
            eliminaEntradas(asientosPalco, asientosPA, asientosPB);
        }
    }
    
    public static void eliminaEntradas(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        int opcion = 0;
        int opcionEntrada = 0;
        int auxIndex;
        String[] datosEntrada = null;
        String valorEntradaEliminar = "";
        String txtEntradaEliminar = "";
        
        ArrayList<String> rutsUnicos = new ArrayList<>();
        ArrayList<String> nombresUnicos = new ArrayList<>();
        ArrayList<String> datosUnicos = new ArrayList<>();
        
        ArrayList<String> zonaEliminar = new ArrayList<>();
        ArrayList<String> asientoEliminar = new ArrayList<>();
        ArrayList<String> nombreZonaEliminar = new ArrayList<>();
        
        for(String datos : entradasVendidasPalco){
            if(!datosUnicos.contains(datos)) datosUnicos.add(datos);
        }
        for(String datos : entradasVendidasPA){
            if(!datosUnicos.contains(datos)) datosUnicos.add(datos);
        }
        for(String datos : entradasVendidasPB){
            if(!datosUnicos.contains(datos)) datosUnicos.add(datos);
        }
        
        System.out.println("ELIMINAR ENTRADAS");
        System.out.println("");
        System.out.println("SELECCIONE LOS DATOS DEL COMPRADOR PARA ELIMINAR ENTRADAS:");
        int aux = 1;
        for(String DU : datosUnicos){
            String[] datosBoleta = DU.split(",");
            System.out.println("["+aux+"] RUT: "+Utilitarios.formatoRut(datosBoleta[0])+" * NOMBRE: "+datosBoleta[1]);
            rutsUnicos.add(datosBoleta[0]);
            nombresUnicos.add(datosBoleta[1]);
            aux++;
        }
        System.out.println("");
        
        try{
            do{
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > aux){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > aux);
        }catch(Exception e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            eliminaEntradas(asientosPalco, asientosPA, asientosPB);
        }
        
        String rutSeleccionado = rutsUnicos.get((opcion - 1));
        String nombreSeleccionado = nombresUnicos.get((opcion - 1));
        
        Utilitarios.limpiaPantalla();
        
        valorEntradaEliminar = rutSeleccionado+","+nombreSeleccionado;
        int cantidadEntradasPorRUT = 1;
        System.out.println("SELECCIONE LA ENTRADA QUE DESEA ELIMINAR PARA EL COMPRADOR");
        System.out.println(nombreSeleccionado+" * "+Utilitarios.formatoRut(rutSeleccionado));
        System.out.println("");
        
        for(String key : entradasPalco.keySet()){
            datosEntrada = entradasPalco.get(key);
            if(rutSeleccionado.equals(datosEntrada[6])){
                System.out.println("["+cantidadEntradasPorRUT+"] ZONA: "+datosEntrada[0]+"\tASIENTO: "+datosEntrada[1]);
                zonaEliminar.add("PALCO");
                asientoEliminar.add(datosEntrada[1]);
                nombreZonaEliminar.add(datosEntrada[0]);
                cantidadEntradasPorRUT += 1;
            }
        }
        for(String key : entradasPB.keySet()){
            datosEntrada = entradasPB.get(key);
            if(rutSeleccionado.equals(datosEntrada[6])){
                System.out.println("["+cantidadEntradasPorRUT+"] ZONA: "+datosEntrada[0]+"\tASIENTO: "+datosEntrada[1]);
                zonaEliminar.add("PB");
                asientoEliminar.add(datosEntrada[1]);
                nombreZonaEliminar.add(datosEntrada[0]);
                cantidadEntradasPorRUT += 1;
            }
        }
        for(String key : entradasPA.keySet()){
            datosEntrada = entradasPA.get(key);
            if(rutSeleccionado.equals(datosEntrada[6])){
                System.out.println("["+cantidadEntradasPorRUT+"] ZONA: "+datosEntrada[0]+"\tASIENTO: "+datosEntrada[1]);
                zonaEliminar.add("PA");
                asientoEliminar.add(datosEntrada[1]);
                nombreZonaEliminar.add(datosEntrada[0]);
                cantidadEntradasPorRUT += 1;
            }
        }
        
        try{
            do{
                opcionEntrada = teclado.nextInt();
                if(opcionEntrada < 1 || opcionEntrada > cantidadEntradasPorRUT){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcionEntrada < 1 || opcionEntrada > cantidadEntradasPorRUT);
        }catch(Exception e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            eliminaEntradas(asientosPalco, asientosPA, asientosPB);
        }
        
        cantidadEntradasPorRUT -= 1;
       
        if(zonaEliminar.get((opcionEntrada - 1)).equals("PALCO")){
            String auxAsientoEliminar = asientoEliminar.get((opcionEntrada - 1));
            txtEntradaEliminar = "ZONA:"+nombreZonaEliminar.get((opcionEntrada - 1))+"\tASIENTO: "+auxAsientoEliminar;
            entradasPalco.remove(auxAsientoEliminar);
            Utilitarios.cambiaValorArray(asientosPalco, 0, auxAsientoEliminar, letrasColumnas);
            if(cantidadEntradasPorRUT == 1){
                auxIndex = entradasVendidasPalco.indexOf(valorEntradaEliminar);
                entradasVendidasPalco.remove(auxIndex);
            }
        }
        if(zonaEliminar.get((opcionEntrada - 1)).equals("PA")){
            String auxAsientoEliminar = asientoEliminar.get((opcionEntrada - 1));
            txtEntradaEliminar = "ZONA:"+nombreZonaEliminar.get((opcionEntrada - 1))+"\tASIENTO: "+auxAsientoEliminar;
            entradasPA.remove(auxAsientoEliminar);
            Utilitarios.cambiaValorArray(asientosPA, 0, auxAsientoEliminar, letrasColumnas);
            if(cantidadEntradasPorRUT == 1){
                auxIndex = entradasVendidasPA.indexOf(valorEntradaEliminar);
                entradasVendidasPA.remove(auxIndex);
            }
        }
        if(zonaEliminar.get((opcionEntrada - 1)).equals("PB")){
            String auxAsientoEliminar = asientoEliminar.get((opcionEntrada - 1));
            txtEntradaEliminar = "ZONA:"+nombreZonaEliminar.get((opcionEntrada - 1))+"\tASIENTO: "+auxAsientoEliminar;
            entradasPB.remove(auxAsientoEliminar);
            Utilitarios.cambiaValorArray(asientosPB, 0, auxAsientoEliminar, letrasColumnas);
            if(cantidadEntradasPorRUT == 1){
                auxIndex = entradasVendidasPB.indexOf(valorEntradaEliminar);
                entradasVendidasPB.remove(auxIndex);
            }
        }

        Utilitarios.limpiaPantalla();
        System.out.println("");
        System.out.println("SE ELIMINO EXITOSAMENTE LA ENTRADA:");
        System.out.println(txtEntradaEliminar);
        System.out.println("");
        
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        menuPrincipal(asientosPalco, asientosPA, asientosPB);
    }
    
    public static void editarEntradas(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        if(entradasVendidasPalco.isEmpty() && entradasVendidasPA.isEmpty() && entradasVendidasPB.isEmpty()){
            System.out.println("ERROR: NO HAY VENTAS AUN");
            System.out.println("Para editar entradas, primero debe haber ventas");
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine();
        
            Utilitarios.limpiaPantalla();
            menuPrincipal(asientosPalco, asientosPA, asientosPB);
        }else{
            Utilitarios.limpiaPantalla();
            editaEntradas(asientosPalco, asientosPA, asientosPB);
        }
    }
    
    public static void editaEntradas(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        int opcion = 0;
        int opcionEntrada = 0;
        int auxIndex;
        String[] datosEntrada = null;
        String valorEntradaEditar = "";
        String txtEntradaEditar = "";
        String auxAsientoEditar = "";
        String nuevoAsiento = "";
        
        ArrayList<String> rutsUnicos = new ArrayList<>();
        ArrayList<String> nombresUnicos = new ArrayList<>();
        ArrayList<String> datosUnicos = new ArrayList<>();
        
        ArrayList<String> zonaEditar = new ArrayList<>();
        ArrayList<String> asientoEditar = new ArrayList<>();
        ArrayList<String> nombreZonaEditar = new ArrayList<>();
        
        for(String datos : entradasVendidasPalco){
            if(!datosUnicos.contains(datos)) datosUnicos.add(datos);
        }
        for(String datos : entradasVendidasPA){
            if(!datosUnicos.contains(datos)) datosUnicos.add(datos);
        }
        for(String datos : entradasVendidasPB){
            if(!datosUnicos.contains(datos)) datosUnicos.add(datos);
        }
        
        System.out.println("EDITAR ASIENTO");
        System.out.println("");
        System.out.println("SELECCIONE LOS DATOS DEL COMPRADOR PARA EDITAR EL ASIENTO:");
        int aux = 1;
        for(String DU : datosUnicos){
            String[] datosBoleta = DU.split(",");
            System.out.println("["+aux+"] RUT: "+Utilitarios.formatoRut(datosBoleta[0])+" * NOMBRE: "+datosBoleta[1]);
            rutsUnicos.add(datosBoleta[0]);
            nombresUnicos.add(datosBoleta[1]);
            aux++;
        }
        System.out.println("");
        
        try{
            do{
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > aux){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > aux);
        }catch(Exception e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            eliminaEntradas(asientosPalco, asientosPA, asientosPB);
        }
        
        String rutSeleccionado = rutsUnicos.get((opcion - 1));
        String nombreSeleccionado = nombresUnicos.get((opcion - 1));
        
        Utilitarios.limpiaPantalla();
        
        valorEntradaEditar = rutSeleccionado+","+nombreSeleccionado;
        int cantidadEntradasPorRUT = 1;
        System.out.println("SELECCIONE LA ENTRADA QUE DESEA EDITAR EL ASIENTO");
        System.out.println(nombreSeleccionado+" * "+Utilitarios.formatoRut(rutSeleccionado));
        System.out.println("");
        
        for(String key : entradasPalco.keySet()){
            datosEntrada = entradasPalco.get(key);
            if(rutSeleccionado.equals(datosEntrada[6])){
                System.out.println("["+cantidadEntradasPorRUT+"] ZONA: "+datosEntrada[0]+"\tASIENTO: "+datosEntrada[1]);
                zonaEditar.add("PALCO");
                asientoEditar.add(datosEntrada[1]);
                nombreZonaEditar.add(datosEntrada[0]);
                cantidadEntradasPorRUT += 1;
            }
        }
        for(String key : entradasPB.keySet()){
            datosEntrada = entradasPB.get(key);
            if(rutSeleccionado.equals(datosEntrada[6])){
                System.out.println("["+cantidadEntradasPorRUT+"] ZONA: "+datosEntrada[0]+"\tASIENTO: "+datosEntrada[1]);
                zonaEditar.add("PB");
                asientoEditar.add(datosEntrada[1]);
                nombreZonaEditar.add(datosEntrada[0]);
                cantidadEntradasPorRUT += 1;
            }
        }
        for(String key : entradasPA.keySet()){
            datosEntrada = entradasPA.get(key);
            if(rutSeleccionado.equals(datosEntrada[6])){
                System.out.println("["+cantidadEntradasPorRUT+"] ZONA: "+datosEntrada[0]+"\tASIENTO: "+datosEntrada[1]);
                zonaEditar.add("PA");
                asientoEditar.add(datosEntrada[1]);
                nombreZonaEditar.add(datosEntrada[0]);
                cantidadEntradasPorRUT += 1;
            }
        }
        
        try{
            do{
                opcionEntrada = teclado.nextInt();
                if(opcionEntrada < 1 || opcionEntrada > cantidadEntradasPorRUT){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcionEntrada < 1 || opcionEntrada > cantidadEntradasPorRUT);
        }catch(Exception e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            eliminaEntradas(asientosPalco, asientosPA, asientosPB);
        }
        
        cantidadEntradasPorRUT -= 1;
       
        if(zonaEditar.get((opcionEntrada - 1)).equals("PALCO")){
            auxAsientoEditar = asientoEditar.get((opcionEntrada - 1));
            txtEntradaEditar = "ZONA:"+nombreZonaEditar.get((opcionEntrada - 1));
            Utilitarios.limpiaPantalla();
            nuevoAsiento = buscaNuevoAsiento(1, nombreZonaEditar.get((opcionEntrada - 1)), asientosPalco, asientosPA, asientosPB);
            datosEntrada = entradasPalco.get(auxAsientoEditar);
            datosEntrada[1] = nuevoAsiento;
            entradasPalco.put(nuevoAsiento.toUpperCase(), datosEntrada);
            entradasPalco.remove(auxAsientoEditar.toUpperCase());
            Utilitarios.cambiaValorArray(asientosPalco, 0, auxAsientoEditar, letrasColumnas);
            Utilitarios.cambiaValorArray(asientosPalco, 1, nuevoAsiento, letrasColumnas);
        }
        if(zonaEditar.get((opcionEntrada - 1)).equals("PA")){
            auxAsientoEditar = asientoEditar.get((opcionEntrada - 1));
            txtEntradaEditar = "ZONA:"+nombreZonaEditar.get((opcionEntrada - 1));
            Utilitarios.limpiaPantalla();
            nuevoAsiento = buscaNuevoAsiento(2, nombreZonaEditar.get((opcionEntrada - 1)), asientosPalco, asientosPA, asientosPB);
            datosEntrada = entradasPA.get(auxAsientoEditar);
            datosEntrada[1] = nuevoAsiento;
            entradasPA.put(nuevoAsiento.toUpperCase(), datosEntrada);
            entradasPA.remove(auxAsientoEditar.toUpperCase());
            Utilitarios.cambiaValorArray(asientosPA, 0, auxAsientoEditar, letrasColumnas);
            Utilitarios.cambiaValorArray(asientosPA, 1, nuevoAsiento, letrasColumnas);
        }
        if(zonaEditar.get((opcionEntrada - 1)).equals("PB")){
            auxAsientoEditar = asientoEditar.get((opcionEntrada - 1));
            txtEntradaEditar = "ZONA:"+nombreZonaEditar.get((opcionEntrada - 1));
            Utilitarios.limpiaPantalla();
            nuevoAsiento = buscaNuevoAsiento(3, nombreZonaEditar.get((opcionEntrada - 1)), asientosPalco, asientosPA, asientosPB);
            datosEntrada = entradasPB.get(auxAsientoEditar);
            datosEntrada[1] = nuevoAsiento;
            entradasPB.put(nuevoAsiento.toUpperCase(), datosEntrada);
            entradasPB.remove(auxAsientoEditar.toUpperCase());
            Utilitarios.cambiaValorArray(asientosPB, 0, auxAsientoEditar, letrasColumnas);
            Utilitarios.cambiaValorArray(asientosPB, 1, nuevoAsiento, letrasColumnas);
        }
        
        Utilitarios.limpiaPantalla();
        System.out.println("");
        System.out.println("SE CAMBIO EXITOSAMENTE EL ASIENTO PARA LA ENTRADA:");
        System.out.println(txtEntradaEditar);
        System.out.println("ASIENTO ANTIGUO: "+auxAsientoEditar);
        System.out.println("NUEVO ASIENTO:   "+nuevoAsiento);
        System.out.println("");
        
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        menuPrincipal(asientosPalco, asientosPA, asientosPB);
    }
    
    public static String buscaNuevoAsiento(int opcion, String zonaTeatro, int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);

        imprimeMapaAsientos(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB);
        
        System.out.println("(*) ASIENTOS VENDIDOS");
        if(asientoNoValido){
            System.out.println("-- EL ASIENTO "+asientoSeleccionado.toUpperCase()+" NO ES VALIDO --");
        }
        if(asientoOcupado){
            System.out.println("-- EL ASIENTO "+asientoSeleccionado.toUpperCase()+" YA ESTA VENDIDO --");
        }
        System.out.println("SELECCIONE EL ASIENTO QUE QUIERE COMPRAR");
        System.out.println("POR EJEMPLO: A2");
        
        asientoSeleccionado = teclado.nextLine();
        asientoNoValido = false;
        asientoOcupado = false;
        
        if(asientoSeleccionado.isEmpty() || asientoSeleccionado == null){
            Utilitarios.limpiaPantalla();
            seleccionaAsiento(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB);
        }
        
        String columnaAux = asientoSeleccionado.substring(0,1);
        String filaAux = asientoSeleccionado.substring(1,2);
        int filaAsiento = Integer.parseInt(filaAux);
        filaAsiento -= 1;
        int columnaAsiento = Utilitarios.buscaColumnaDesdeLetra(columnaAux, letrasColumnas);
        
        if(columnaAsiento < 0 || columnaAsiento > cantColumnas || filaAsiento < 0 || filaAsiento > cantFilas) asientoNoValido = true;
        else{
            switch(opcion){
                case 1 -> {
                    if(asientosPalco[filaAsiento][columnaAsiento] == 1) asientoOcupado = true;
                    else asientosPalco[filaAsiento][columnaAsiento] = 1;
                }
                case 2 -> {
                    if(asientosPA[filaAsiento][columnaAsiento] == 1) asientoOcupado = true;
                    else asientosPA[filaAsiento][columnaAsiento] = 1;
                }
                case 3 -> {
                    if(asientosPB[filaAsiento][columnaAsiento] == 1) asientoOcupado = true;
                    else asientosPB[filaAsiento][columnaAsiento] = 1;
                }
                default -> {
                }
            }
        }
        
        if(asientoNoValido || asientoOcupado){
            Utilitarios.limpiaPantalla();
            buscaNuevoAsiento(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB);
        }
        
        return asientoSeleccionado.toUpperCase();
    }
    
    public static void muestraPromociones(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        String correccionEspacios;
        
        tipoEntradas[0] = "ZONA A - PALCO";
        tipoEntradas[1] = "ZONA B - TRIBUNA BAJA";
        tipoEntradas[2] = "ZONA C - TRIBUNA ALTA";
        
        precioEntradas[0] = "$15.000";
        precioEntradas[1] = "$10.000";
        precioEntradas[2] = "$ 7.000";
        
        Utilitarios.limpiaPantalla();
        System.out.println("-- PRECIOS DE ENTRADAS --");
        
        for(int i=0; i<tipoEntradas.length; i++){
            correccionEspacios = (i == 0)? ":        " : ": ";
            System.out.println(tipoEntradas[i]+correccionEspacios+precioEntradas[i]);
        }
        
        System.out.println("");
        System.out.println("-- PROMOCIONES DISPONIBLES --");
        for(int i=0; i<descuentos.size(); i++){
            System.out.println("DESCUENTO "+nombreDescuentos.get(i)+":\t"+descuentos.get(i)+"%");
        }
        System.out.println("* Para acceder al descuento de 3ra edad, debe ser mayor de "+edadTerceraEdad+" años");
        
        System.out.println("");
        
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
       
        Utilitarios.limpiaPantalla();
        menuPrincipal(asientosPalco, asientosPA, asientosPB);
    }
    
    public static void editaPromociones(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        int i = 0;
        
        try{
            do{
                System.out.println("-- SELECCIONE EL CAMPO A EDITAR --");
                for(i=0; i<descuentos.size(); i++){
                    System.out.println((i + 1)+".- DESCUENTO "+nombreDescuentos.get(i)+":\t"+descuentos.get(i)+"%");
                }
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > descuentos.size()){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > descuentos.size());
        }catch(Exception e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            editaPromociones(asientosPalco, asientosPA, asientosPB);
        }
        
        int nuevoDescuento = 0;
        
        try{
            do{
                System.out.println("INGRESE EL NUEVO PORCENTAJE DE DESCUENTO PARA "+nombreDescuentos.get((opcion - 1)));
                nuevoDescuento = teclado.nextInt();
                if(nuevoDescuento < 1 || nuevoDescuento > 100){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- EL DESCUENTO DEBE ESTAR ENTRE 1 Y 99 --");
                    System.out.println("");
                }
            }while(nuevoDescuento < 1 || nuevoDescuento > 100);
        }catch(Exception e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            editaPromociones(asientosPalco, asientosPA, asientosPB);
        }
        
        descuentos.set((opcion - 1), nuevoDescuento);
        
        Utilitarios.limpiaPantalla();
        System.out.println("-- LOS NUEVOS DESCUENTOS SON: --");
        for(i=0; i<descuentos.size(); i++){
            System.out.println("DESCUENTO "+nombreDescuentos.get(i)+":\t"+descuentos.get(i)+"%");
        }
        
        System.out.println("");
        
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();teclado.nextLine();
       
        Utilitarios.limpiaPantalla();
        menuPrincipal(asientosPalco, asientosPA, asientosPB);
    }
    
    public static void impresionBoleta(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        if(entradasVendidasPalco.isEmpty() && entradasVendidasPA.isEmpty() && entradasVendidasPB.isEmpty()){
            System.out.println("ERROR: NO HAY VENTAS AUN");
            System.out.println("Para imprimir boletas, primero debe haber ventas de entradas");
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine();
        
            Utilitarios.limpiaPantalla();
            menuPrincipal(asientosPalco, asientosPA, asientosPB);
        }else{
            Utilitarios.limpiaPantalla();
            imprimeBoleta(asientosPalco, asientosPA, asientosPB);
        }
    }
    
    public static void imprimeBoleta(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        int nroBoleta = Utilitarios.generarNroBoleta(1000, 9999);
        int hashBoleta = Utilitarios.generarNroBoleta(100000, 999999);
        int opcion = 0;
        int espaciosAdicionales = 0;
        
        int totalBoleta = 0;
        
        String[] datosEntrada = null;
        String txtDescuentoEntrada = "";
        String txtTotalEntrada = "";
        String txtCodigoBarras = "";
        
        ArrayList<String> rutsUnicos = new ArrayList<>();
        ArrayList<String> nombresUnicos = new ArrayList<>();
        ArrayList<String> datosUnicos = new ArrayList<>();
        for(String datos : entradasVendidasPalco){
            if(!datosUnicos.contains(datos)) datosUnicos.add(datos);
        }
        for(String datos : entradasVendidasPA){
            if(!datosUnicos.contains(datos)) datosUnicos.add(datos);
        }
        for(String datos : entradasVendidasPB){
            if(!datosUnicos.contains(datos)) datosUnicos.add(datos);
        }
        
        System.out.println("IMPRESION DE BOLETAS");
        System.out.println("");
        System.out.println("SELECCIONE LOS DATOS DEL COMPRADOR PARA IMPRIMIR SU BOLETA:");
        int aux = 1;
        for(String DU : datosUnicos){
            String[] datosBoleta = DU.split(",");
            System.out.println("["+aux+"] RUT: "+Utilitarios.formatoRut(datosBoleta[0])+" * NOMBRE: "+datosBoleta[1]);
            rutsUnicos.add(datosBoleta[0]);
            nombresUnicos.add(datosBoleta[1]);
            aux++;
        }
        System.out.println("");
        
        try{
            do{
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > aux){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > aux);
        }catch(Exception e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            imprimeBoleta(asientosPalco, asientosPA, asientosPB);
        }
        
        String rutSeleccionado = rutsUnicos.get((opcion - 1));
        String nombreSeleccionado = nombresUnicos.get((opcion - 1));
                
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String txtHoy = hoy.format(formatter);
        
        Utilitarios.limpiaPantalla();
        
        System.out.println("********************************************************************************************");
        System.out.println("*    TEATRO MORO SA                                                                        *");
        System.out.println("*   RUT "+Utilitarios.formatoRut("732767800")+"                                                       --------------- *");
        System.out.println("*  AV TOMAS MORO 1285                                                      | BOLETA "+nroBoleta+" | *");
        System.out.println("* LAS CONDES. SANTIAGO                                                     --------------- *");
        System.out.println("* TEL: +56 2 2233 4455                                                                     *");
        System.out.println("********************************************************************************************");
        System.out.println("FECHA:        "+txtHoy);
        System.out.println("RAZON SOCIAL: "+nombreSeleccionado);
        System.out.println("RUT:          "+Utilitarios.formatoRut(rutSeleccionado));
        System.out.println("********************************************************************************************");
        System.out.println("CANT  DESCRIPCION                                                         PRECIO   DESCUENTO");
        
        NumberFormat precio = NumberFormat.getInstance(new Locale("es", "CL"));
        precio.setGroupingUsed(true);
        
        for(String key : entradasPalco.keySet()){
            datosEntrada = entradasPalco.get(key);
            if(rutSeleccionado.equals(datosEntrada[6])){
                txtDescuentoEntrada = "";
                
                String descripcionEntrada = "ENTRADA "+datosEntrada[0]+" (ASIENTO: "+datosEntrada[1]+"). "+datosEntrada[3];
                espaciosAdicionales = 43 - datosEntrada[0].length() - datosEntrada[3].length();
                for(int i=0; i<espaciosAdicionales; i++) descripcionEntrada += " ";
                
                String precioEntrada = (Integer.parseInt(datosEntrada[2]) < 10000)? "$ "+precio.format(Integer.parseInt(datosEntrada[2])) : "$"+precio.format(Integer.parseInt(datosEntrada[2]));
                String descuentoEntrada = precio.format(Integer.parseInt(datosEntrada[2]) - Integer.parseInt(datosEntrada[4]));
                espaciosAdicionales = 11 - descuentoEntrada.length();
                for(int i=0; i<espaciosAdicionales; i++) txtDescuentoEntrada += " ";
                txtDescuentoEntrada += "$"+descuentoEntrada;

                totalBoleta += Integer.parseInt(datosEntrada[4]);
                
                System.out.println("   1  "+descripcionEntrada+precioEntrada+txtDescuentoEntrada);
            }
        }
        for(String key : entradasPB.keySet()){
            datosEntrada = entradasPB.get(key);
            if(rutSeleccionado.equals(datosEntrada[6])){
                txtDescuentoEntrada = "";
                
                String descripcionEntrada = "ENTRADA "+datosEntrada[0]+" (ASIENTO: "+datosEntrada[1]+"). "+datosEntrada[3];
                espaciosAdicionales = 43 - datosEntrada[0].length() - datosEntrada[3].length();
                for(int i=0; i<espaciosAdicionales; i++) descripcionEntrada += " ";
                
                String precioEntrada = (Integer.parseInt(datosEntrada[2]) < 10000)? "$ "+precio.format(Integer.parseInt(datosEntrada[2])) : "$"+precio.format(Integer.parseInt(datosEntrada[2]));
                String descuentoEntrada = precio.format(Integer.parseInt(datosEntrada[2]) - Integer.parseInt(datosEntrada[4]));
                espaciosAdicionales = 11 - descuentoEntrada.length();
                for(int i=0; i<espaciosAdicionales; i++) txtDescuentoEntrada += " ";
                txtDescuentoEntrada += "$"+descuentoEntrada;

                totalBoleta += Integer.parseInt(datosEntrada[4]);
                
                System.out.println("   1  "+descripcionEntrada+precioEntrada+txtDescuentoEntrada);
            }
        }
        for(String key : entradasPA.keySet()){
            datosEntrada = entradasPA.get(key);
            if(rutSeleccionado.equals(datosEntrada[6])){
                txtDescuentoEntrada = "";
                
                String descripcionEntrada = "ENTRADA "+datosEntrada[0]+" (ASIENTO: "+datosEntrada[1]+"). "+datosEntrada[3];
                espaciosAdicionales = 43 - datosEntrada[0].length() - datosEntrada[3].length();
                for(int i=0; i<espaciosAdicionales; i++) descripcionEntrada += " ";
                
                String precioEntrada = (Integer.parseInt(datosEntrada[2]) < 10000)? "$ "+precio.format(Integer.parseInt(datosEntrada[2])) : "$"+precio.format(Integer.parseInt(datosEntrada[2]));
                String descuentoEntrada = precio.format(Integer.parseInt(datosEntrada[2]) - Integer.parseInt(datosEntrada[4]));
                espaciosAdicionales = 11 - descuentoEntrada.length();
                for(int i=0; i<espaciosAdicionales; i++) txtDescuentoEntrada += " ";
                txtDescuentoEntrada += "$"+descuentoEntrada;

                totalBoleta += Integer.parseInt(datosEntrada[4]);
                
                System.out.println("   1  "+descripcionEntrada+precioEntrada+txtDescuentoEntrada);
            }
        }

        String totalEntrada = precio.format(totalBoleta);
        espaciosAdicionales = 91 - totalEntrada.length();
        for(int i=0; i<espaciosAdicionales; i++) txtTotalEntrada += " ";
        txtTotalEntrada += "$"+totalEntrada;
        
        String codigoBarra = Utilitarios.codBarras(String.valueOf(hashBoleta));
        espaciosAdicionales = 31;
        for(int i=0; i<espaciosAdicionales; i++) txtCodigoBarras += " ";
        txtCodigoBarras += codigoBarra;
        
        System.out.println("");
        System.out.println("                                                                                       TOTAL");
        System.out.println(txtTotalEntrada);
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(txtCodigoBarras);
        
        System.out.println("");
        
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        menuPrincipal(asientosPalco, asientosPA, asientosPB);
    }
    
    public static void listadoVentas(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        if(entradasVendidasPalco.isEmpty() && entradasVendidasPA.isEmpty() && entradasVendidasPB.isEmpty()){
            System.out.println("NO HAY VENTAS AUN");
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine();
        
            Utilitarios.limpiaPantalla();
            menuPrincipal(asientosPalco, asientosPA, asientosPB);
        }else{
            Utilitarios.limpiaPantalla();
            listarTodasLasVentas(asientosPalco, asientosPA, asientosPB);
        }
    }
    
    public static void listarTodasLasVentas(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        String[] datosEntrada = null;
        
        tipoEntradas[0] = "ZONA A - PALCO";
        tipoEntradas[1] = "ZONA B - TRIBUNA BAJA";
        tipoEntradas[2] = "ZONA C - TRIBUNA ALTA";
        
        NumberFormat precio = NumberFormat.getInstance(new Locale("es", "CL"));
        precio.setGroupingUsed(true);
        
        System.out.println("LISTADO DE VENTAS");
        System.out.println("");
        
        System.out.println("===  VENTAS "+tipoEntradas[0]+" ===");
        if(entradasPalco.isEmpty()){
            System.out.println("SIN VENTAS");
            System.out.println("-------------------------------------");
        }
        for(String key : entradasPalco.keySet()){
            datosEntrada = entradasPalco.get(key);
            
            String descuentoEntrada = precio.format(Integer.parseInt(datosEntrada[2]) - Integer.parseInt(datosEntrada[4]));
            
            System.out.println("ZONA         : "+datosEntrada[0]+" (ASIENTO "+datosEntrada[1]+")");
            System.out.println("PRECIO NORMAL: $"+precio.format(Integer.parseInt(datosEntrada[2])));
            System.out.println("DESCUENTO    : $"+descuentoEntrada);
            System.out.println("PRECIO PAGADO: $"+precio.format(Integer.parseInt(datosEntrada[4])));
            System.out.println("-------------------------------------");
        }
        
        System.out.println("===  VENTAS "+tipoEntradas[1]+" ===");
        if(entradasPB.isEmpty()){
            System.out.println("SIN VENTAS");
            System.out.println("-------------------------------------");
        }
        for(String key : entradasPB.keySet()){
            datosEntrada = entradasPB.get(key);
            
            String descuentoEntrada = precio.format(Integer.parseInt(datosEntrada[2]) - Integer.parseInt(datosEntrada[4]));
            
            System.out.println("ZONA         : "+datosEntrada[0]+" (ASIENTO "+datosEntrada[1]+")");
            System.out.println("PRECIO NORMAL: $"+precio.format(Integer.parseInt(datosEntrada[2])));
            System.out.println("DESCUENTO    : $"+descuentoEntrada);
            System.out.println("PRECIO PAGADO: $"+precio.format(Integer.parseInt(datosEntrada[4])));
            System.out.println("-------------------------------------");
        }
        
        System.out.println("===  VENTAS "+tipoEntradas[2]+" ===");
        if(entradasPA.isEmpty()){
            System.out.println("SIN VENTAS");
            System.out.println("-------------------------------------");
        }
        for(String key : entradasPA.keySet()){
            datosEntrada = entradasPA.get(key);
            
            String descuentoEntrada = precio.format(Integer.parseInt(datosEntrada[2]) - Integer.parseInt(datosEntrada[4]));
            
            System.out.println("ZONA         : "+datosEntrada[0]+" (ASIENTO "+datosEntrada[1]+")");
            System.out.println("PRECIO NORMAL: $"+precio.format(Integer.parseInt(datosEntrada[2])));
            System.out.println("DESCUENTO    : $"+descuentoEntrada);
            System.out.println("PRECIO PAGADO: $"+precio.format(Integer.parseInt(datosEntrada[4])));
            System.out.println("-------------------------------------");
        }
        
        System.out.println("");
        
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        menuPrincipal(asientosPalco, asientosPA, asientosPB);
    }
    
    public static void calculaIngresosTotales(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        if(entradasVendidasPalco.isEmpty() && entradasVendidasPA.isEmpty() && entradasVendidasPB.isEmpty()){
            System.out.println("NO HAY VENTAS AUN");
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine();
        
            Utilitarios.limpiaPantalla();
            menuPrincipal(asientosPalco, asientosPA, asientosPB);
        }else{
            Utilitarios.limpiaPantalla();
            calcularIngresosTotales(asientosPalco, asientosPA, asientosPB);
        }
    }
    
    public static void calcularIngresosTotales(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        String[] datosEntrada = null;
        
        tipoEntradas[0] = "ZONA A - PALCO";
        tipoEntradas[1] = "ZONA B - TRIBUNA BAJA";
        tipoEntradas[2] = "ZONA C - TRIBUNA ALTA";
        
        int totalIngresos = 0;
        int totalIngresosPalco = 0;
        int totalIngresosPA = 0;
        int totalIngresosPB = 0;
        
        NumberFormat precio = NumberFormat.getInstance(new Locale("es", "CL"));
        precio.setGroupingUsed(true);
        
        System.out.println("INGRESOS TOTALES POR VENTA DE ENTRADAS");
        System.out.println("");
        
       for(String key : entradasPalco.keySet()){
            datosEntrada = entradasPalco.get(key);
            totalIngresosPalco += Integer.parseInt(datosEntrada[4]);
        }
        for(String key : entradasPB.keySet()){
            datosEntrada = entradasPB.get(key);
            totalIngresosPB += Integer.parseInt(datosEntrada[4]);
        }
        for(String key : entradasPA.keySet()){
            datosEntrada = entradasPA.get(key);
            totalIngresosPA += Integer.parseInt(datosEntrada[4]);
        }
        
        totalIngresos = totalIngresosPalco + totalIngresosPA + totalIngresosPB;
        
        System.out.println("TOTAL INGRESOS "+tipoEntradas[0]+"\t\t$"+precio.format(totalIngresosPalco));
        System.out.println("TOTAL INGRESOS "+tipoEntradas[1]+"\t$"+precio.format(totalIngresosPB));
        System.out.println("TOTAL INGRESOS "+tipoEntradas[2]+"\t$"+precio.format(totalIngresosPA));
        System.out.println("");
        System.out.println("TOTAL INGRESOS \t\t\t\t$"+precio.format(totalIngresos));
        System.out.println("-------------------------------------");
        System.out.println("");
        
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        menuPrincipal(asientosPalco, asientosPA, asientosPB);
    }
}

class Utilitarios{
    public static void limpiaPantalla(){
        for(int i=0; i<30; i++){
            System.out.println("");
        }
    }
    
    public static String codBarras(String nroBoleta){
        String barra = "";
        for(int i=0; i<nroBoleta.length(); i++) {
            char digit = nroBoleta.charAt(i);
            barra += digitoABarra(digit);
        }
        return barra;
    }
    
    public static String digitoABarra(char digito){
        return switch (digito) {
            case '0' -> "|||||";
            case '1' -> ":||||";
            case '2' -> "::|||";
            case '3' -> ":::||";
            case '4' -> "::::|";
            case '5' -> ":::::";
            case '6' -> "|::::";
            case '7' -> "||:::";
            case '8' -> "|||::";
            default -> "||||:";
        };
    }
    
    public static int generarNroBoleta(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    public static String formatoRut(String rut){
        int R = Integer.parseInt(rut.substring(0, rut.length() - 1));
        String DV = rut.substring(rut.length() - 1, rut.length());
        
        NumberFormat formato = NumberFormat.getInstance(new Locale("es", "CL"));
        formato.setGroupingUsed(true);
        return formato.format(R)+"-"+DV;
    }
    
    public static void despedida(){
        System.out.println("GRACIAS POR UTILIZAR LAS HERRAMIENTAS DE ADMINISTRACION DEL TEATRO MORO");
    }
    
    public static Boolean validaEdad(String edad){
        int edadNum;
        
        if(edad == null || edad.isEmpty()) return false;
        
        try{
            edadNum = Integer.parseInt(edad);
        }catch(NumberFormatException e){
            return false;
        }
        
        return (edadNum >= 5);
    }
    
    public static Boolean validaRut(String rut){
        int rutNum;
        if(rut == null || rut.isEmpty() || rut.length() <= 1) return false;
        
        String R = rut.substring(0, rut.length() - 1);
        String DV = rut.substring(rut.length() - 1, rut.length());
        
        try{
            rutNum = Integer.parseInt(R);
        }catch(NumberFormatException e){
            return false;
        }
        
        return DV.toLowerCase().equals(dv(R));
    }
    
    public static String dv(String rut){
        Integer M=0,S=1,T=Integer.parseInt(rut);
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        
        return ( S > 0 ) ? String.valueOf(S-1) : "k";		
    }
    
    public static int buscaColumnaDesdeLetra(String letra, String[] letrasColumnas){
        int pos = -1;
        for(int i=0; i<letrasColumnas.length; i++){
            if(letra.toUpperCase().equals(letrasColumnas[i])){
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    public static void cambiaValorArray(int[][] asientos, int nuevoValor, String asientoSeleccionado, String[] letrasColumnas){
        String columnaAux = asientoSeleccionado.substring(0,1);
        String filaAux = asientoSeleccionado.substring(1,2);
        int filaAsiento = Integer.parseInt(filaAux);
        filaAsiento -= 1;
        int columnaAsiento = buscaColumnaDesdeLetra(columnaAux, letrasColumnas);
        
        asientos[filaAsiento][columnaAsiento] = nuevoValor;
    }
}