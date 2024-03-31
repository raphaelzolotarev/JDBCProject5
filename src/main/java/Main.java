import be.config.MySQLConfig;
import be.model.Account;
import be.model.UserDetail;
import be.service.AccountService;
import be.service.UserDetailService;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = MySQLConfig.getConnection();
        AccountService accountService = new AccountService();
        UserDetailService userDetailService = new UserDetailService();

        //SELECT
            System.out.println("\n**********show all accounts**********");
            List<Account> listAccount = accountService.getAccounts();
            listAccount.forEach(System.out::println);

            System.out.println("\n**********show all user details**********");
            List<UserDetail> listUserDetail = userDetailService.getAllUsersDetails();
            listUserDetail.forEach(System.out::println);

            System.out.println("\n**********show one account**********");
            Account accountToShow = accountService.getAccount("almaz");
            System.out.println(accountToShow==null?"Sorry this account doesnt exists":accountToShow);

            System.out.println("\n**********show one user details**********");
            UserDetail userToShow = userDetailService.getUserDetails(accountToShow);
            System.out.println(userToShow==null?"Sorry this user details doesnt exists":userToShow);

        //ADD
            System.out.println("\n**********add new user details**********");
            System.out.println(userDetailService.addUserDetail("bobbyv", "123", "Bobby", "Valentino", "bob@val.com"));
            System.out.println(userDetailService.addUserDetail("almaz", "123", "Elmaz", "Dz", "elmaz@intec.be"));
            System.out.println(userDetailService.addUserDetail("maximus", "123", "Maxime", "Deroo", "maxim@intec.com"));
            System.out.println(userDetailService.addUserDetail("haytamor", "123", "Haytam", "Aroui", "haytam@intec.com"));
            System.out.println(userDetailService.addUserDetail("abdulai", "123", "Abdul", "Alyoussef", "abdul@intec.com"));
            System.out.println(userDetailService.addUserDetail("abdulai2", "123", "Abdul", "Alyoussef", "abdul2@intec.com"));

            System.out.println("\n**********add new account**********");
            System.out.println(accountService.addAccount("hilal3","123"));

        //DELETE
            System.out.println("\n**********delete an account**********");
            System.out.println(accountService.deleteAccount("almaz"));

            System.out.println("\n**********delete a user**********");
            Account accountToDelete = accountService.getAccount("almaz");
            System.out.println(userDetailService.deleteUserDetails(accountToDelete));

    }
}