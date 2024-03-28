package be.service;

import be.model.Account;
import be.model.UserDetail;
import be.repository.UserDetailRepository;

public class UserDetailService {
    private UserDetailRepository userDetailRepository = new UserDetailRepository();
    private AccountService accountService = new AccountService();

    public void addUserDetail(String username, String password,
                              String firstName, String lastName, String email) {

        Account account = new Account(username, password);
        UserDetail userDetail = new UserDetail(firstName, lastName, email, account);

        Account dbAccount = accountService.getAccount(username);

        if (dbAccount != null && !password.equals(dbAccount.getPassword())){
            throw new RuntimeException("Account exists, wrong password");
        } else if (dbAccount == null) {
            accountService.addAccount(account);
        }

        userDetailRepository.addUserDetail(userDetail);
    }
}
