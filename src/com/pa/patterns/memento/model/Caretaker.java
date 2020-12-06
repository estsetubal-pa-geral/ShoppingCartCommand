/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.patterns.memento.model;

import java.util.*;

public class Caretaker {
    private Map<Date, Memento> objMementos;
//    private Stack<Memento> objMementos;
//    private Memento memento;

    private Originator originator;

    public Caretaker(Originator originator) {
        this.originator = originator;
        objMementos = new HashMap<Date, Memento>();
//        objMementos = new Stack();
    }

    public void saveState() {
        objMementos.put(new Date(), originator.createMemento());
//        objMementos.add(originator.createMemento());
//        memento = originator.createMemento();
    }

    public void restoreState(Date date) throws NoMementoException {
        Memento memento = objMementos.get(date);
        if(memento == null){
            throw new NoMementoException();
        }
        originator.setMemento(memento);
    }
/*
    public void restoreState() throws NoMementoException {
        if (objMementos.empty()) {
            throw new NoMementoException();
        }
        originator.setMemento(objMementos.pop());
//        if (memento == null) {
//            throw new NoMementoException();
//        }
//        originator.setMemento(memento);
    }*/

    public Collection<Date> getDates() {
        return objMementos.keySet();
    }
}
