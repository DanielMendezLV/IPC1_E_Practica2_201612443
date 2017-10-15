/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author danie
 */
public class AtomicIntegerCounter {
    private final AtomicInteger i = new AtomicInteger();
    public void cambiarAtomico(Integer valor)
    {
        i.set(valor);
    }   
    public int get()
    {
        return i.get();
    }
}
