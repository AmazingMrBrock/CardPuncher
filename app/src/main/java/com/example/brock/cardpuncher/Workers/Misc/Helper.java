package com.example.brock.cardpuncher.Workers.Misc;

import android.util.Log;

/**
 * Created by b on 2016-12-01.
 */

public class Helper {
    public static Helper helper = new Helper();

    public Helper() {
        super();
    }

    public int[] variableArray(int newVar, int[] thisArray){
        int[] newArray = new int[thisArray.length + 1];
        for (int i = 0; i < thisArray.length + 1; i++){
            if(i < thisArray.length){
                newArray[i] = thisArray[i];
            }
            else{
                newArray[thisArray.length] = newVar;
            }
        }
        return newArray;
    }

    public int findInArray(int toFind, int[] holdingArray){
        int position = -1;

        for(int i = 0; i< holdingArray.length; i++){
            if(holdingArray[i] == toFind){
                position = i;
            }
        }

        if(position == -1){
            Log.d("ERROR", "findInArray: array did not contain the object.");
        }
        return position;
    }

    public int findInArray(String toFind, String[] holdingArray){
        int position = -1;

        for(int i = 0; i< holdingArray.length; i++){
            if(holdingArray[i] == toFind){
                position = i;
            }
        }

        if(position == -1){
            Log.d("ERROR", "findInArray: array did not contain the object.");
        }
        return position;
    }

}