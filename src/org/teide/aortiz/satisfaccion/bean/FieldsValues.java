/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teide.aortiz.satisfaccion.bean;

/**
 *
 * @author antonio
 */
public interface FieldsValues {
    
    public static final int DATE_FIELD = 0;
    public static final int NAME_FIELD = 4;
    public static final int START_RESP = 4; //Nueva versión TEIDE 2022
    public static final int END_RESP = 48; //Nueva versión TEIDE 2022
    public static final int SEX = 3; //Nueva versión TEIDE 2022
    public static final int START_KNOW = 48; //Nueva versión TEIDE 2022
    public static final int END_KNOW = 51; //Nueva versión TEIDE 2022
    public static final int START_OPINION = 51; //Nueva versión TEIDE 2022
    public static final int END_OPINION = 58; //Nueva versión TEIDE 2022
    
    public static final int USER_VALUES_ROWS = 22;
    public static final int USER_VALUES_COLS = 10;
    public static final int[] SATISFACTION_COLS = {8,0,2,4,6};
    public static final int[] IMPORTANCE_COLS = {9,1,3,5,7};
    public static String MAN_VALUE = "1";
    public static String WOMAN_VALUE = "2";
    public static final int CENTER_VALUES = 6;
    public static final int STUDENT_VALUES = 4;
    
    public static final int[] NAME_CORDS = {2,1};
    public static final int[] DATE_CORDS = {2,11};
    public static final int[] MAN_CORDS = {4,1};
    public static final int[] WOMAN_CORDS = {4,8};
    public static final int[] PEOPLE_CORDS = {6,7};
    public static final int[] START_USER_RESP = {12,1};
    public static final int[] CENTER_KNOW = {36,6};
    public static final int[] STUDENT_KNOW = {44,5};
    public static final int[] BETTER_OPINION = {54,0};
    public static final int[] BETTER_OPINION_COUNT = {54,12};
    public static final int[] PLEASE_OPINION = {76,0};
    public static final int[] PLEASE_OPINION_COUNT = {76,12};
    public static final int N_MAX_OPINION = 10; 
}
