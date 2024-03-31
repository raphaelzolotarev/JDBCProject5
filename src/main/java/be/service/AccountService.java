package be.service;

import be.model.Account;
import be.repository.AccountRepository;

import java.util.List;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();

    public String addAccount(Account account) {
        return accountRepository.addAccount(account) ? "New account added!" : "You cannot add an account with that name because it already exists, choose another name";
    }

    public String addAccount(String username, String password) {
        Account account = new Account(username, password);
        return this.addAccount(account);
    }

    public Account getAccount(String username) {
        return accountRepository.getAccount(username);
    }

    public List<Account> getAccounts() {
        return accountRepository.getAllAccounts();
    }

    public String deleteAccount(String username) {
        int value = accountRepository.deleteAccount(username);
        if (value == 1){
            return "User deleted!";
        }
        else if (value == -1){
            return "Sorry an error occured, cannot proceed!";
        }
        return "This user doesnt exists!";

    }
}
