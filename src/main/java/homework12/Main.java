package homework12;

import homework12.daos.AccountDao;
import homework12.daos.ClientDao;
import homework12.daos.StatusesDao;
import homework12.entities.Accounts;
import homework12.entities.ClientStatus;
import homework12.entities.Clients;
import homework12.entities.Statuses;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

        ClientDao clientDao = new ClientDao();
        for (Clients clients : clientDao.findAllClients()) {
            System.out.println(clients);
        }

        System.out.println("INSERT NEW CLIENT");

        Clients clients = new Clients();
        clients.setName("yyy");
        clients.setEmail("yyy@gmail.com");
        clients.setPhone(3303223233213L);
        clients.setAbout("yyy");
        clients.setAge(33);

        clientDao.insertClients(clients);

        System.out.println("AFTER INSERT:\n" + clients);

        System.out.println("***********************************************************");

        System.out.println("DELETE CLIENT");

        clientDao.deleteClients(9);

        System.out.println("AFTER DELETE:\n" + clients);

        System.out.println("***********************************************************");

        Clients client1 = clientDao.findClientsById(12);
        System.out.println("Client before update:\n" + client1);
        client1.setPhone(380913233232L);
        clientDao.updateClients(client1);
        System.out.println("Client after update:\n" + client1);

        System.out.println(clientDao.findClientsByPhone(380913233232L));

        System.out.println("***********************************************************");

        System.out.println(" Clients where id equals accounts client_id:");
        for (Clients clients2 : clientDao.findAllClientsWhereIDClientEqualsAccountsClientId()) {
            System.out.println(clients);
        }

        System.out.println("***********************************************************");

        System.out.println(" Clients name,email,alias > 18:");
        for (ClientStatus clientStatus : clientDao.findNameEmailAlias()) {
            System.out.println(clientStatus);
        }

        System.out.println("***********************************************************");

        AccountDao accountDao = new AccountDao();
        for (Accounts accounts : accountDao.findAllAccounts()) {
            System.out.println(accounts);
        }

        Accounts accounts = new Accounts();
        accounts.setClient_id(23);
        accounts.setNumber("777 333 444 222");
        accounts.setValue(707.07);
        accountDao.insertAccount(accounts);

        System.out.println("***********************************************************");

        System.out.println("BEFORE DELETE");

        accountDao.deleteAccount(7);

        System.out.println("AFTER DELETE:\n" + accounts);

        System.out.println("***********************************************************");

        Accounts account = accountDao.findByIdAccounts(1);
        System.out.println("Account before update:\n" + account);
        account.setNumber("77 777 777 777");
        accountDao.updateAccount(account);
        System.out.println("Account after update:\n" + account);

        System.out.println("***********************************************************");

        System.out.println(" Accounts where value bigger than given value: ");
        for (String number : accountDao.findAccountNumberBiggerValue(1000.01)) {
            System.out.println(number);
        }

        System.out.println("***********************************************************");

        StatusesDao statusesDao = new StatusesDao();
        for (Statuses statuses : statusesDao.findAllStatuses()) {
            System.out.println(statuses);
        }

        Statuses status = new Statuses();
        status.setAlias("KRITIN");
        status.setDescription("KRITIN service. ");
        statusesDao.insertStatuses(status);

        System.out.println("***********************************************************");

        System.out.println("BEFORE DELETE");

        statusesDao.deleteStatuses(2);

        System.out.println("AFTER DELETE");

        System.out.println("***********************************************************");

        Statuses statuses1 = statusesDao.findStatusesById(19);
        System.out.println("Status before update:\n" + statuses1);
        statuses1.setDescription("Premium service.");
        statusesDao.updateStatuses(statuses1);
        System.out.println("Status after update:\n" + statuses1);
    }
}
