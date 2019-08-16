package dao;

public class proxy {
    private String connectCode;
    private ResultMessage resultMessage;

    public String getConnectCode() {
        return connectCode;
    }

    public void setConnectCode(String connectCode) {
        this.connectCode = connectCode;
    }

    public ResultMessage getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(ResultMessage resultMessage) {
        this.resultMessage = resultMessage;
    }
}
