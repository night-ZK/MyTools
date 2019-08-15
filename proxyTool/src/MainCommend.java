import java.util.Scanner;

public class MainCommend {
    public static void main(String[] args) {
        System.out.print("please entry commend: ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextLine().equals("fp -p")){
            System.out.print("please entry filePath: ");
            String filePath = scanner.nextLine();
            System.out.println("you entry path: " + filePath);
            FindProxy findProxy = new FindProxy();
            boolean result = findProxy.findProxyByPath(filePath);
            if (result){
                System.out.println("execute commend success..");
            }
        }
    }
}
