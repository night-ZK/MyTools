import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FindProxy {
    public boolean findProxyByPath(String path){
        File file = new File(path);
        if(file != null) System.out.println("find file..");
        List proxyAddressList = getProxy(file);
        return writeFile(file, proxyAddressList);
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
                    bw.write("newParagraph: ");
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
                proxyCountString.add(proxyAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proxyCountString;
    }
}
