public class TextDisplay{
    
    // prints out ======= for design purposes
    public static void design() {
        System.out.println("======================================================================================================================");
    }

    // clear console method
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }


}