package be.service;

import be.model.Account;
import be.model.UserDetail;
import be.repository.UserDetailRepository;

import java.util.List;

public class UserDetailService {
    private UserDetailRepository userDetailRepository = new UserDetailRepository();
    private AccountService accountService = new AccountService();

    public String addUserDetail(String username, String password,
                              String firstName, String lastName, String email) {

        Account account = new Account(username, password);
        UserDetail userDetail = new UserDetail(firstName, lastName, email, account);

        Account dbAccount = accountService.getAccount(username);

        if (dbAccount != null && !password.equals(dbAccount.getPassword())){
            throw new RuntimeException("Account exists, wrong password");
        } else if (dbAccount == null) {
            accountService.addAccount(account);
        }

        return userDetailRepository.addUserDetail(userDetail) ? "User details added!" : "This user detail already exists, please choose another one";
    }


    public List<UserDetail> getAllUsersDetails() {
        return userDetailRepository.getAllUserDetail();
    }
    public UserDetail getUserDetails(Account account) {
        return userDetailRepository.getUserDetail(account);
    }

    public String deleteUserDetails(Account account) {
        int value = userDetailRepository.deleteUserDetail(account);
        if (value == 1){
            return "User details deleted!";
        }
        else if (value == -1){
            return "Sorry an error occured, cannot proceed!";
        }
        return "This user details dont exists!";

    }
}
