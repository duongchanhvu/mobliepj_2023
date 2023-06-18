package objects;

public class User {
    public String email;
    public String userID;
    public double balance;

    public User(){

    }

   public User(String email, String userID){
       this.email = email;
       this.userID = userID;
   }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getUserID(){
        return userID;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }



}
