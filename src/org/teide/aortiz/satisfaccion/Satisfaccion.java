/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teide.aortiz.satisfaccion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author antonio
 */
public class Satisfaccion {
    
    private String date, name;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Satisfaccion s = new Satisfaccion();
            ArrayList<DataBean> al = s.obtainResults("/Users/antonio/Downloads/encuestas2/1EI.txt");
            for (DataBean dataBean : al) {
                for (String string : dataBean.getResp()) {
                    System.out.print(string+" ");
                }
                System.out.println("");
                for (String string : dataBean.getKnow()) {
                    System.out.print(string+" ");
                }
                System.out.println("");
                System.out.println("SEX: "+dataBean.getSex());
                for (String string : dataBean.getOpinion()) {
                    System.out.print(string+ " ");
                }
                System.out.println("");
            }
            System.out.println("Nombre: "+s.name);
            System.out.println("Fecha: "+s.date);
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Este método obtiene los resultados de cada alumnos, devolverá un listado de tipo DataBean por cada alumno
     * @param inputCSVfilename ruta completa del fichero CVS extraído de Moodle
     * @return el listado de valores de los usuarios de un curso
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private ArrayList<DataBean> obtainResults (String inputCSVfilename) throws FileNotFoundException, IOException {
        ArrayList<DataBean> al = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(inputCSVfilename)));
        String line;
        boolean obtainDateAndName = true;
        
        //La primera línea no hace falta que la leamos porque lleva las cabeceras
        br.readLine();
        while ((line=br.readLine())!=null) {
            String[] results = line.split("\t");
            //Para el primer alumno obtendremos la fecha y el nombre del grupo
            if (obtainDateAndName) {
                date = results[FieldsValues.DATE_FIELD].split(" ")[0];
                name = results[FieldsValues.NAME_FIELD];
                obtainDateAndName = false;
            }
            //Obtenemos todos las respuestas del usuario
            String[] resp = getResp(results);
            String sex = getSex(results);
            String[] know = getKnow(results);
            String[] opinion = getOpinion(results);
            
            DataBean db = new DataBean();
            db.setResp(resp);
            db.setSex(sex);
            db.setKnow(know);
            db.setOpinion(opinion);
            
            al.add(db);
        }
        
        //Prueba impresión
        transformAll(al);
        
        return al;
    }
    
    /**
     * Permite extraer las respuestas de un usuario
     * @param values representa una respuesta completa de un usuario
     * @return todas las respuestas de satisfacción e importancia del usuario
     */
    private String[] getResp (String[] values) {
        return Arrays.copyOfRange(values, FieldsValues.START_RESP, FieldsValues.END_RESP);
    }
    
    /**
     * Permite extraer el sexo de un usuario
     * @param values representa una respuesta completa de un usuario
     * @return el sexo del usuario, 1 hombre 2 mujer
     */
    private String getSex (String[] values) {
        return values[FieldsValues.SEX].split(" ")[0];
    }
    
    /**
     * Permite extraer los conocimientos de un usuario
     * @param values representa una respuesta completa de un usuario
     * @return los conocimientos del usuario (como conoció el centro y como se considera como estudiante).
     */
    private String[] getKnow (String[] values) {
        return Arrays.copyOfRange(values, FieldsValues.START_KNOW, FieldsValues.END_KNOW);
    }
    
    /**
     * Permite extraer la opinión de un usuario
     * @param values representa una respuesta completa de un usuario
     * @return las opiniones del usuario (aspectos de mejora y que le agradan al alumno). Máximo 3 por cada
     */
    private String[] getOpinion (String[] values) {
        return Arrays.copyOfRange(values, FieldsValues.START_OPINION, FieldsValues.END_OPINION);
    }
    
    /**
     * Permite transformar el listado de respuestas del usuario y asignarlo a un int[][] 
     * @param userValues el listado de respuestas del usuario en formato int[][] para llevarlo a la Excel
     * @param values el listado de respuestas de un usuario
     * @return el listado de respuestas en formato para pasarlo a la Excel
     */
    private void transformResp (int[][] userValues, String[] resp) {
        int row = 0;
        for (int i=0;i<resp.length;i++) {
            if (i%2==0) {
                //Escribimos la satisfacción
                userValues[row][FieldsValues.SATISFACTION_COLS[Integer.parseInt(resp[i])]]++;
                userValues[row][FieldsValues.SATISFACTION_COL]++;
            }
            else {
                //Escribimos la importancia
                userValues[row][FieldsValues.IMPORTANCE_COLS[Integer.parseInt(resp[i])]]++;
                userValues[row++][FieldsValues.IMPORTANCE_COL]++;
            }
        }
    }
    
    /**
     * Permite transformar el sexo de un usuario y asignarlo a un int[] 
     * @param userValuesel listado de sexo del usuario en formato in[] para llevarlo a la Excel
     * @param sex el sexo del usuario
     */
    private void transformSex (int[] userValues, String sex) {
        if (sex.equals(FieldsValues.MAN_VALUE)) userValues[Integer.parseInt(FieldsValues.MAN_VALUE)-1]++;
        else userValues[Integer.parseInt(FieldsValues.WOMAN_VALUE)-1]++;
    }
    
    /**
     * Permite transformar la opinión de un usuario y asignarlo a dos int[], uno con la información de cómo comoció el centro y otro como se valora como estudiante 
     * @param userValueKnow cómo conoció el centro
     * @param userValueStudent cómo se valora como estudiante
     * @param resp listado de respuestas del usuario
     */
    private void transformKnow (int[] userValueKnow, int[] userValueStudent, String[] resp) {
        //Primero tratamos como conoció el centro
        userValueKnow[Integer.parseInt(resp[0].split(" ")[0])-1]++;
        //Después tratamos como se valora como estudiante
        userValueStudent[Integer.parseInt(resp[2].split(" ")[0])-1]++;
    }
    
    /**
     * Permite transformar las preguntas abiertas del usuario
     * @param userValuesBetter aspectos a mejorar del centro
     * @param userValuesPlease aspectos que le agradan
     * @param resp respuestas del usuario
     */
    private void transformOpinion (ArrayList<DataOrderBean> userValuesBetter, ArrayList<DataOrderBean> userValuesPlease, String[] resp) {
        for (int i = 0; i < resp.length; i++) {
            //Aspectos a mejorar
            if (i<3) {
                if (!resp[i].trim().isEmpty()) {
                    if (userValuesBetter.contains(resp[i].trim())) userValuesBetter.get(userValuesBetter.indexOf(resp[i].trim())).increment();
                    else userValuesBetter.add(new DataOrderBean(resp[i].trim()));
                }
            }
            else {
                if (!resp[i].trim().isEmpty()) {
                    if (userValuesPlease.contains(resp[i].trim())) userValuesPlease.get(userValuesPlease.indexOf(resp[i].trim())).increment();
                    else userValuesPlease.add(new DataOrderBean(resp[i].trim()));
                }
            }
        }
    }
    
    /**
     * Transforma todas las respuestas en los valores necesarios para su paso a la Excel
     * @param values listado de valores de los usuarios
     */
    private void transformAll (ArrayList<DataBean> values) {
        int[][] userResp = new int[FieldsValues.USER_VALUES_ROWS][FieldsValues.USER_VALUES_COLS];
        int[] sex = new int[2];
        int[] centerKnow = new int[FieldsValues.CENTER_VALUES];
        int[] studentKnow = new int[FieldsValues.STUDENT_VALUES];
        ArrayList<DataOrderBean> alBetter = new ArrayList<>();
        ArrayList<DataOrderBean> alPlease = new ArrayList<>();
        for (DataBean value : values) {
            transformResp(userResp, value.getResp());
            transformSex(sex, value.getSex());
            transformKnow(centerKnow, studentKnow, value.getKnow());
            transformOpinion(alBetter, alPlease, value.getOpinion());
        }
        
        //Imprimimos
        for (int i = 0; i < userResp.length; i++) {
            for (int j = 0; j < userResp[i].length; j++) {
                System.out.print(userResp[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("--------------------------------");
        
        System.out.println("SEX: "+sex[0]+" - "+sex[1]);
        System.out.println("Center: ");
        for (int i = 0; i < centerKnow.length; i++) {
            System.out.print(centerKnow[i]+" ");
        }
        System.out.println("-------------------------------");
        
        System.out.println("Student");
        for (int i = 0; i < studentKnow.length; i++) {
            System.out.print(studentKnow[i]+" ");
        }
        System.out.println("-------------------------------");
        
        for (DataOrderBean dataOrderBean : alBetter) {
            System.out.println(dataOrderBean.getResp()+" - "+dataOrderBean.getnResp());
        }
        System.out.println("-------------------------------");
        for (DataOrderBean dataOrderBean : alPlease) {
            System.out.println(dataOrderBean.getResp()+" - "+dataOrderBean.getnResp());
        }
    }
    
}
