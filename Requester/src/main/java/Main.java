public class Main {
    public static void main(String[] args) {
        String request = "Select * FROM tony.transactions WHERE id = {some} AND status = {some}";

        Requester requester = new Requester(request, "input.xlsx");
        System.out.println(requester.prepareRequests());

    }
}
