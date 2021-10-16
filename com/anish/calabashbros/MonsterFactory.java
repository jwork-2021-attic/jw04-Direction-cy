package com.anish.calabashbros;

public class MonsterFactory implements Factory<Monster>{
    public Monster[] create(int n){
        return new Monster[n];
    }
}