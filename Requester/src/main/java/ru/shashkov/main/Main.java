package ru.shashkov.main;

import ru.shashkov.model.RequestWriter;

public class Main {
    public static void main(String[] args) {
        String request = "Select * FROM tony.transactions WHERE id = {some} AND status = {some};";

        RequestWriter requestWriter = new RequestWriter(request, "input.xlsx");
        System.out.println(requestWriter.prepareRequests());

        requestWriter.writeRequests();
    }
}
