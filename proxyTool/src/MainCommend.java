import java.util.Scanner;

public class MainCommend {
    public static void main(String[] args) {
        System.out.print("please entry commend: ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextLine().equals("fp -p")){
            String filePath;
            if(args != null && args.length > 0){
                filePath = args[0];
            }else {
                System.out.print("please entry filePath: ");
                filePath = scanner.nextLine();
            }
            System.out.println("you entry path: " + filePath);
            FindProxy findProxy = new FindProxy();
            boolean result = findProxy.findProxyByPath(filePath);
            System.out.println("responseJson: " + findProxy.connectAndGetResponseJson());
            if (result){
                System.out.println("execute commend success..");
            }
        }
    }
}
