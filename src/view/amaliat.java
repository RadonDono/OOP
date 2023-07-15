package view;

import java.util.Scanner;

public class amaliat {
    user_view comshow=new user_view();
    check checks=new check();
    Scanner scan;
    amaliat(Scanner sc){
        scan=sc;
    }
    void vorod(){
        //dar vorod tanha login ya create az karbar porside mishavad
        comshow.vorod_kardan();
        String vorod=scan.nextLine();
        if(checks.check_Stringvorod(vorod)==1){
            login();
        }
        else if(checks.check_Stringvorod(vorod)==0){
            create_account();
        }
        else{
            comshow.eror_inavalid();
            vorod();
        }
        vorod();
    }
    void login(){
        comshow.enter_username();
        String username=scan.nextLine();
        if (checks.return_check(username)){
            return;
        }
        else {
            String pass=get_password();
            if (pass.equals("return")){
                return;
            }
            else{
                //inja ma bayad be tamam esm ha va password hadastresi dashte bashim
                //username check
                //password check
            }
        }
    }
    String get_password(){
        comshow.enter_password();
        String pass=scan.nextLine();
        if (checks.return_check(pass)){
            return "-1";
        }
        if (checks.pass_check(pass)){
            return pass;
        }
        else {
            comshow.eror_password();
            get_password();
            return "43";
        }
    }
    void create_account(){
        comshow.enter_username();
        String username=scan.nextLine();
        if (checks.return_check(username)){
            return;
        }
        else {
            String pass=get_password();
            if (pass.equals("return")){
                return;
            }
            else{
                //inja ma bayad be tamam esm ha va password hadastresi dashte bashim
                //username check
                //password check
                //create account
            }
        }
    }


}
