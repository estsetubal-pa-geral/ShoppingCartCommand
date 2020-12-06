/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.patterns.memento.model;

import java.util.Stack;

public class Caretaker {

    private Stack<Memento> objMementos;
    private Originator originator;

    public Caretaker(Originator originator) {
        this.originator = originator;
        objMementos = new Stack();
    }

    public void saveState() {
        objMementos.add(originator.createMemento());
    }

    public void restoreState() {
        if (!objMementos.empty())
            originator.setMemento(objMementos.pop());

    }
}
