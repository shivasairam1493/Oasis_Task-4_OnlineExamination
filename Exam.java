import java.util.*;

class Exam 
  {
    private String username;
    private String password;
    private String fullName;
    
    private boolean isLoggedIn;
    private int timeRemaining;
    private int questionCount;
    private int[] userAnswers;
    private int[] correctAnswers;
    private static int i = 0;
 

    void register(Exam[] e) 
    {
        if (i != 5) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter user name:");
            String u = sc.nextLine();
            System.out.println("Enter your password:");
            String p = sc.nextLine();
            System.out.println("Enter your Full Name:");
            String q = sc.nextLine();

            e[i]=new Exam();
            e[i].username=u;
            e[i].password=p;
            e[i].fullName=q;
            i++;

            System.out.println("Your registration is successful");
            System.out.println(i);
        }
        
         else 
        {
            System.out.println("No more registrations are allowed");
        }

        this.isLoggedIn = false;
        this.timeRemaining = 10; // in minutes
        this.questionCount = 10;
        this.userAnswers = new int[questionCount];
        this.correctAnswers = new int[questionCount];
        // initialize correct answers with random values (0 or 1)
        Random random = new Random();
        for (int j = 0; j < questionCount; j++) 
        {
            correctAnswers[j] = random.nextInt(2); // Generates 0 or 1
        }
    }

    void seeProfile(Exam[] e)
    {   
          Scanner sc=new Scanner(System.in);
          System.out.println("Enter your username:");
          String a=sc.nextLine();
          
          System.out.println("Enter password");
          String b=sc.nextLine();    
      
          for (int j = 0; j < i; j++) {
            if (a.equals(e[j].username) && b.equals(e[j].password)) {
                isLoggedIn = true;
                System.out.println("Login successful");
              
                   System.out.println("Your details are:");
                   System.out.println("User name : "+e[j].username);
                   System.out.println("Password: "+e[j].password);    
                   System.out.println("Full Name: "+e[j].fullName);
                   isLoggedIn=false;
                   break;
               } 
         }
       
    }
    
     
     void updateProfile(Exam[] e) 
     {
        System.out.println("Enter username:");
        Scanner scanner = new Scanner(System.in);
        String inputUsername = scanner.nextLine();
        System.out.println("Enter password:");
        String inputPassword = scanner.nextLine();

        for (int j = 0; j < i; j++) 
        {
            if (inputUsername.equals(e[j].username) && inputPassword.equals(e[j].password)) {
                isLoggedIn = true;
                System.out.println("Login successful");
                System.out.println("Enter new password:");
                String newPassword = scanner.nextLine();
                e[j].password = newPassword;
                System.out.println("Password updated successfully");
                isLoggedIn = false;
                return;
            }
        }
        System.out.println("Incorrect username or password");
    }
    void login(Exam[] e)
     {
    
        System.out.println("Log in to give the Exam or to see your profile");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Password: ");
        String inputPassword = scanner.nextLine();

        for (int j = 0; j < i; j++) 
        {
            if (inputUsername.equals(e[j].username) && inputPassword.equals(e[j].password)) 
            {
                isLoggedIn = true;
                System.out.println("Login successful. Best of Luck for your exam. Please logout after completion of your exam.");
                
               
                break;
            } 
            
          else
           {
                System.out.println("Login failed. Please try again.");
            }
        }
    }
    

    void logout() 
    {
        isLoggedIn = false;
        System.out.println("Logout successful.");
    }

    void startExam(Exam[] e) 
    {
        if (!isLoggedIn)
        {
            login(e);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have " + timeRemaining + " minutes to complete the exam.");
        for (int j = 0; j < questionCount; j++) {
            System.out.println("Question " + (j + 1) + ":");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("3. Option 3");
            System.out.println("4. Option 4");
            System.out.print("Select your answer: ");
            int answer = scanner.nextInt();
            userAnswers[j] = answer;
        }
        
        //checks whether are there any users or not
         if (!isLoggedIn)
          {
            System.out.println("Your details are not found");
            return;
           }

        System.out.println("Would you like to submit?\n1: Yes\n2: NO");
        int n = scanner.nextInt();
        if (n == 1) 
        {
            submitExam();
        }
        
         else
          {
            // auto-submit exam when time is up
            try 
            {
                Thread.sleep(timeRemaining * 10 * 1000);
            } catch (InterruptedException k) {
                k.printStackTrace();
                submitExam();
            }

        }
    }

    void submitExam() 
    {
        if (!isLoggedIn) 
        {
            System.out.println("Please login first.");
            return;
        }
        int score = 0;
        for (int j = 0; j < questionCount; j++) 
        {
            if (userAnswers[j] == correctAnswers[j])
             {
                score++;
             }
        }
        System.out.println("Your score is " + score + " out of " + questionCount + ".");
        System.out.println("Best of luck :)");
        logout();
    }

    public static void main(String[] args)
     {
        Scanner sc = new Scanner(System.in);
        Exam[] e = new Exam[5];
        Exam examSystem = new Exam();
        int ch;
        
        System.out.println("---------------------------------------------------");
        System.out.println("Instructions :");
        System.out.println("1->There are 10 MCQs and time limit is 10 minutes");
        System.out.println("2->If you not submit the exam in 10 minutes it will be automatically submitted ");
         System.out.println("after 10 minutes");
        System.out.println("---------------------------------------------------");

        do {
            System.out.println("\nChoose the below option:");
            System.out.println("1. Register for exam");
            System.out.println("2. Change password");
            System.out.println("3. Take exam");
            System.out.println("4. See your profile");
            System.out.println("5. Quit the page");

            ch = sc.nextInt();

            switch (ch) 
            {
                case 1:
                    examSystem.register(e);
                    break;
                case 2:
                    examSystem.updateProfile(e);
                    break;
                case 3:
                    examSystem.startExam(e);
                    break;
                case 4:
                    examSystem.seeProfile(e);
                    break;
                case 5:
                    examSystem.logout();
                    break;
                default:
                    System.out.println("Choose the correct option");
                    break;
            }

        } while (ch != 5);
    }
}
	
