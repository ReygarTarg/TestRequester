package ru.shashkov.view;

import lombok.AllArgsConstructor;
import ru.shashkov.controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class ListenerCreator {
    private final Controller controller;

    ActionListener createListenerForRestartButton() {
     return new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             controller.writeRequests();
         }
     };
    }
}
