import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindProxy {

    List<String> urlParameters = new ArrayList<>();
    public boolean findProxyByPath(String path){
        File file = new File(path);
        if(file.exists()) {
            System.out.println("find file..");
            List proxyAddressList = getProxy(file);
            return writeFile(file, proxyAddressList);
        }else{
            System.out.println("not find file..");
            new Scanner(System.in).nextLine();
        }
        return false;
    }

    private boolean writeFile(File file, List<String> proxyAddressList) {
        try(FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw)){
            for (String address: proxyAddressList) {
                bw.write(address);
                bw.write(13);
                bw.write(10);
                int index = proxyAddressList.indexOf(address);
                if(index != 0 && index % 50 == 0) {
                    bw.write(13);
                    bw.write(10);
                    bw.write("newParagraph:");
                    bw.write(13);
                    bw.write(10);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String analysisProxy(String proxyAddress) {
        if(proxyAddress.contains("@")){

            int index = proxyAddress.indexOf("@");
            proxyAddress = proxyAddress.substring(0, index);
            System.out.println("analysisAddress: " + proxyAddress);
        }
        return proxyAddress;
    }

    private List getProxy(File file){
        List proxyCountString = new ArrayList();
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
             BufferedReader br = new BufferedReader(isr)){
            String proxyAddress;
            while((proxyAddress = br.readLine()) != null){
                proxyAddress = analysisProxy(proxyAddress);
                urlParameters.add(getURLParameters(proxyAddress));
                proxyCountString.add(proxyAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proxyCountString;
    }

    private String getURLParameters(String proxyAddress) {
        String urlps = "&ip_ports%5B%5D=1-zxk%3A2-zxk";
        String[] ps = proxyAddress.split(":");
        if (ps.length < 2) return "";
        urlps = urlps.replace("1-zxk",ps[0]);
        urlps = urlps.replace("2-zxk",ps[1]);
        return urlps;
    }

    private String getURL(){
        String url = "http://www.xdaili.cn/ipagent//checkIp/ipList?";
        StringBuffer sb = new StringBuffer();
        for (String urlps:urlParameters) {
            sb.append(urlps);
        }
        String ps = sb.toString().replaceFirst("&","");
        return url += ps;
    }

    public String connectAndGetResponseJson(){
        String urlAddress = getURL();
//        String urlAddress = "http://www.xdaili.cn/ipagent//checkIp/ipList?ip_ports%5B%5D=177.124.57.214%3A8080&ip_ports%5B%5D=176.227.188.66%3A53281&ip_ports%5B%5D=103.228.117.244%3A8080&ip_ports%5B%5D=190.82.70.109%3A80&ip_ports%5B%5D=91.121.162.173%3A80&ip_ports%5B%5D=187.53.60.82%3A8080&ip_ports%5B%5D=87.103.234.116%3A3128&ip_ports%5B%5D=91.203.125.46%3A8081&ip_ports%5B%5D=42.115.88.220%3A53281&ip_ports%5B%5D=188.0.138.147%3A8080&ip_ports%5B%5D=185.148.220.11%3A8081&ip_ports%5B%5D=187.44.1.167%3A8080&ip_ports%5B%5D=185.148.218.246%3A8081&ip_ports%5B%5D=175.101.16.92%3A8080&ip_ports%5B%5D=31.31.77.107%3A3128&ip_ports%5B%5D=114.7.4.82%3A8080&ip_ports%5B%5D=191.235.87.19%3A8080&ip_ports%5B%5D=154.46.204.36%3A80&ip_ports%5B%5D=162.243.38.127%3A3128&ip_ports%5B%5D=45.55.238.49%3A3128&ip_ports%5B%5D=190.252.123.122%3A8080&ip_ports%5B%5D=177.67.84.153%3A8080&ip_ports%5B%5D=202.152.40.28%3A8080&ip_ports%5B%5D=104.236.1.180%3A80&ip_ports%5B%5D=67.205.155.140%3A80&ip_ports%5B%5D=177.38.243.26%3A8080&ip_ports%5B%5D=144.217.203.149%3A80&ip_ports%5B%5D=182.53.95.147%3A8080&ip_ports%5B%5D=149.56.98.0%3A8080&ip_ports%5B%5D=175.192.89.92%3A3128&ip_ports%5B%5D=95.161.177.3%3A8080&ip_ports%5B%5D=189.32.207.222%3A8080&ip_ports%5B%5D=89.40.115.113%3A3128&ip_ports%5B%5D=85.255.7.221%3A80&ip_ports%5B%5D=92.208.138.179%3A80&ip_ports%5B%5D=41.207.49.136%3A8080&ip_ports%5B%5D=160.202.43.75%3A8080&ip_ports%5B%5D=212.96.99.229%3A8080&ip_ports%5B%5D=149.56.193.126%3A8080&ip_ports%5B%5D=193.124.186.124%3A3128&ip_ports%5B%5D=104.196.114.98%3A80&ip_ports%5B%5D=192.99.79.158%3A8080&ip_ports%5B%5D=46.44.60.71%3A8081&ip_ports%5B%5D=5.2.75.15%3A1080&ip_ports%5B%5D=139.59.104.254%3A80&ip_ports%5B%5D=94.177.250.149%3A3128&ip_ports%5B%5D=177.67.81.96%3A3128&ip_ports%5B%5D=177.54.147.254%3A3128&ip_ports%5B%5D=138.197.140.16%3A8080&ip_ports%5B%5D=88.147.189.62%3A8081&ip_ports%5B%5D=198.50.203.53%3A8080";
        try {
            URL proxyURL = new URL(urlAddress);
            URLConnection urlConnection = proxyURL.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            String responseJsonLine;
            StringBuffer responseJson = new StringBuffer();
            while((responseJsonLine = br.readLine()) != null){
                responseJson.append(responseJsonLine);
            }
            return responseJson.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        FindProxy f = new FindProxy();
        System.out.println(f.getURLParameters("192.168.1.1:80"));
//        System.out.println(f.new FindProxy());
    }
}
